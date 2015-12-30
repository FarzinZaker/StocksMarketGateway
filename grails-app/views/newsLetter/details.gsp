<%@ page import="stocks.messaging.NewsLetter" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="newsLetter.details.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsLetter.title'), url: createLink(controller: 'newsLetter')],
                    [text: newsLetterInstance?.newsLetter?.subject, url: createLink(controller: 'newsLetter', action: 'build', id: newsLetterInstance?.newsLetter?.id)],
                    [text: message(code: 'history'), url: createLink(controller: 'newsLetter', action: 'history', id: newsLetterInstance?.newsLetter?.id)],
                    [text: '<i class="fa fa-bullhorn"></i>' + message(code: 'details'), url: createLink(controller: 'newsLetter', action: 'details', id: newsLetterInstance?.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 k-rtl">
            <div id="grid"></div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $("#grid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'detailsJsonList', id:newsLetterInstance.id)}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            customer: {type: "string"},
                            email: {type: "string"},
                            scheduleDate: {type: "string"},
                            sendDate: {type: "string"},
                            status: {type: "string"},
                            errorMessage: {type: "string"}
                        }
                    },
                    data: "data",
                    total: "total"
                },
                pageSize: 10,
                serverPaging: true,
                serverFiltering: true,
                serverSorting: true
            },
            filterable: false,
            sortable: true,
            pageable: true,
            columns: [
                {
                    field: "customer",
                    title: "${message(code:'newsLetterLog.customer.label')}"
                },
                {
                    field: "email",
                    title: "${message(code:'newsLetterLog.email.label')}"
                },
                {
                    field: "scheduleDate",
                    title: "${message(code:'newsLetterLog.scheduleDate.label')}"
                },
                {
                    field: "sendDate",
                    title: "${message(code:'newsLetterLog.sendDate.label')}"
                },
                {
                    field: "status",
                    title: "${message(code:'newsLetterLog.status.label')}"
                },
                {
                    field: "errorMessage",
                    title: "${message(code:'newsLetterLog.errorMessage.label')}"
                }
            ]
        });
    });

    function showItemDetails(e) {
        window.location.href = "${createLink(action: 'details')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }
</script>
</body>
</html>
