package stocks.tse


class BoardService extends TSEService<Board> {

    @Override
    protected getSampleObject() {
        new Board()
    }

    @Override
    protected Board find(Board object) {
        Board.findByCode(object.code)
    }

    public List<Board> importData(){
        importData('board', [[]])
    }
}
