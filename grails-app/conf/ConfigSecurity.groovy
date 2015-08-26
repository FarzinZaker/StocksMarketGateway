
// Added by the Spring Security Core plugin:
//grails.plugin.springsecurity.logout.postOnly = false
//grails.plugin.springsecurity.securityConfigType = 'Annotation'
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//        '/': ["permitAll"],
//        '/assets/**': ["permitAll"],
//        '/raty/**': ["permitAll"],
//        '/index': ["permitAll"],
//        '/index.gsp': ["permitAll"],
//        '/**/js/**': ["permitAll"],
//        '/**/css/**': ["permitAll"],
//        '/**/images/**': ["permitAll"],
//        '/**/favicon.ico': ["permitAll"],
////        '/test/**': ["permitAll"],
////        '/admin/**': ["hasRole('ADMIN')"],
////        '/chart/**': ["permitAll"],
////        '/article/**': ["permitAll"],
////        '/news/**': ["permitAll"],
//        '/image/**': ["permitAll"],
//        '/file/**': ["permitAll"],
////        '/tag/**': ["permitAll"],
////        '/rate/**': ["permitAll"],
////        '/document/**': ["permitAll "],
////        '/user/**': ["permitAll "]
//]




grails.plugin.console.enabled=true
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        "/console/**":          ['ROLE_ADMIN'],
        "/plugins/console*/**": ['ROLE_ADMIN']
]