package stocks

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
    Image image

    static hasMany = [followList: User]

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true
        password blank: false
        sex inList: ['male', 'female']
        broker nullable: true
        image nullable: true
        city nullable: true
    }

    static mapping = {
        table 'usersacc'
        password column: '`password`'
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
