package stocks.social

class TelegramCommandHistory {

    String userName
    String firstCommand
    String secondCommand

    static constraints = {
        firstCommand nullable:  true
        secondCommand nullable: true
    }
}
