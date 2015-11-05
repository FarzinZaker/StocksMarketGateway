package stocks

import stocks.messaging.NewsLetterCategory
import stocks.social.TelegramUser

class User {

    static auditable = true

    static searchable = true

    transient springSecurityService

    String firstName
    String lastName
    String email
    String username
    String password
    String sex
    String mobile
    Broker broker
    String nationalCode
    City city
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Boolean useMobilePushNotification = false
    Image image
    String externalImageUrl
    Integer maxDept = 0
    User referer

    TelegramUser telegramUser

    static hasMany = [followList: User, newsLetterCategories: NewsLetterCategory]

    static transients = ['springSecurityService']

    static constraints = {
        firstName nullable: true
        lastName nullable: true
        username blank: false, unique: true
//        password blank: false
        sex nullable: true, inList: ['male', 'female']
        mobile nullable: true
        nationalCode nullable: true
        broker nullable: true
        useMobilePushNotification nullable: true
        image nullable: true
        city nullable: true
        externalImageUrl nullable: true
        referer nullable: true
        maxDept nullable: true
        telegramUser nullable: true
    }

    static mapping = {
        table 'usersacc'
        password column: '`password`'
        newsLetterCategories joinTable: [name: 'msg_mm_user_category', key: 'user_id', column: 'category_id']
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    @Override
    String toString() {
        "${firstName} ${lastName}"
    }
}
