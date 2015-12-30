<%@ page import="stocks.messaging.NewsLetter" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="newsLetter.history.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsLetter.title'), url: createLink(controller: 'newsLetter')],
                    [text: newsLetter.subject, url: createLink(controller: 'newsLetter', action: 'build', id: newsLetter?.id)],
                    [text: '<i class="fa fa-bullhorn"></i>' + message(code: 'history'), url: createLink(controller: 'newsLetter', action: 'history', id: newsLetter?.id)]
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
                        url: "${createLink(action: 'historyJsonList', id:newsLetter.id)}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            startDate: {type: "string"},
                            finishDate: {type: "string"},
                            status: {type: "string"}
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
                    field: "startDate",
                    title: "${message(code:'newsLetterInstance.startDate.label')}"
                },
                {
                    field: "finishDate",
                    title: "${message(code:'newsLetterInstance.finishDate.label')}"
                },
                {
                    field: "status",
                    title: "${message(code:'newsLetterInstance.status.label')}"
                },
                {
                    command: {text: "${message(code:'details')}", click: showItemDetails},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
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
