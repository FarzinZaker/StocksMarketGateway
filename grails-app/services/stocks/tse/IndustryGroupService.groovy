package stocks.tse

class IndustryGroupService extends TSEService<IndustryGroup> {

    @Override
    protected getSampleObject() {
        new IndustryGroup()
    }

    @Override
    protected IndustryGroup find(IndustryGroup object) {
        IndustryGroup.findByCode(object.code)
    }

    public List<IndustryGroup> importData(){
        importData('sector', [[]])
    }
}
