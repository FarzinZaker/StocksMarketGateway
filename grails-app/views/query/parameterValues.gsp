<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 11/10/14
  Time: 1:12 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="query.parameter.allowedValues.title"/> ${query.title}</title>
</head>

<body>
<div class="container-fluid" id="ngController" ng-controller="alertingRegisterController">
    <div class="row-fluid">
        <div class="col-xs-12">
            <h1><g:message code="query.parameter.allowedValues.title"/> ${query.title}</h1>

            <div class="k-rtl">
                <div id="tabstrip">
                    <ul>
                        <g:each in="${parameters}" var="parameter" status="i">
                            <li class='${i == 0 ? "k-state-active" : ''}'>
                                ${parameter.name}
                            </li>
                        </g:each>
                    </ul>

                    <g:each in="${parameters}" var="parameter">
                        <div>
                            <div id="grid_${parameter.id}"></div>

                            <div id="variationsGrid_${parameter.id}"></div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    useOldConfirm = true;
    %{--<g:each in="${parameters}" var="parameter">--}%
        %{--function onChange_${parameter.id}(arg) {--}%
        %{--var selected = $.map(this.select(), function(item) {--}%
            %{--var selectedItem = $("#grid_${parameter.id}").data("kendoGrid").dataItem(item);--}%
            %{--return selectedItem.id;--}%
        %{--});--}%

        %{--console.log(selected[0]);--}%

            %{--$.ajax({--}%
                %{--type: "POST",--}%
                %{--url: '${createLink(controller: 'parameter', action: 'parameterValueVariations')}',--}%
                %{--data: { id: selected[0] }--}%
            %{--}).done(function (response) {--}%
                %{--$('#variationsGrid_${parameter.id}').html(response);--}%
                %{--$('html, body').animate({--}%
                    %{--scrollTop: $("#variationsGrid_${parameter.id}").offset().top--}%
                %{--}, 1000);--}%
            %{--});--}%
    %{--}--}%
    %{--</g:each>--}%

    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });


        <g:each in="${parameters}" var="parameter">
        var dataSource_${parameter.id} = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueList', id: parameter.id)}",
                    dataType: "json",
                    type: "POST"
                },
                update: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueUpdate', id: parameter.id)}",
                    dataType: "json",
                    type: "POST"
                },
                destroy: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueDelete', id: parameter.id)}",
                    dataType: "json",
                    type: "POST"
                },
                create: {
                    url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueCreate', id: parameter.id)}",
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
        $("#grid_${parameter.id}").kendoGrid({
            dataSource: dataSource_${parameter.id},
            pageable: true,
            height: 550,
            toolbar: ["create"],
//            selectable: true,
            %{--change: onChange_${parameter.id},--}%
            columns: [
                { field: "title", title: "${message(code:'parameterSuggestedValue.title')}" },
                { command: { text: "${message(code:'parameterSuggestedValueVariation.button')}", click: showVariations_${parameter.id} }, title: " ", width: "110px" },
                { command: ["edit", "destroy"], title: "&nbsp;", width: "175px" }
            ],
            editable: "inline"
        });

        function showVariations_${parameter.id}(e) {
            e.preventDefault();

            var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
            console.log(dataItem.id);
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'parameter', action: 'parameterValueVariations')}',
                data: { id: dataItem.id }
            }).done(function (response) {
                $('#variationsGrid_${parameter.id}').html(response);
                $('html, body').animate({
                    scrollTop: $("#variationsGrid_${parameter.id}").offset().top
                }, 1000);
            });
        }
        </g:each>

    });
</script>

</body>
</html>