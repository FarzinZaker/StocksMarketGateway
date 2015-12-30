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
<div class="container" id="ngController" ng-controller="alertingRegisterController">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.newsletters'), url: createLink(controller: 'query')],
                    [text: query?.title, url: createLink(controller: 'query', action: 'build', id: query?.id)],
                    [text: '<i class="fa fa-paper-plane-o"></i> ' + message(code: 'query.parameter.allowedValues.breadcrumbTitle'), url: createLink(controller: 'query', action: 'parameterValues', id: query?.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            %{--<h1 class="crimson">--}%
                %{--<i class="fa fa-paper-plane-o"></i>--}%
                %{--<g:message code="query.parameter.allowedValues.title"/> ${query.title}--}%
            %{--</h1>--}%

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
            schema: {
                model: {
                    id: "id",
                    fields: {
                        id: {editable: false, nullable: true},
                        title: {validation: {required: true}}
                    }
                },
                data: "data",
                total: "total"
            }
        });

//        var dataSource_variation = new kendo.data.DataSource();

        $("#grid_${parameter.id}").kendoGrid({
            dataSource: dataSource_${parameter.id},
            pageable: false,
            scrollable: false,
            toolbar: ["create"],
//            selectable: true,
            %{--change: onChange_${parameter.id},--}%
            columns: [
                {field: "title", title: "${message(code:'parameterSuggestedValue.title')}"},
                %{--{ command: { text: "${message(code:'parameterSuggestedValueVariation.button')}", click: showVariations_${parameter.id} }, title: " ", width: "110px" },--}%
                {command: ["edit", "destroy"], title: "&nbsp;", width: "175px"}
            ],
            editable: "inline",
            detailInit: detailInit
        });

        function detailInit(e) {
            $("<div/>").appendTo(e.detailCell).kendoGrid({
                dataSource: {
                    transport: {
                        type: 'odata',
                        read: {
                            url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationList')}/" + e.data.id,
                            dataType: "json",
                            type: "POST"
                        },
                        update: {
                            url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationUpdate')}/" + e.data.id,
                            dataType: "json",
                            type: "POST"
                        },
                        destroy: {
                            url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationDelete')}/" + e.data.id,
                            dataType: "json",
                            type: "POST"
                        },
                        create: {
                            url: "${createLink(controller: 'parameter', action: 'parameterSuggestedValueVariationCreate')}/" + e.data.id,
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
                    schema: {
                        model: {
                            id: "id",
                            fields: {
                                id: {editable: false, nullable: true},
                                title: {validation: {required: true}}
                            }
                        },
                        data: "data",
                        total: "total"
                    },
                    serverFiltering: true
                },
//                height: 550,
                toolbar: ["create"],
                columns: [
                    {field: "title", title: "${message(code:'parameterSuggestedValueVariation.title')}"},
                    {command: ["edit", "destroy"], title: "&nbsp;", width: "200px"}
                ],
                editable: "inline",
                scrollable: false,
                pageable: false
            });
        }

        function showVariations_${parameter.id}(e) {
            e.preventDefault();

            var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
            console.log(dataItem.id);
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'parameter', action: 'parameterValueVariations')}',
                data: {id: dataItem.id}
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