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
        dataSource {
// mysql
//            dbCreate = "update"
//            url = "jdbc:mysql://localhost/stocks?useUnicode=true&characterEncoding=UTF-8"
//            username = "root"
//            password = ""
//            pooled = true
//            logSql = false
//            driverClassName = "com.mysql.jdbc.Driver"
//            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis = 1800000
//                timeBetweenEvictionRunsMillis = 1800000
//                numTestsPerEvictionRun = 3
//                testOnBorrow = true
//                testWhileIdle = true
//                testOnReturn = true
//                validationQuery = "SELECT 1"
//            }

// mariadb
//            dbCreate = "update"
//            url = "jdbc:mariadb://localhost:3306/Stocks"
//            driverClassName = "org.mariadb.jdbc.Driver"
//            username = "root"
//            password = "Salam123"
//            pooled = true
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis=1800000
//                timeBetweenEvictionRunsMillis=1800000
//                numTestsPerEvictionRun=3
//                testOnBorrow=true
//                testWhileIdle=true
//                testOnReturn=true
//                validationQuery="SELECT 1"
//            }

// SQL Server
//            dbCreate = "update"
//            username = "sa"
//            password = "Cube1234"
//            logSql = false
//            url = "jdbc:sqlserver://192.168.0.30:1433;databaseName=Stocks"
//
//            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
//            dialect = "org.hibernate.dialect.SQLServerDialect"
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis = 1800000
//                timeBetweenEvictionRunsMillis = 1800000
//                numTestsPerEvictionRun = 3
//                testOnBorrow = true
//                testWhileIdle = true
//                testOnReturn = true
//                validationQuery = "SELECT 1"
//            }

//Oracle
            dbCreate = "update"
            url = "jdbc:oracle:thin:@192.168.0.3:1521:orcl"
            username = "stocks"
            password = "stocks"
            hibernate.default_schema = "stocks"
            pooled = true
            logSql=false
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
        dataSource {
//            dbCreate = "update"
//            url = "jdbc:mysql://localhost/stocks?useUnicode=true&characterEncoding=UTF-8"
//            username = "root"
//            password = ""
//            pooled = true
//            logSql = false
//            driverClassName = "com.mysql.jdbc.Driver"
//            dialect = "org.hibernate.dialect.MySQLMyISAMDialect"
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis = 1800000
//                timeBetweenEvictionRunsMillis = 1800000
//                numTestsPerEvictionRun = 3
//                testOnBorrow = true
//                testWhileIdle = true
//                testOnReturn = true
//                validationQuery = "SELECT 1"
//            }
//            username = "sa"
//            password = "Salam123"
//            url = "jdbc:sqlserver://192.168.52.42:1433;databaseName=Stocks"
//
//            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
//            dialect = "org.hibernate.dialect.SQLServerDialect"
//            properties {
//                maxActive = -1
//                minEvictableIdleTimeMillis = 1800000
//                timeBetweenEvictionRunsMillis = 1800000
//                numTestsPerEvictionRun = 3
//                testOnBorrow = true
//                testWhileIdle = true
//                testOnReturn = true
//                validationQuery = "SELECT 1"
//            }

            //Oracle
            dbCreate = "update"
            url = "jdbc:oracle:thin:@192.168.64.3:1521:CHAHARTABLO"
            username = "stocks"
            password = "stocks"
            hibernate.default_schema = "stocks"
            pooled = true
            logSql=false
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
}
