package stocks

import com.microsoft.sqlserver.jdbc.SQLServerDriver
import groovy.sql.Sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Timestamp
import org.hibernate.SessionFactory

class LowLevelDataService {

    def grailsApplication
    SessionFactory sessionFactory

    Connection getConnection(){
        sessionFactory.currentSession.connection()
    }

    void executeStoredProcedure(String spName, Map parameters) {

        def sql = new Sql(connection)

        def sqlCall = "{call ${spName}(${parameters.collect { "?" }.join(', ')})}"
        try {
            sql.call(sqlCall , parameters.collect{it.value})
        } catch (Exception e) {
            println()
            "Could not execute ${sqlCall} with params ${parameters}: ${e.getMessage()} ${e.stackTrace}"
        }
    }
    def executeFunction(String spName, Map parameters) {

        def sql = new Sql(connection)

        def sqlCall = "{? = call ${spName}(${parameters.collect { "?" }.join(', ')})}"
        def rows = []
        try {
            rows = sql.call(sqlCall, [rows] + parameters.collect{it.value})
        } catch (Exception e) {
            println()
            "Could not execute ${sqlCall} with params ${parameters}: ${e.getMessage()} ${e.stackTrace}"
        }
        rows
    }
}
