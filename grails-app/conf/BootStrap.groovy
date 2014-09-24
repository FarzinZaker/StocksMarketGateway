import stocks.*

class BootStrap {

    def dataService

    def init = { servletContext ->

        RoleHelper.ROLES.each {
            def authority = it
            Role.findByAuthority(authority) ?: new Role(authority: authority).save(failOnError: true)
        }
        def adminRole = Role.findByAuthority(RoleHelper.ROLE_ADMIN)

        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'admin',
                firstName: 'admin',
                lastName: 'admin',
                email: 'admin@local',
                sex: 'male',
                nationalCode: '2803348446',
                enabled: true).save(failOnError: true)

        adminUser.password = 'admin'
        adminUser.save()

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

        dataService.initializeJobs()

    }
    def destroy = {
    }
}
