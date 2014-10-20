package stocks.tse.persist

import stocks.tse.Board
import stocks.tse.TSEPersistService
import stocks.tse.event.BoardEvent

class BoardPersistService extends TSEPersistService<Board, BoardEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new Board()
    }

    @Override
    protected void beforeCreate(BoardEvent event) {

    }

    @Override
    protected void afterCreate(BoardEvent event, Board data) {

    }

    @Override
    protected void beforeUpdate(BoardEvent event, Board data) {

    }

    @Override
    protected void afterUpdate(BoardEvent event, Board data) {

    }
}
