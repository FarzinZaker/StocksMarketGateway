
user.files.imagesPath = "/Personal/DevDesk/Stocks-Files/images"
codal.filesPath = "/Personal/DevDesk/Stocks-Files/codal"

grails.views.gsp.encoding="UTF-8"
grails.assets.less.compiler = 'less4j'

grails.assets.bundle=false
grails.assets.excludes = [
        "application.js",
        "angular*.js",
//        "kendo.ui/*.*",
//        "bootstrap/*.*"
]


// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'stocks.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'stocks.UserRole'
grails.plugins.springsecurity.authority.className = 'stocks.Role'

grails.plugins.springsecurity.successHandler.defaultTargetUrl = '/'
grails.plugins.springsecurity.successHandler.alwaysUseDefault = true

grails.plugins.springsecurity.providerNames = [
        'authenticationProvider',
// 'daoAuthenticationProvider',
        'anonymousAuthenticationProvider',
        'rememberMeAuthenticationProvider']




grails.gorm.default.mapping = {
    autoTimestamp true //or false based on your need
}

quartz {
    waitForJobsToCompleteOnShutdown = false
    props {

        scheduler.makeSchedulerThreadDaemon = true
        org.quartz.scheduler.makeSchedulerThreadDaemon=true

    }
}