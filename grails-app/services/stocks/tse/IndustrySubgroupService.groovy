package stocks.tse

class IndustrySubgroupService extends TSEService<IndustrySubgroup> {
    @Override
    protected getSampleObject() {
        new IndustrySubgroup()
    }

    @Override
    protected IndustrySubgroup find(IndustrySubgroup object) {
        IndustrySubgroup.findByCode(object.code)
    }

    public List<IndustrySubgroup> importData(){
        importData('subSector', [[]])
    }
}
