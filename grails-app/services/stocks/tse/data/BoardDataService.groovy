package stocks.tse.data

import stocks.tse.Board
import stocks.tse.TSEDataService
import stocks.tse.event.BoardEvent


class BoardDataService extends TSEDataService<Board, BoardEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 300000l, startDelay: 60000]
                    ]
//                    trigger: [
//                            type      : 'Cron',
//                            parameters: [cronExpression: '0 0 1 * * ?']
//                    ]
            ]
    ]

    @Override
    protected BoardEvent getSampleEventObject() {
        new BoardEvent()
    }

    @Override
    protected Board find(BoardEvent object) {
        Board.findByCode(object.code)
    }

    public void importData(){
        importData('board', [[]])
    }
}
