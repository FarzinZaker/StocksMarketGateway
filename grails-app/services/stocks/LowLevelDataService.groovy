package stocks

import com.microsoft.sqlserver.jdbc.SQLServerDriver
import groovy.sql.GroovyResultSet
import groovy.sql.Sql
import oracle.jdbc.driver.OracleTypes
import stocks.util.StringHelper

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
//            rows = sql.call(sqlCall, [rows] + parameters.collect{it.value})
            sql.call(sqlCall, [Sql.resultSet(OracleTypes.CURSOR)] + parameters.collect{it.value}) { result ->

                def resultSet = result as GroovyResultSet
                def meta = resultSet.getMetaData()
                def columns = []
                for(def i = 1; i <= meta.columnCount; i++)
                    columns << meta.getColumnName(i)

                resultSet.eachRow { row ->
//                while (resultSet.next()) {

                    def record = [:]
                    columns.each { String column ->
                        record."${StringHelper.underlineToCamel(column)}" = row."${column}"
                    }
                    rows << record

                }
            }
        } catch (Exception e) {
            println()
            "Could not execute ${sqlCall} with params ${parameters}: ${e.getMessage()} ${e.stackTrace}"
        }
        rows
    }
}
