package stocks.messaging

class Unsubscribe {

    String email
    Date addDate
    String addSource
    Boolean removed = false
    Date removeDate
    String removeSource


    static mapping = {
        table 'msg_unsubscribe'
    }

    static constraints = {
        removeDate nullable: true
        removeSource nullable: true
    }
}
