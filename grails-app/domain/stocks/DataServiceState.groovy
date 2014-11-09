package stocks

class DataServiceState {

    static auditable = true

    String serviceName
    String data
    Boolean isLastState = true

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alerting_data_service_state'
        data type: 'text'
    }

    static constraints = {
    }
}
