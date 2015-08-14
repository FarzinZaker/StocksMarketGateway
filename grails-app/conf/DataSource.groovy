dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {

        timeSeries {

            dataSource {
//                host = '192.168.0.3'
                host = '91.98.102.75'
                port = 8086
                username = 'root'
                password = 'root'
                db = 'stock'
            }
        }

        graph {
            dataSource {
                host = 'localhost'
                username = 'stocks'
                password = 'agah!#($'
                db = 'stocks'
            }
        }

        dataSource {
//Oracle
            dbCreate = "update"
//            url = "jdbc:oracle:thin:@192.168.0.3:1521:orcl"
            url = "jdbc:oracle:thin:@91.98.102.76:1521:CHAHARTABLO"
//            url = "jdbc:oracle:thin:@192.168.0.174:1521:CHAHARTABLO"
            username = "stocks"
            password = "stocks"
            hibernate.default_schema = "stocks"
            pooled = true
            logSql = false
            driverClassName = "oracle.jdbc.OracleDriver"
            dialect = "org.hibernate.dialect.Oracle10gDialect"
            properties {
                maxActive = -1
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                numTestsPerEvictionRun = 3
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
                validationQuery = "SELECT 1 from dual"
            }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {

        timeSeries {

            dataSource {
                host = '192.168.64.2'
                port = 8086
                username = 'root'
                password = 'root'
                db = 'stock'
            }
        }

        graph {
            dataSource {
                host = 'localhost'
                username = 'stocks'
                password = 'agah!#($'
                db = 'stocks'
            }
        }

        dataSource {

            //Oracle
            dbCreate = "update"
//            url = "jdbc:oracle:thin:@192.168.64.3:1521:CHAHARTABLO"
//            username = "stocks"
//            password = "stocks"
            hibernate.default_schema = "stocks"
//            pooled = true
//            logSql = false
//            driverClassName = "oracle.jdbc.OracleDriver"
            jndiName = "Oracle"
            dialect = "org.hibernate.dialect.Oracle10gDialect"
            properties {
                maxActive = -1
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                numTestsPerEvictionRun = 3
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
                validationQuery = "SELECT 1 from dual"
            }

        }
    }
}
