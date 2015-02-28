package stocks

import com.microsoft.sqlserver.jdbc.SQLServerDriver
import groovy.sql.Sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Timestamp

class LowLevelDataService {

    def grailsApplication

    def executeStoredProcedure(String spName, Map parameters) {
//        println Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        DriverManager.registerDriver(new SQLServerDriver());
        Properties properties = new Properties();
        properties.put("user", grailsApplication.config.dataSource.username);
        properties.put("password", grailsApplication.config.dataSource.password);
        def connection = DriverManager.getConnection(grailsApplication.config.dataSource.url, properties);
        def sql = new Sql(connection)

        def sqlCall = "exec ${spName} ${parameters.collect { ":${it.key}" }.join(', ')}"
        log.info "Running: $sqlCall with params $parameters"
        def rows = []
        try {
            rows = sql.rows(sqlCall, parameters)
        } catch (Exception e) {
            println() "Could not execute ${sqlCall} with params ${parameters}: ${e.getMessage()} ${e.stackTrace}"
        }
//        connection.close()
//        println rows
        rows
    }
}
