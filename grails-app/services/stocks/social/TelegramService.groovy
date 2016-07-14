package stocks.social

import grails.converters.JSON
import grails.gsp.PageRenderer
import grails.plugin.cache.web.filter.PageFragmentCachingFilter
import grails.util.Environment
import stocks.FarsiNormalizationFilter
import stocks.User
import stocks.rate.Coin
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Oil
import stocks.tse.Symbol
import stocks.tse.SymbolAverageTrade
import stocks.util.EncodingHelper

import java.nio.charset.StandardCharsets

class TelegramService {

    def commands = [
            Symbol   : FarsiNormalizationFilter.apply('نماد'),
            Currency : FarsiNormalizationFilter.apply('ارز'),
            Coin     : FarsiNormalizationFilter.apply('سکه'),
            Metal    : FarsiNormalizationFilter.apply('فلز'),
            Oil      : FarsiNormalizationFilter.apply('نفت'),
            Disconnet: FarsiNormalizationFilter.apply('قطع اتصال')
    ]

    def keyboards = [
            start: [
                    [commands.Symbol],
                    [commands.Currency],
                    [commands.Coin],
                    [commands.Metal],
                    [commands.Oil]
            ]
    ]

    PageRenderer groovyPageRenderer
    def priceService
    def marketStatusService

    def bulkDataService

    Boolean sendMessage(User user, String message, List<List<String>> keyboardLayout = null, resizeKeyboard = true) {
        if (user.telegramUser)
            sendMessage(user.telegramUser, message, keyboardLayout, resizeKeyboard)
        else false
    }

    Boolean sendMessage(TelegramUser user, String message, List<List<String>> keyboardLayout = null, resizeKeyboard = true) {
        sendMessage(user?.chatId, message, keyboardLayout, resizeKeyboard)
    }

    Boolean sendMessage(Long chatId, String message, List<List<String>> keyboardLayout = null, resizeKeyboard = true) {
        if (!keyboardLayout)
            keyboardLayout = keyboards.start
        def parameters = [chat_id: chatId, text: message]
        parameters.put('reply_markup', ([keyboard: keyboardLayout, one_time_keyboard: true, resize_keyboard: resizeKeyboard] as JSON)?.toString())
        sendRequest('sendMessage', parameters)?.ok
    }


    def dispatchUpdates() {
        def status = TelegramStatus.list(max: 1)?.find()
        if (!status) {
            status = new TelegramStatus()
//            status.save()
            bulkDataService.save(status)
        }

        def response = sendRequest('getUpdates', [offset: status.lastUpdateId + 1])
        if (!response?.ok)
            return
        def updates = response.result

        updates.each { update ->
            Boolean result
            def message = FarsiNormalizationFilter.apply(update.message?.text?.toString()?.trim())
            def chatId = update?.message?.chat?.id as Long
            def userName = update?.message?.from?.username ?: "CHAT_${chatId}"
            if (userName) {
                if (message?.startsWith('connect'))
                    result = connect(update?.message?.from as Map, message?.replace('connect', '')?.trim(), chatId)
                else if (message?.equals(commands.Disconnet))
                    result = disconnect(chatId)
                else if (['/help', 'help', '/h', 'h'].contains(message?.toLowerCase()))
                    result = showHelp(chatId)
                else if ([commands.Symbol, commands.Currency, commands.Coin, commands.Metal, commands.Oil].contains(message))
                    result = prepareForCommand(userName, commands.find { it.value == message }?.key, chatId)
                else {
                    def command = TelegramCommandHistory.findByUserName(userName)?.firstCommand
                    result = command ? "send${command}Data"(userName, message, chatId) : false
                }

                if (!result) {
                    sendMessage(chatId, groovyPageRenderer.render(view: '/social/notFound', model: [
                            phrase: message
                    ]))
                    sendMessage(chatId, groovyPageRenderer.render(view: '/social/help'))
                }
            }
        }

        def lastUpdateId = updates.collect { it.update_id }.max() as Long
        if(lastUpdateId) {
            status.lastUpdateId = lastUpdateId
//        status.save(flush: true)
            bulkDataService.save(status)
        }
    }

    Boolean showHelp(Long chatId) {
        def isConnected = TelegramUser.findByChatId(chatId) ? true : false
        sendMessage(chatId, groovyPageRenderer.render(view: '/social/help', model: [isConnected: isConnected]))
        true
    }

    Boolean disconnect(Long chatId) {
        try {
            def telegramUser = TelegramUser.findByChatId(chatId)
            if (telegramUser) {
                telegramUser.user.telegramUser = null
//                telegramUser.user.save(flush: true)
                bulkDataService.save(telegramUser.user)
                telegramUser.delete(flush: true)
            }
        }
        catch (ignored) {

        }
        sendMessage(chatId, groovyPageRenderer.render(view: '/social/disconnectSucceed'))
        return true
    }

