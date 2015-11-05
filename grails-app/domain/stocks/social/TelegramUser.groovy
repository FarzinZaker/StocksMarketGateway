package stocks.social

import stocks.User

class TelegramUser {

    User user
    Long identifier
    String firstName
    String lastName
    String userName
    Long chatId

    static constraints = {
        identifier nullable: true
        firstName nullable: true
        lastName nullable: true
        userName nullable: true
    }
}
