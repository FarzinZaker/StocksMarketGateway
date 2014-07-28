package stocks.tse

class IndustrySubgroupAutoImportService extends TSEAutoImportService<IndustrySubgroup> {
    @Override
    protected getSampleObject() {
        new IndustrySubgroup()
    }

    @Override
    protected String getServiceName() {
        'subSector'
    }

    @Override
    protected ArrayList getParameters() {
        [[]]
    }

    @Override
    protected IndustrySubgroup find(IndustrySubgroup object) {
        IndustrySubgroup.findByCode(object.code)
    }
}
