package stocks.tse

class SupervisorMessageAutoImportService extends TSEAutoImportService<SupervisorMessage> {
    @Override
    protected getSampleObject() {
        new SupervisorMessage()
    }

    @Override
    protected String getServiceName() {
        'msg'
    }

    @Override
    protected ArrayList getParameters() {
        [[]]
    }

    @Override
    protected SupervisorMessage find(SupervisorMessage object) {
        SupervisorMessage.findByTitle(object.title)
    }
}
