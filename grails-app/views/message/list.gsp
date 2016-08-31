<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 8/31/2016
  Time: 3:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="message.list.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-envelope"></i>' + message(code: 'message.list.title'), url: createLink(controller: 'message', action: 'list')]
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
                        url: "${createLink(action: 'jsonList')}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            sender: {type: "string"},
                            shortBody: {type: "string"},
                            date: {type: "string"},
                            isRead: {type: "boolean"},
                            senderId: {type: "integer"},
                            rootMessageId: {type: "integer"}
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
                    field: "isRead",
                    title: ' ',
                    width: '30px',
                    template: "<i class=\"fa #:isRead?'fa-folder-open readMail':'fa-folder notReadMail'#\"></i>"
                },
                {
                    field: "sender",
                    title: "${message(code:'newsLetter.subject.label')}",
                    width: '150px'
                },
                {
                    field: "shortBody",
                    title: '${message(code:'newsLetter.categories.label')}',
                    template: "<a href=\"${createLink(controller: 'message', action: 'view')}/#:rootMessageId#?msg=#:id#\">#:shortBody#</a>"
                },
                {
                    field: "date",
                    title: "${message(code:'newsLetter.sendDate.label')}",
                    width: '100px'
                },
                {
                    field: "shortBody",
                    title: ' ',
                    template: "<a class='btn-reply' href=\"javascript:sendMessage(#:senderId#, '#:sender#',#:id#)\"><i class='fa fa-share'></i></a>",
                    width: '30px'
                },
                {
                    field: "shortBody",
                    title: ' ',
                    template: "<a class='btn-forward' href=\"javascript:sendMessage(#:senderId#, '#:sender#',#:id#, true)\"><i class='fa fa-reply'></i></a>",
                    width: '30px'
                },
                {
                    field: "shortBody",
                    title: ' ',
                    template: "<a class='btn-delete' href=\"javascript:removeGridItem(#:id#)\"><i class='fa fa-remove'></i></a>",
                    width: '40px'
                }
            ]
        });
    });

    var idForDelete = 0;
    function removeGridItem(id) {
        if(idForDelete == 0) {
            idForDelete = id;
            confirm('${message(code:'message.delete.confirm')}', deleteItem, cancelDelete);
        }

    }

    function cancelDelete(){
        idForDelete = 0;
    }

    function deleteItem(){
        if(idForDelete > 0 ) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'delete')}',
                data: { id: idForDelete }
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#grid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'message.delete.error')}');
                }
            });
            idForDelete = 0;
        }
    }
</script>
<msg:sendWindow/>
</body>
</html>