package stocks.tse

class SymbolBestRequestManualImportService extends TSEManualImportService<SymbolBestRequest> {
    @Override
    protected getSampleObject() {
        new SymbolBestRequest()
    }

    @Override
    protected String getServiceName() {
        'bestLimitOneIns'
    }

    @Override
    protected SymbolBestRequest find(SymbolBestRequest object) {
        SymbolBestRequest.findBySymbolInternalCodeAndNumber(object.symbolInternalCode, object.number)
    }

    public List<SymbolBestRequest> execute(Long symbolInternalCode){
        execute([symbolInternalCode])
    }
}
