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
                    [text: message(code: 'message.list.title'), url: createLink(controller: 'message', action: 'list')],
                    [text: '<i class="fa fa-envelope"></i>' + message(code: 'message.title') + ' ' + msg?.sender?.nickname, url: createLink(controller: 'message', action: 'view', params: [id: rootMessage?.id, msg: msg?.id])]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 k-rtl">
            <ul class="message-list">
                <g:render template="message" model="[rootMessage: rootMessage, msg: msg, list: list]"/>
            </ul>
        </div>
    </div>
</div>

<msg:sendWindow/>
<script language="javascript">

    var idForDelete = 0;
    function removeGridItem(id) {
        if (idForDelete == 0) {
            idForDelete = id;
            confirm('${message(code:'message.delete.confirm')}', deleteItem, cancelDelete);
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
                    location.reload();
                }
                else {
                    window.alert('${message(code:'message.delete.error')}');
                }
            });
            idForDelete = 0;
        }
    }

    $(document).ready(function () {
        $('html, body').animate({
            scrollTop: $("#message_${params.msg}").offset().top - 100
        }, 500);
    });
</script>
</body>
</html>