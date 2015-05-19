import stocks.tse.AdjustmentHelper

class GlobalParameterFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                AdjustmentHelper.initGlobalAdjustmentType()
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
