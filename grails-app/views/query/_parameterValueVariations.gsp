<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 11/10/14
  Time: 1:12 PM
--%>

<h2><g:message code="parameterSuggestedValueVariation.header"/> ${suggestedValue.title}</h2>

<div id="grid_variation_${suggestedValue.id}"></div>
<div class="toolbar">
    <form:button onclick="goToTop_${suggestedValue.parameterId}()" text="${message(code:'goToTop')}"/>
</div>

<script language="javascript" type="text/javascript">

    function goToTop_${suggestedValue.parameterId}(){
        $('html, body').animate({
            scrollTop: $("#grid_${suggestedValue.parameterId}").offset().top
        }, 1000);
    }

    $(document).ready(function () {

        var dataSource_variation_${suggestedValue.id} = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationList', id: suggestedValue.id)}",
                    dataType: "json",
                    type: "POST"
                },
                update: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationUpdate', id: suggestedValue.id)}",
                    dataType: "json",
                    type: "POST"
                },
                destroy: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationDelete', id: suggestedValue.id)}",
                    dataType: "json",
                    type: "POST"
                },
                create: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationCreate', id: suggestedValue.id)}",
                    dataType: "json",
                    type: "POST"
                },
                parameterMap: function (options, operation) {
                    if (operation !== "read" && options.models) {
                        return {models: kendo.stringify(options.models)};
                    }
                }
            },
            batch: true,
            pageSize: 20,
            schema: {
                model: {
                    id: "id",
                    fields: {
                        id: { editable: false, nullable: true },
                        title: { validation: { required: true } }
                    }
                },
                data: "data",
                total: "total"
            }
        });
        $("#grid_variation_${suggestedValue.id}").kendoGrid({
            dataSource: dataSource_variation_${suggestedValue.id},
            pageable: true,
            height: 550,
            toolbar: ["create"],
            columns: [
                { field: "title", title: "${message(code:'parameterSuggestedValueVariation.title')}" },
                { command: ["edit", "destroy"], title: "&nbsp;", width: "200px" }
            ],
            editable: "inline"
        });

    });
</script>