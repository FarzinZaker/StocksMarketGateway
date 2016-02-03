package stocks.rental

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.feed.ExternalNews
import stocks.social.RentalTelegramBot
import stocks.rate.*

class TelegramBotController {

    def key() {
        def bot = RentalTelegramBot.get(params.id)
        if (bot && bot.enabled) {
            render bot.key
        } else {
            render ""
        }
    }

    def news() {
        render(ExternalNews.createCriteria().list {
            eq('category', params.category)
            maxResults(3);
            order('date', ORDER_DESCENDING)
            projections {
                property('link')
            }
        } as JSON)
    }

    def rates() {

        def result = []
        switch (params.type) {
            case 'currency':
//                ['us-dollar-exchange', 'euro', 'gbp', 'aed', 'lear-turkey', 'chinese-yuan', 'iraq-dinar']
                params.items.toString().split('|').each {
                    Currency item = Currency.findBySymbol(it)
                    def percent = (Math.round(item.change * 10000 / (item?.price - item?.change)) as double)  / 100
                    result << [
                            name: item.name,
                            price: g.formatNumber(number: item?.price, type: 'number'),
                            high: g.formatNumber(number: item?.high, type: 'number'),
                            low: g.formatNumber(number: item.low, type: 'number'),
                            change: formatChange(item.change),
                            percent: formatChangePercent(percent),
                            date: jalaliDate(item?.modificationDate),
                            unit: message(code: 'rial')
                    ]
                }
                break
            case 'gold':
//                ['ons', 'n-coin', 'o-coin', 'h-coin', 'q-coin', 'geram18']
                params.items.toString().split('|').each {
                    def item = Coin.findBySymbol(it)
                    def percent = (Math.round(item.change * 10000 / (item?.price - item?.change)) as double)  / 100
                    result << [
                            name: item.name,
                            price: g.formatNumber(number: item?.price, type: 'number'),
                            high: g.formatNumber(number: item?.high, type: 'number'),
                            low: g.formatNumber(number: item.low, type: 'number'),
                            change: formatChange(item.change),
                            percent: formatChangePercent(percent),
                            date: jalaliDate(item?.modificationDate),
                            unit: ['ons'].contains(it) ? message(code: 'dollar') : message(code: 'rial')
                    ]
                }
                break
            case 'metal':
//                ['copper', 'aluminium', 'nickel', 'tin', 'zinc', 'cobalt']
                params.items.toString().split('|').each {
                    def item = Metal.findBySymbol(it)
                    def percent = (Math.round(item.change * 10000 / (item?.price - item?.change)) as double)  / 100
                    result << [
                            name: item.name,
                            price: g.formatNumber(number: item?.price, type: 'number'),
                            high: g.formatNumber(number: item?.high, type: 'number'),
                            low: g.formatNumber(number: item.low, type: 'number'),
                            change: formatChange(item.change),
                            percent: formatChangePercent(percent),
                            date: jalaliDate(item?.modificationDate),
                            unit: message(code: 'dollar')
                    ]
                }
                break
            case 'oil':
//                ['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex']
                params.items.toString().split('|').each {
                    def item = Oil.findBySymbol(it)
                    def percent = (Math.round(item.change * 10000 / (item?.price - item?.change)) as double)  / 100
                    result << [
                            name: item.name,
                            price: g.formatNumber(number: item?.price, type: 'number'),
                            high: g.formatNumber(number: item?.high, type: 'number'),
                            low: g.formatNumber(number: item.low, type: 'number'),
                            change: formatChange(item.change),
                            percent: formatChangePercent(percent),
                            date: jalaliDate(item?.modificationDate),
                            unit: item.unit
                    ]
                }
                break
        }
        render(result as JSON)
    }

    private static jalaliDate = { Date date ->
        def cal = Calendar.getInstance()
        cal.setTime(date)

        def result = ''
        def jc = new JalaliCalendar(cal)
        result += String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        result += ' '
        result += String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
        result
    }

    private formatChange = {Double value ->
        if(value == 0D)
            return ''
        def result = g.formatNumber(number: value, type: 'number')?.toString()
        if(result.contains('-'))
            result = result.replace('-', '') + '-'
        result
    }

    private formatChangePercent = {Double value ->
        if(value == 0D)
            return ''
        def result = g.formatNumber(number: value, type: 'number')?.toString()
        if(result.contains('-'))
            result = result.replace('-', '') + '-'
        result = '%' + result

        if(value < 0)
            result += '   ⬇️'

        if(value > 0)
            result += '   ⬆️'

        result
    }
}
