<%@ page import="stocks.messaging.NewsLetter" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="newsLetter.list.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsLetter.title'), url: createLink(controller: 'newsLetter')],
                    [text: '<i class="fa fa-bullhorn"></i>' + message(code: 'newsLetter.list.title'), url: createLink(controller: 'newsLetter', action: 'list')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12 k-rtl">
            <div id="grid"></div>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build')}"
                                 text="${message(code: 'newsLetter.add')}"/>
            </div>
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
                        url: "${createLink(action: 'jsonList')}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            subject: {type: "string"},
                            sendDate: {type: "string"}
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
                    field: "name",
                    title: "${message(code:'newsLetter.subject.label')}"
                },
                {
                    field: "categories",
                    title: "${message(code:'newsLetter.categories.label')}"
                },
                {
                    field: "sendDate",
                    title: "${message(code:'newsLetter.sendDate.label')}"
                },
                {
                    command: {text: "${message(code:'history')}", click: showItemHistory},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'edit')}", click: editGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'remove')}", click: removeGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });

    function editGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function showItemHistory(e) {
        window.location.href = "${createLink(action: 'history')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    var idForDelete = 0;
    function removeGridItem(e) {
        if (idForDelete == 0) {
            idForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'city.delete.confirm')}', deleteItem, cancelDelete);
        }

    }

    function cancelDelete() {
        idForDelete = 0;
    }

    function deleteItem() {
        if (idForDelete > 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'delete')}',
                data: {id: idForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#grid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'city.delete.error')}');
                }
            });
            idForDelete = 0;
        }
    }
</script>
</body>
</html>
