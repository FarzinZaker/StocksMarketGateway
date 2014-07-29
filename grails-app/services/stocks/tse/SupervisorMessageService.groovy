package stocks.tse

class SupervisorMessageService extends TSEService<SupervisorMessage> {
    @Override
    protected getSampleObject() {
        new SupervisorMessage()
    }

    @Override
    protected SupervisorMessage find(SupervisorMessage object) {
        SupervisorMessage.findByTitle(object.title)
    }

    public List<SupervisorMessage> importData(){
        importData('msg', [[]])
    }
}
