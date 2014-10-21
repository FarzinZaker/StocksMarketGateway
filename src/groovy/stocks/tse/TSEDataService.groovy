package stocks.tse

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.DataServiceState
import stocks.FarsiNormalizationFilter
import stocks.tse.ws.TsePublicV2Soap_BindingStub
import stocks.tse.ws.TsePublicV2Locator
import stocks.tse.ws.TsePublicV2Soap_PortType

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/26/14
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TSEDataService<T, K> {

    def tseEventGateway

    private def authenticationParameters = ['ecportal.ir', 'ecportal']

    private TsePublicV2Soap_PortType service

    private TsePublicV2Soap_PortType getService() {
        if (!service) {
            TsePublicV2Locator fooLocator = new TsePublicV2Locator();
            fooLocator.getEngine().setOption("sendMultiRefs", false);
            service = fooLocator.getTsePublicV2Soap();

            def binding = (TsePublicV2Soap_BindingStub) service;
            binding.setTimeout(120000);
        }
        service
    }

    protected abstract K getSampleEventObject();

    protected void importData(String serviceName, List<List> parameters) {
        tseEventGateway.open(sampleEventObject, serviceName, parameters)
        def list = []
        parameters.each { parameter ->
            list.addAll(parseData(serviceName, parameter))
        }
        tseEventGateway.close(sampleEventObject, serviceName, parameters, list)
    }

    private def parseData(String serviceName, List parameter) {
        try {
//            getService()."${serviceName}"(*(authenticationParameters + parameter))
            def xml = getService()."${serviceName}"(authenticationParameters + parameter)
            def obj = new XmlSlurper().parseText(xml._any[1].toString())

            def domainClass = new DefaultGrailsDomainClass(getSampleEventObject().class)

            def list = []
            obj.children()[0].children().each { item ->
                def object = domainClass.newInstance()

                domainClass.constrainedProperties.each { property ->
                    def value
                    def xmlNodeName = domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.xmlNodeName
                    if (xmlNodeName) {
                        value = item[xmlNodeName]?.text()?.toString()
                    } else {
                        def parameterIndex = domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.parameterIndex
                        if (parameterIndex != null) {
                            value = parameter[parameterIndex as Integer]
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
                                object."${property.key}" = FarsiNormalizationFilter.apply((value as String)?.trim())
                                break
                            default:
                                object."${property.key}" = parseForeignKey(
                                        property.value.propertyType.name,
                                        domainClass?.constrainedProperties?."${property.key}"?.metaConstraints?.fkColumn,
                                        FarsiNormalizationFilter.apply(value as String))
                        }
//                    println(value)
                    }
                }

                object.data = find(object as K)
                list << tseEventGateway.send(object)
            }
            logState([status: 'successful'])
            list
        }
        catch (ex) {
            logState([status: 'failed', message: ex.message, stackTrace: ex.stackTrace])
            []
        }
    }

    protected abstract T find(K object)

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

    protected static Date parseDate(String locale, String value, String time) {
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
}