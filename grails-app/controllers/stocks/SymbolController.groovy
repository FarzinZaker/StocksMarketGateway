package stocks

import grails.converters.JSON
import stocks.tse.Symbol

class SymbolController {

    def info() {
        def symbol = Symbol.get(params.id as Long)

        [
                symbol: symbol

        ]

    }
}
