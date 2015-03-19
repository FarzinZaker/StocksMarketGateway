package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Index
import stocks.tse.IndexSymbol
import stocks.tse.TSEDataService
import stocks.tse.event.IndexSymbolEvent

class IndexSymbolDataService extends TSEDataService<IndexSymbol, IndexSymbolEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 60000l, startDelay: 60000]
                    ]
//                    trigger: [
//                            type      : 'Cron',
//                            parameters: [cronExpression: '0 20 1 * * ?']
//                    ]
            ]
    ]

    @Override
    protected IndexSymbolEvent getSampleEventObject() {
        new IndexSymbolEvent()
    }

    @Override
    protected IndexSymbol find(IndexSymbolEvent object) {
        IndexSymbol.findByIndexCodeAndSymbolInternalCodeAndDate(object.indexCode, object.symbolInternalCode, object.date)
    }

    public void importData() {

        def parameters = []
        Index.createCriteria().listDistinct {
            projections {
                property('internalCode')
            }
        }.each { index ->
            (0..5).each { market ->
                parameters << [index, new UnsignedByte(market as String)]
            }
        }

        importData('indexInstrument', parameters)
    }
}
