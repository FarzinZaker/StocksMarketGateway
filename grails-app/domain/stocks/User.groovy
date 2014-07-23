package stocks

class User {

    transient springSecurityService

    String firstName
    String lastName
    String email
    String username
    String password
    String sex
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
        sex nullable: true, inList: ['male', 'female']
        image nullable: true
    }

    static mapping = {
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