    Boolean connect(Map from, String code, Long chatId) {
        try {
            def parts = code.split(':')
            if (parts.size() != 2) {
                sendMessage(chatId, groovyPageRenderer.render(view: '/social/connectFailed'))
                return true
            }
            def id = parts[0] as Long
            def validator = parts[1] as String

            if (EncodingHelper.MD5("connect_${id}_to_telgram") != validator) {
                sendMessage(chatId, groovyPageRenderer.render(view: '/social/connectFailed'))
                return true
            }

            def user = User.get(id)
            if (!user) {
                sendMessage(chatId, groovyPageRenderer.render(view: '/social/connectFailed'))
                return true
            }

            def telegramUser = TelegramUser.get(user?.telegramUser?.id)
            if (!telegramUser)
                telegramUser = new TelegramUser()
            telegramUser.identifier = from.id as Long
            telegramUser.firstName = from.first_name
            telegramUser.lastName = from.last_name
            telegramUser.userName = from.username
            telegramUser.chatId = chatId
            telegramUser.user = user
//            telegramUser.save()
            bulkDataService.save(telegramUser)

            user.telegramUser = telegramUser
//            user.save()
            bulkDataService.save(user)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/connectSucceed'))
        }
        catch (ignored) {

        }
        return true
    }

    Boolean prepareForCommand(String userName, String command, Long chatId) {
        def cmd = TelegramCommandHistory.findByUserName(userName)
        if (!cmd)
            cmd = new TelegramCommandHistory(userName: userName)
        cmd.secondCommand = cmd.firstCommand
        cmd.firstCommand = command
//        def result = cmd.save() ? true : false
        bulkDataService.save(cmd)

        sendMessage(chatId, groovyPageRenderer.render(view: '/social/specifyName', model: [propertyType: command]), "get${command}Keyboard"(userName) as List<List<String>>)

        true
    }

    Boolean sendSymbolData(String userName, String name, Long chatId) {
        name = name?.split(' ')?.find()
        if (!name || name == '')
            return false
        def items = Symbol.search(name, max: 5)?.results
        items.each { Symbol searchItem ->
            def item = Symbol.get(searchItem?.id)
            savePropertyHistory(userName, 'Symbol', item?.toString())
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/symbol', model: [
                    symbol        : item,
                    lastDailyTrade: priceService.lastDailyTrade(item),
                    date          : marketStatusService.correctMarketLastDataUpdateTime(marketStatusService.MARKET_STOCK, new Date())
            ]))
        }
        items.size() > 0
    }

    List<List<String>> getSymbolKeyboard(String userName) {
        def list = TelegramPropertyHistory.createCriteria().list {
            eq('userName', userName)
            eq('propertyType', 'Symbol')
            order('date', ORDER_DESCENDING)
            projections {
                property('propertyName')
            }
            maxResults(10)
        }?.collect { [it] }

        list.size() ? list : null
    }

    Boolean sendCurrencyData(String userName, String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Currency.search(name, max: 5)?.results
        items.each { Currency item ->
            savePropertyHistory(userName, 'Currency', item?.name)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/currency', model: [
                    currency: item
            ]))
        }
        items.size() > 0
    }

    List<List<String>> getCurrencyKeyboard(String userName) {
        def list = TelegramPropertyHistory.createCriteria().list {
            eq('userName', userName)
            eq('propertyType', 'Currency')
            order('date', ORDER_DESCENDING)
            projections {
                property('propertyName')
            }
            maxResults(10)
        }?.collect { [it] }

        list.size() ? list : null
    }

    Boolean sendCoinData(String userName, String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Coin.search(name, max: 5)?.results
        items.each { Coin item ->
            savePropertyHistory(userName, 'Coin', item?.name)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/coin', model: [
                    coin: item
            ]))
        }
        items.size() > 0
    }

    List<List<String>> getCoinKeyboard(String userName) {
        Coin.createCriteria().list {}.sort { it.name }.collect { [it.name] }
    }

    Boolean sendMetalData(String userName, String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Metal.search(name, max: 5)?.results
        items.each { Metal item ->
            savePropertyHistory(userName, 'Metal', item?.name)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/metal', model: [
                    metal: item
            ]))
        }
        items.size() > 0
    }

    List<List<String>> getMetalKeyboard(String userName) {
        Metal.createCriteria().list {}.findAll { it.name }.sort { it.name }.collect { [it.name] }
    }

    Boolean sendOilData(String userName, String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Oil.search(name, max: 5)?.results
        items.each { Oil item ->
            savePropertyHistory(userName, 'Oil', item?.name)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/oil', model: [
                    oil: item
            ]))
        }
        items.size() > 0
    }

    List<List<String>> getOilKeyboard(String userName) {
        Oil.createCriteria().list {}.sort { it.name }.collect { [it.name] }
    }

    void savePropertyHistory(String userName, String type, String name) {

        def propertyHistory = TelegramPropertyHistory.findByPropertyTypeAndPropertyNameAndUserName(type, name, userName)
        if (!propertyHistory)
            propertyHistory = new TelegramPropertyHistory(propertyType: type, propertyName: name, userName: userName)
        propertyHistory.date = new Date()
//        propertyHistory.save()
        bulkDataService.save(propertyHistory)
    }

    Map sendRequest(String method, Map parameters) {
        byte[] postData = parameters.collect { "${it.key}=${it.value.encodeAsURL()}" }.join('&').getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "https://api.telegram.org/bot${Environment.current == Environment.PRODUCTION ? '116983317:AAGiZWID3rSOT63Q9xeGfSeWTYEW3DEp9nA' : '149185899:AAGB0acLYspLNFugpu5NF4RBh5J2IwfiGq0'}/${method}";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream())
        wr.write(postData);
        try {
            JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map
        }
        catch (ignored) {
            [:]
        }
    }

}
