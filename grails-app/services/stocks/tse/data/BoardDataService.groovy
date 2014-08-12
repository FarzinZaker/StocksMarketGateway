package stocks.tse.data

import stocks.tse.Board
import stocks.tse.TSEDataService
import stocks.tse.event.BoardEvent


class BoardDataService extends TSEDataService<Board, BoardEvent> {

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
