package stocks

import stocks.alerting.*
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class AlertingTagLib {

    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "alerting"

    def grailsApplication

    def domainClassList = { attrs, body ->
        def domainClassList = grailsApplication.getArtefacts("Domain").findAll {
            it.packageName in ['stocks.tse', 'stocks.codal'] && it.hasProperty('notify') && it.newInstance()?.notify
        }
        out << form.select(
                name: "domainClass",
                items: domainClassList.collect {
                    [
                            text : message(code: "${it.fullName}.label"),
                            value: it.fullName
                    ]
                },
                validation: "required",
                style: "width:500px;")
    }

    def queryBuilder = { attrs, body ->

        out << asset.stylesheet(src: "query-builder/query-builder.css")
        out << "<link rel='stylesheet' type='text/css' href='${resource(dir: "css/bootstrap/datepicker", file: "bootstrap-datepicker.css")}'/>"
        out << "<script language='javascript' type='text/javascript' src='${resource(dir: "js/bootstrap/datepicker", file: "bootstrap-datepicker.js")}'></script>"
        out << "<script language='javascript' type='text/javascript' src='${resource(dir: "js/bootstrap/datepicker", file: "bootstrap-datepicker.fa.js")}'></script>"
        out << asset.javascript(src: "query-builder/query-builder.js")
        out << asset.javascript(src: "query-builder/i18n/fa.js")

        out << """
            <script language="javascript" type="text/javascript">
                function setQueryElementsStyle(){
                    \$('#${attrs.id ?: attrs.name}.query-builder select:not(.furnished)').kendoComboBox({
                        change : function (e) {
                            if (this.value() && this.selectedIndex == -1) {
                                var dt = this.dataSource._data[0];
                                this.text(dt[this.options.dataTextField]);
                                this._selectItem();
                            }
                        }
                    });
                    \$('#${attrs.id ?: attrs.name}.query-builder select').addClass('furnished').change(function(){
                        \$(this).data("kendoComboBox").select(\$(this).find('option:selected').index());
                    });
                }
            </script>

            <div id="${attrs.id ?: attrs.name}"></div>
"""

        DefaultGrailsDomainClass domainClass = grailsApplication.getDomainClass(attrs.domainClassName)

        def options = [:]
        options.sortable = true
        options.filters = []
        domainClass.persistentProperties.findAll {
            it.domainClass.constrainedProperties."${it.name}".metaConstraints.query
        }.each { property ->

            def sourceDomain = property.domainClass.constrainedProperties."${property.name}".metaConstraints.query instanceof Boolean ? null : property.domainClass.constrainedProperties."${property.name}".metaConstraints.query?.domain

            def filter = [:]

            filter.id = property.name
            filter.label = message code: "${domainClass.fullName}.${property.name}.label"
            filter.type = sourceDomain ? 'domain' : formatFieldType(property.type)

            if (property.type == Date) {
                filter.validation = [format: 'YYYY/MM/DD']
                filter.plugin = 'datepicker'
                filter.plugin_config = [
                        format        : 'yyyy/mm/dd',
                        todayBtn      : 'linked',
                        todayHighlight: true,
                        autoclose     : true,
                        language      : 'fa'
                ]
            }

            if (filter.type == 'domain') {
                filter.domain = property.domainClass.constrainedProperties."${property.name}".metaConstraints.query?.domain?.name
                filter.field = "${property.name}"
                filter.dataUrl = createLink(controller: 'query', action: 'autoComplete', params: [domain: property.domainClass.fullName, field: property.name])
            }

            options.filters << filter
        }

        out << form.hidden(name: 'query')

        out << """
            <script language="javascript" type="text/javascript">
                \$('#${attrs.id ?: attrs.name}').queryBuilder(
                    ${options as JSON}
                );

                \$(document).ready(function () {
                    var formElement = \$('#${attrs.id ?: attrs.name}').parent();
                    while (formElement.get(0).tagName != 'FORM')
                        formElement = formElement.parent();
                    formElement.find('input[type=submit]').click(function () {
                        \$('#query').val(JSON.stringify(
                                \$('#${attrs.id ?: attrs.name}').queryBuilder('getRules'),
                                undefined, 2
                        ));
                    });
"""
        if (attrs.value)
            out << """
                    \$('#${attrs.id ?: attrs.name}').queryBuilder('setRules', ${attrs.value as JSON});
"""
        out << """

                });
            </script>
"""
    }

    def formatFieldType(Class type) {
        switch (type) {
            case String:
                'string'
                break
            case Integer:
                'integer'
                break
            case Long:
                'long'
                break
            case Date:
                'date'
                break
            case Double:
                'double'
                break
            case Float:
                'float'
                break
            default:
                'string'
        }
    }

    def parameterFieldList = { attrs, body ->
        def query = Query.get(attrs.queryId)
        def queryInstance = QueryInstance.get(attrs.queryInstanceId) ?: new QueryInstance([query: query])
        if (!query)
            query = queryInstance?.query
        def parameters = Parameter.findAllByQuery(query)
        parameters.each { parameter ->
            out << render(template: '/query/register/parameter', model: [parameter: parameter, queryInstance: queryInstance])
        }
    }

    def parameterField = { attrs, body ->
        def parameter = attrs.parameter as Parameter
        def queryInstance = attrs.queryInstace as QueryInstance
        def suggestedValues = ParameterSuggestedValue.findAllByParameter(parameter)
        if (['integer', 'string'].contains(parameter.type)) {
            out << render(template: "/query/register/parameters/${parameter.type}", model: [queryInstance: queryInstance, parameter: parameter, suggestedValues: suggestedValues])
        } else {

            def domainClass = grailsApplication.getDomainClass(queryInstance.query.domainClazz)
            def property = domainClass.persistentProperties.find { it.name == parameter.type }
            def queryOptions = domainClass.constrainedProperties."${property.name}".metaConstraints.query
            if (queryOptions.deliveryMethods) {
                out << "<div class='pushButtonRadioGroup' id='deliveryMethods_${parameter.id}'>"
                def indexer = 1
                queryOptions.deliveryMethods.each { deliveryMethod ->
                    out << g.radio(name: "deliveryMethod_${parameter.id}", onchange: "setDeliveryMethodPagesVisibility_${parameter.id}()", id: "deliveryMethod_${parameter.id}_${deliveryMethod.name}", 'aria-relevant': "deliveryMethodPage_${parameter.id}_${deliveryMethod.name}", 'data-deliveryMethod': deliveryMethod.name, value: deliveryMethod.name, checked: indexer++ == 1)
                    out << "<label for='deliveryMethod_${parameter.id}_${deliveryMethod.name}'>${message(code: "${domainClass.fullName}.${property.name}.deliveryMethods.${deliveryMethod.name}")}</label>"
                }
                out << "</div>"

                queryOptions.deliveryMethods.each { deliveryMethod ->
                    out << "<div class='deliveryMethodPage' id='deliveryMethodPage_${parameter.id}_${deliveryMethod.name}'>"
                    if (deliveryMethod.type != DeliveryMethods.SPECIAL)
                        out << render(template: "/query/register/parameters/domain/${deliveryMethod.type}", model: [queryInstance: queryInstance, parameter: parameter, suggestedValues: suggestedValues, deliveryMethod: deliveryMethod, domainClass: domainClass, property: property])
                    else
                        out << render(template: "/query/register/parameters/domain/special/${deliveryMethod.name}", model: [queryInstance: queryInstance, parameter: parameter, suggestedValues: suggestedValues, deliveryMethod: deliveryMethod, domainClass: domainClass, property: property])
                    out << "</div>"

                    out << """
                    <script language='javascript' type='text/javascript'>
                        function setDeliveryMethodPagesVisibility_${parameter.id}(){
                            \$('#deliveryMethods_${parameter.id}').find('input[type=radio]').each(function(){
                                if(\$(this).is(':checked')){
                                    currentDeliveryMethod_${parameter.id} = \$(this).attr('data-deliveryMethod');
                                    \$('#' + \$(this).attr('aria-relevant')).css('display', 'inline-block');
                                    if(\$('#' + \$(this).attr('aria-relevant')).find('.underConstruction').length > 0){
                                        \$('#' + \$(this).attr('aria-relevant')).parent().find('.k-button').css('display','none');
                                    }
                                    else{
                                        \$('#' + \$(this).attr('aria-relevant')).parent().find('.k-button').css('display','inline-block');
                                    }
                                }
                                else{
                                    \$('#' + \$(this).attr('aria-relevant')).css('display', 'none');
                                }
                            });
                        }
                        \$(document).ready(function(){
                            setDeliveryMethodPagesVisibility_${parameter.id}();
                        });
                    </script>
"""
                }

                out << render(template: '/query/register/parameters/domain/gateway', model: [queryInstance: queryInstance, parameter: parameter, suggestedValues: suggestedValues, domainClass: domainClass, property: property])
            }
        }
//        if (parameter.multiSelect)
            out << render(template: "/query/register/parameters/multiSelect", model: [parameter: parameter])
    }

    def parameterDefaultValueText = {attrs, body ->

        def parameter = attrs.parameter as Parameter
        def domainClass = grailsApplication.getDomainClass(parameter.query.domainClazz)
        def property = domainClass.persistentProperties.find { it.name == parameter.type }
        def queryOptions
        if(property)
            queryOptions = domainClass.constrainedProperties."${property?.name}".metaConstraints.query
        if(!queryOptions || queryOptions instanceof Boolean)
            out << parameter.defaultValue
        else{
            def sourceDomainClass = grailsApplication.getDomainClass(queryOptions.domain.name)
            def valueFieldName =  queryOptions.value.toCharArray()
            valueFieldName[0] = queryOptions.value.toUpperCase()[0]
            def item = sourceDomainClass.clazz."findBy${valueFieldName.toString()}"(parameter.defaultValue)
            if(item)
                out << queryOptions.display(item)
            else
                out << parameter.defaultValue
        }

    }

    def parameterDefaultValueValue = {attrs, body ->
        def parameter = attrs.parameter as Parameter
        out << parameter.defaultValue
    }

    def parameterDefaultValueType = {attrs, body ->

        def parameter = attrs.parameter as Parameter
        def domainClass = grailsApplication.getDomainClass(parameter.query.domainClazz)
        def property = domainClass.persistentProperties.find { it.name == parameter.type }
        def queryOptions
        if(property)
        queryOptions = domainClass.constrainedProperties."${property?.name}".metaConstraints.query
        if(!queryOptions || queryOptions instanceof Boolean)
            out << 'const'
        else{
            out << "${property.name}-search"
        }
    }
}