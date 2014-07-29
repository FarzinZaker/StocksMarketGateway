package stocks.tse

class IndustryGroupStateService extends TSEService<IndustryGroupState> {
    @Override
    protected getSampleObject() {
        new IndustryGroupState()
    }

    @Override
    protected IndustryGroupState find(IndustryGroupState object) {
        IndustryGroupState.findByIndustryGroupIdentifierAndDate(object.industryGroupIdentifier, object.date)
    }

    public List<IndustryGroupState> importData(){
        importData('sectorState', [[(new Date() - 1).format("yyyyMMdd") as Integer]])
    }
}
