import stocks.tse.AdjustmentHelper

class GlobalParameterFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
//                println('global parameters filter')
                AdjustmentHelper.initGlobalAdjustmentType()
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
