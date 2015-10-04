<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.build.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'menu.portfolios'), url: createLink(controller: 'portfolio')],
                    [text: '<i class="fa fa-shopping-cart"></i> ' + message(code: 'portfolio.build.title'), url: createLink(controller: 'portfolio', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">
            %{--<h1 class="darkBlue">--}%
                %{--<i class="fa fa-shopping-cart"></i>--}%
                %{--<g:message code="portfolio.build.title"/></h1>--}%
            <g:if test="${flash.message}">
                <div class="errorMessage">
                    ${flash.message}
                </div>
            </g:if>
            <form:form action="save" name="portfolioForm">
                <form:hidden name="id" entity="${portfolio}"/>

                <form:field fieldName="portfolio.name">
                    <form:textBox name="name" style="width:500px" entity="${portfolio}" validation="required"
                                  value="${flash.data ?: portfolio?.name ?: ''}"/>
                </form:field>
                <form:field fieldName="portfolio.advanced">
                    <div style="width: 500px">
                    <form:checkbox name="defaultItems" text="${message(code: 'portfolio.defaultItems.label')}"
                                   style="width:132px" entity="${portfolio}" onchange="showHideItems()"
                                   checked="${portfolio?.defaultItems != null ? portfolio?.defaultItems : true}"/>
                    %{--<form:checkbox name="fullAccounting" text="${message(code: 'portfolio.fullAccounting.label')}"--}%
                                   %{--style="width:120px" entity="${portfolio}"--}%
                                   %{--checked="${portfolio?.fullAccounting ?: false}"/>--}%
                    <form:checkbox name="useWageAndDiscount"
                                   text="${message(code: 'portfolio.useWageAndDiscount.label')}"
                                   style="width:120px" entity="${portfolio}"
                                   checked="${portfolio?.useWageAndDiscount ?: false}"/>
                    <form:checkbox name="useBroker" text="${message(code: 'portfolio.useBroker.label')}"
                                   style="width:120px"
                                   entity="${portfolio}"
                                   onchange="showHideBrokers()"
                                   checked="${portfolio?.useBroker ?: false}"/>
                    </div>
                </form:field>
                <div id="portfolioItems">
                    <form:field fieldName="portfolio.items">
                        <div style="width: 500px">
                            <g:each in="${items}" var="itm">
                                <form:checkbox text="${itm.title}"
                                               checked="${portfolio?.defaultItems ? itm.default : portfolioAvailItems.find {
                                                   it.item == itm.clazz
                                               }}" name="${itm.clazz}"
                                               onchange="showHidePortfoilioItems('${itm.clazz}')"
                                               style="width:180px"/>
                            </g:each>
                        </div>
                    </form:field>
                </div>

                <div id="brokers">
                    <form:field fieldName="portfolio.bokers">
                        <form:multiSelect name="broker"
                                          dataSourceUrl="${createLink(controller: 'portfolio', action: 'jsonSearchBroker')}"
                                          style="width: 500px" entity="${portfolioAvailBrokers}"/>
                    </form:field>
                </div>
                <div id="container"></div>
                <g:each in="${['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioCustomBondsItem', 'portfolioCustomSymbolItem', 'portfolioCustomSymbolPriorityItem', 'portfolioImmovableItem', 'portfolioMovableItem']}">
                    <div id="${it}GridContainer" class="k-rtl">
                        <form:field fieldName="${it}" label="${message(code: "${it}.label")}" showHelp="0">
                            <section id="${it}Section">
                            <div id="${it}Grid"></div>
                            <script>
                                $(function () {
                                    $('#${it}Grid').kendoGrid({
                                        dataSource: {
                                            type: "json",
                                            serverFiltering: true,

                                            transport: {
                                                read: {
                                                    url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}"
                                                },
                                                parameterMap: function (option, operation) {
                                                    var result = option;
                                                    result.clazz = '${it}';
                                                    result.id =${ params.id?:0};
                                                    return result;
                                                }
                                            }
                                        },
                                        pageable:true,
                                        pageSize: 10,
                                        toolbar: [
                                            {template:'<a class="k-button" href="\\#${it}Section" onclick="return createProperty(\'${it}\')">${message(code:'create')}</a>'}
                                        ],
                                        columns: [
                                            {
                                                field: "propertyTitle",
                                                title: '${message(code:'property.title')}'
                                            },
                                            {
                                                command: [
                                                    {text: "<g:message code="delete"/>", click: deleteProperty},
                                                    {text: "<g:message code="edit"/>", click: editProperty},
                                                ],
                                                title: "&nbsp;",
                                                width: "160px"
                                            }
                                        ]
                                    })
                                    showHidePortfoilioItems('${it}')
                                })
                            </script>
                            </section>
                        </form:field>
                    </div>
                </g:each>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'portfolio.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
    <script>
        function showHidePortfoilioItems(type) {
            if ($('#' + type).is(':checked')) {
                $('#' + type + 'GridContainer').fadeIn();
            }
            else {
                $('#' + type + 'GridContainer').fadeOut();
            }
        }
        function showHideItems() {
            if ($('#defaultItems').is(':checked')) {
                $('#portfolioItems').fadeOut();
            }
            else {
                $('#portfolioItems').fadeIn();
            }
        }
        function showHideBrokers() {
            if ($('#useBroker').is(':checked')) {
                $('#brokers').fadeIn();
            }
            else {
                $('#brokers').fadeOut();
            }
        }
        showHideBrokers();
        showHideItems();
        function createProperty(type) {

            var container=$('#'+type+'GridContainer')
            $('<div id="propertyWindow"/>')
                    .data('caller', $(this))
                    .appendTo(container)
                    .addClass('k-rtl')
                    .kendoWindow({
                        width: '500px',
                        title: false,
                        position:{
                            left: "20%"
                        },
                        content: '${createLink(action: 'propertyForm')}?clazz=' + type+'&portfolioId=${params.id}',
                        modal: true,
                        close: function (e) {
                            $('#' + type + 'Grid').data('kendoGrid').dataSource.read();
                        }
                    }).data('kendoWindow').center().open();
        }
        function editProperty(e) {
            e.preventDefault()

            var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
            var container=$('#'+dataItem.clazz+'GridContainer')
            $('<div id="propertyWindow"/>')
                    .data('caller', $(this))
                    .appendTo(container)
                    .addClass('k-rtl')
                    .kendoWindow({
                        width: '500px',
                        title: false,
                        content: '${createLink(action: 'propertyForm')}/' + dataItem.propertyId + '?clazz=' + dataItem.clazz+ '&portfolioId=${params.id}',
                        modal: true,
                        close: function (e) {
                            $('#' + dataItem.clazz + 'Grid').data('kendoGrid').dataSource.read();
                        }
                    }).data('kendoWindow').center().open();
        }
        var idForDelete = 0;
        var clazzForDelete = '';
        function deleteProperty(e) {
            e.preventDefault();

            var dataItem = this.dataItem($(e.currentTarget).closest("tr"));

            idForDelete = dataItem.propertyId;
            clazzForDelete = dataItem.clazz;
            confirm('${message(code: 'property.delete.confirm')}', function () {
                doDelete(idForDelete, clazzForDelete);
            }, function () {
                cancelDelete();
            })
        }

        function cancelDelete() {
            idForDelete = 0;
            clazzForDelete = ''
        }

        function doDelete(idForDelete, clazzForDelete) {
            console.log(idForDelete)
            if (idForDelete > 0) {
                $.ajax({
                    type: "POST",
                    url: '${createLink(action: 'deleteProperty')}',
                    data: {
                        id: idForDelete,
                        clazz: clazzForDelete,
                        portfolioId:${params.id?:0}
                    }
                }).done(function (response) {
                    if (response == 1) {
                        $('#' + clazzForDelete + 'Grid').data('kendoGrid').dataSource.read();
                    }
                    else {
                        window.alert('${message(code: 'property.delete.error')}');
                    }
                });
                idForDelete = 0;
            }
        }

    </script>
</div>
</body>
</html>