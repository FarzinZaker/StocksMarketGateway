package stocks

class DataServiceState {

    String serviceName
    String data
    Boolean isLastState = true

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alerting_data_service_state'
        data type: 'text'
        serviceName index: 'idx_service_lastState'
        isLastState index: 'idx_service_lastState'
    }

    static constraints = {
    }
}
