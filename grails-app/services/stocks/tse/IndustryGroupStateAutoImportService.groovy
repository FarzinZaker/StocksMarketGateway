package stocks.tse

class IndustryGroupStateAutoImportService extends TSEAutoImportService<IndustryGroupState> {
    @Override
    protected getSampleObject() {
        new IndustryGroupState()
    }

    @Override
    protected String getServiceName() {
        'sectorState'
    }

    @Override
    protected ArrayList getParameters() {
        [[new Date().format("yyyyMMdd") as Integer]]
    }

    @Override
    protected IndustryGroupState find(IndustryGroupState object) {
        IndustryGroupState.findByIndustryGroupIdentifierAndDate(object.industryGroupIdentifier, object.date)
    }
}
