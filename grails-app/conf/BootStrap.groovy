import org.apache.tomcat.jni.Global
import stocks.*
import stocks.tse.SymbolDailyTrade

class BootStrap {

    def dataService
    def dataStateService
    def grailsApplication

    def initGraphDBService

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
                mobile: '09122110811',
                sex: 'male',
                nationalCode: '2803348446',
                enabled: true).save(failOnError: true)

        adminUser.password = 'admin'
        adminUser.save()

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

        dataStateService.initializeStateLogging()
        if (!grailsApplication.config.jobsDisabled)
            dataService.initializeJobs()

        //check global settings
        GlobalSettingHelper.items.each {
            if(!GlobalSetting.findByName(it.name))
            {
                def setting = new GlobalSetting()
                setting.name = it.name
                setting.value = it.defaultValue
                setting.save()
            }
        }

        //init caches
//        grailsCacheManager.getCache('loadAllIndicatorValues')

//        println('--------------------------------------------')
//        println('initializing graph db')
//        initGraphDBService.init()
//        println('graph db initialization completed')
//        println('--------------------------------------------')

        TrustAllCerts.apply()
    }
    def destroy = {
    }
}
