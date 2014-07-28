package stocks.tse

class IndustryGroupAutoImportService extends TSEAutoImportService<IndustryGroup> {

    @Override
    protected getSampleObject() {
        new IndustryGroup()
    }

    @Override
    protected String getServiceName() {
        'sector'
    }

    @Override
    protected ArrayList getParameters() {
        [[]]
    }

    @Override
    protected IndustryGroup find(IndustryGroup object) {
        IndustryGroup.findByCode(object.code)
    }
}
