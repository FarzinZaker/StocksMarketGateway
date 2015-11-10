package stocks.social

class TelegramPropertyHistory {

    String userName
    String propertyType
    String propertyName

    String date

    static mapping = {
        date column: 'dat'
    }

    static constraints = {
    }
}
