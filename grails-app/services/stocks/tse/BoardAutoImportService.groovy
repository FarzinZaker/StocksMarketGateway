package stocks.tse

class BoardAutoImportService extends TSEAutoImportService<Board> {
    @Override
    protected def getSampleObject() {
        new Board()
    }

    @Override
    protected String getServiceName() {
        'board'
    }

    @Override
    protected ArrayList getParameters() {
        [[]]
    }

    @Override
    protected Board find(Board object) {
        Board.findByCode(object.code)
    }
}
