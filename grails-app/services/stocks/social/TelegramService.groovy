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
            symbol   : FarsiNormalizationFilter.apply('نماد'),
            currency : FarsiNormalizationFilter.apply('ارز'),
            coin     : FarsiNormalizationFilter.apply('سکه'),
            metal    : FarsiNormalizationFilter.apply('فلز'),
            oil      : FarsiNormalizationFilter.apply('نفت'),
            disconnet: FarsiNormalizationFilter.apply('قطع اتصال')
    ]

    PageRenderer groovyPageRenderer
    def priceService
    def marketStatusService

    Boolean sendMessage(User user, String message) {
        if (user.telegramUser)
            sendMessage(user.telegramUser, message)
        else false
    }

    Boolean sendMessage(TelegramUser user, String message) {
        sendMessage(user?.chatId, message)
    }

    Boolean sendMessage(Long chatId, String message) {
        sendRequest('sendMessage', [chat_id: chatId, text: message])?.ok
    }


    def dispatchUpdates() {
        def status = TelegramStatus.list(max: 1)?.find()
        if (!status) {
            status = new TelegramStatus()
            status.save()
        }

        def response = sendRequest('getUpdates', [offset: status.lastUpdateId + 1])
        if (!response?.ok)
            return
        def updates = response.result

        updates.each { update ->
            def result = false
            def message = FarsiNormalizationFilter.apply(update.message?.text?.toString())
            def chatId = update?.message?.chat?.id as Long
            if (message?.startsWith('connect'))
                result = connect(update?.message?.from as Map, message?.replace('connect', '')?.trim(), chatId)
            if (message?.startsWith(commands.disconnet))
                result = disconnect(chatId)
            else if (message?.startsWith(commands.symbol))
                result = sendSymbolStatus(message?.replace(commands.symbol, '')?.trim(), chatId)
            else if (message?.startsWith(commands.currency))
                result = sendCurrencyStatus(message?.replace(commands.currency, '')?.trim(), chatId)
            else if (message?.startsWith(commands.coin))
                result = sendCoinStatus(message?.replace(commands.coin, '')?.trim(), chatId)
            else if (message?.startsWith(commands.metal))
                result = sendMetalStatus(message?.replace(commands.metal, '')?.trim(), chatId)
            else if (message?.startsWith(commands.oil))
                result = sendOilStatus(message?.replace(commands.oil, '')?.trim(), chatId)
            else
                result = showHelp(chatId)



            if (!result) {
                sendMessage(chatId, groovyPageRenderer.render(view: '/social/notFound', model: [
                        phrase: message
                ]))
                sendMessage(chatId, groovyPageRenderer.render(view: '/social/help'))
            }
        }

        status.lastUpdateId = updates.collect { it.update_id }.max() as Long
        status.save()

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
                telegramUser.user.save(flush: true)
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
            telegramUser.save()

            user.telegramUser = telegramUser
            user.save()

            sendMessage(chatId, groovyPageRenderer.render(view: '/social/connectSucceed'))
        }
        catch (ignored) {

        }
        return true
    }

    Boolean sendSymbolStatus(String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Symbol.search(name)?.results
        items.each { Symbol searchItem ->
            def item = Symbol.get(searchItem?.id)
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/symbol', model: [
                    symbol        : item,
                    lastDailyTrade: priceService.lastDailyTrade(item),
                    date          : marketStatusService.correctMarketLastDataUpdateTime(marketStatusService.MARKET_STOCK, new Date())
            ]))
        }
        items.size() > 0
    }

    Boolean sendCurrencyStatus(String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Currency.search(name)?.results
        items.each { Currency item ->
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/currency', model: [
                    currency: item
            ]))
        }
        items.size() > 0
    }

    Boolean sendCoinStatus(String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Coin.search(name)?.results
        items.each { Coin item ->
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/coin', model: [
                    coin: item
            ]))
        }
        items.size() > 0
    }

    Boolean sendMetalStatus(String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Metal.search(name)?.results
        items.each { Metal item ->
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/metal', model: [
                    metal: item
            ]))
        }
        items.size() > 0
    }

    Boolean sendOilStatus(String name, Long chatId) {
        if (!name || name == '')
            return false
        def items = Oil.search(name)?.results
        items.each { Oil item ->
            sendMessage(chatId, groovyPageRenderer.render(view: '/social/oil', model: [
                    oil: item
            ]))
        }
        items.size() > 0
    }

    Map sendRequest(String method, Map parameters) {
        byte[] postData = parameters.collect { "${it.key}=${it.value}" }.join('&').getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "https://api.telegram.org/bot149185899:AAGB0acLYspLNFugpu5NF4RBh5J2IwfiGq0/${method}";
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
        JSON.parse(new DataInputStream(conn.getInputStream()).readLines().join('')) as Map
    }

}
