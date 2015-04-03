grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
        // excludes 'grails-plugin-log4j'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        mavenRepo "http://repo.grails.org/grails/repo/"
        mavenRepo "http://repository.codehaus.org/"
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://repo.grails.org/grails/core" // https://jira.grails.org/browse/GPSEARCHABLE-224
        mavenRepo "https://code.lds.org/nexus/content/groups/main-repo"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        compile('com.oracle:ojdbc6:11.2.0.3')

//        runtime 'mysql:mysql-connector-java:5.1.29'
//        runtime 'org.mariadb.jdbc:mariadb-java-client:1.1.7'
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
//        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"

        compile "javax.media:jai-core:1.1.3"
        compile "com.sun.media:jai-codec:1.1.3"
        compile "net.java.dev.jai-imageio:jai-imageio-core-standalone:1.2-pre-dr-b04-2013-04-23"

//        compile 'org.axonframework:axon-core:2.2'
//        runtime 'com.thoughtworks.xstream:xstream:1.4.7'

        compile 'org.apache.axis:axis:1.4'
        compile 'javax.xml:jaxrpc-api:1.1'
        compile 'commons-discovery:commons-discovery:0.4'
        compile 'org.ccil.cowan.tagsoup:tagsoup:1.2'

        build 'joda-time:joda-time:2.3'
        build 'joda-time:joda-time-hibernate:1.3'

        compile 'commons-httpclient:commons-httpclient:3.1'
        compile 'org.jsoup:jsoup:1.8.1'

        compile 'com.tictactec:ta-lib:0.4.0'

        compile 'org.codehaus.gpars:gpars:1.2.1'

        compile 'com.sun.jersey:jersey-bundle:1.1.5.1'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:$grailsVersion"

        // plugins for the compile step
////        compile ":scaffolding:2.1.1"
//        compile ":cache:1.1.6"
//        compile ":cache-ehcache:1.0.1"
        compile ":asset-pipeline:1.8.11"

        // plugins needed at runtime but not for compilation
//        runtime ":hibernate:3.6.10.16"// or":hibernate4:4.3.5.4"\
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.11.1"

        // Uncomment these to enable additional asset-pipeline capabilities
        //compile ":sass-asset-pipeline:1.7.4"
        compile ":less-asset-pipeline:1.7.0"
        compile ":coffee-asset-pipeline:1.7.0"
//        //compile ":handlebars-asset-pipeline:1.3.0.3"
//
        compile ":spring-security-core:1.2.7.3"
        compile ":rest:0.8"
        compile ":searchable:0.6.8"
        compile ":simple-captcha:0.9.9"
        compile ":mail:1.0.6"

        compile ":quartz:0.4.1"

        compile ":audit-logging:1.0.3"
    }
}
