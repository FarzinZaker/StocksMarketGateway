package stocks.tse

import fi.joensuu.joyds1.calendar.format.SimpleDateFormat
import org.apache.axis.types.UnsignedByte
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.tse.ws.old.TsePublicV2Locator
import stocks.tse.ws.old.TsePublicV2Soap_PortType

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/26/14
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TSEImportService<T> {

    protected abstract def getSampleObject()

    protected abstract String getServiceName()

    protected abstract def getParameter(int index)

    def authenticationParameters = ['ecportal.ir', 'ecportal']

    private TsePublicV2Soap_PortType service

    private TsePublicV2Soap_PortType getService() {
        if (!service) {
            service = new TsePublicV2Locator().getTsePublicV2Soap()
        }
        service
    }

    protected List<T> list(parameter) {
        getService()."${serviceName}"(*(authenticationParameters + parameter))
        def xml = getService()."${serviceName}"(authenticationParameters + parameter)
        def obj = new XmlSlurper().parseText(xml._any[1].toString())

        def domainClass = new DefaultGrailsDomainClass(sampleObject.class)
        def result = obj.children()[0].children().collect { item ->
            def object = domainClass.newInstance()

            domainClass.constrainedProperties.each { property ->
                def value
                def xmlNodeName = domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.xmlNodeName
                if (xmlNodeName) {
                    value = item[xmlNodeName]?.text()?.toString()
                } else {
                    def parameterIndex = domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.parameterIndex
                    if (parameterIndex != null) {
                        value = getParameter(parameterIndex as Integer)
                    }
                }
                if (value != null && value != '') {
                    switch (property.value.propertyType) {
                        case Integer:
                            object."${property.key}" = value?.toInteger()
                            break
                        case Long:
                            object."${property.key}" = value?.toLong()
                            break
                        case Double:
                            object."${property.key}" = value?.toDouble()
                            break
                        case Boolean:
                            object."${property.key}" = value?.toInteger() > 0
                            break
                        case Date:
                            object."${property.key}" = parseDate(
                                    domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.locale,
                                    value,
                                    item."${domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.timeXmlNode}"?.text())

                            break
                        case String:
                            object."${property.key}" = value as String
                            break
                        default:
                            object."${property.key}" = parseForeignKey(
                                    property.value.propertyType.name,
                                    domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.fkColumn,
                                    value)
                    }
                    println(value)
                }
            }

            def existingInstance = find(object as T)
            if (existingInstance) {
                existingInstance.properties.findAll { !it.key.toString().endsWith('Id') }.each {
                    if (object."${it.key}" != null)
                        existingInstance."${it.key}" = object."${it.key}"
                }
                existingInstance.modificationDate = new Date()
                existingInstance.save()
            } else {
                object.creationDate = new Date()
                object.modificationDate = new Date()
                object.save()
            }

            object
        }
        return result
    }

    protected abstract T find(T object)

    private static Date parseDate(String locale, String value, String time) {
        if (locale == 'en')
            if (time) {
                time = String.format("%06d", time as Integer);
                new java.text.SimpleDateFormat('yyyyMMdd-hhmmss').parse("${value}-${time}")
            } else
                new java.text.SimpleDateFormat('yyyyMMdd').parse(value)
        else if (locale == 'fa')
            throw new Exception('not implemented yet')
        else
            null
    }

    private static Object parseForeignKey(name, field, value) {
        def targetClass = ApplicationHolder.application.getDomainClass(name).clazz
        def fkValue = value
        switch (new DefaultGrailsDomainClass(targetClass).persistantProperties.find {
            it.name.toLowerCase() == field.toLowerCase()
        }.type) {
            case Integer:
                fkValue = value as Integer
                break
            case Long:
                fkValue = value as Long
                break
        }
        targetClass."findBy${field}"(fkValue)
    }
}