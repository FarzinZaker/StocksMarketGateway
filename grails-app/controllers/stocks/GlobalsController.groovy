package stocks

import stocks.tse.AdjustmentHelper

class GlobalsController {

    def adjustmentType() {
        AdjustmentHelper.setGlobalAdjustmentType(params.id as String)
        render 1
    }

}
