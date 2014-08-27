package stocks

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
        out << asset.stylesheet(src: "bootstrap/datepicker/bootstrap-datepicker.css")
        out << asset.javascript(src: "bootstrap/datepicker/bootstrap-datepicker.js")
        out << asset.javascript(src: "bootstrap/datepicker/bootstrap-datepicker.fa.js")
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

            def sourceDomain = property.domainClass.constrainedProperties."${property.name}".metaConstraints.sourceDomain

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
                filter.sourceDomain = sourceDomain
                filter.sourceField = property.domainClass.constrainedProperties."${property.name}".metaConstraints.sourceField
                filter.dataUrl = createLink(controller: 'query', action: 'autoComplete', params: [sourceDomain: filter.sourceDomain, sourceField: filter.sourceField])
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
            default:
                'string'
        }
    }
}
