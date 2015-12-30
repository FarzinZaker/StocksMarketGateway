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
    <title><g:message code="transaction.user" args="${[user]}"/></title>

    <script language="javascript" type="text/javascript">
        var dataUrlParams = "user=${user?.id}";
        var editUrlParams = "user=${user?.id}"
    </script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'transaction'), url: createLink(controller: 'transaction')],
                    [text: '<i class="fa fa-money"></i> ' + message(code: 'transaction.user', args: [user]), url: createLink(controller: 'transaction', action: 'user', params: [user: user?.id])]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="queryListContainer k-rtl">

                <div id="queryListView"></div>

                <div id="pager" class="k-pager-wrap"></div>
            </div>

            <g:render template="list"/>

            <div class="toolbar">
                <form:linkButton href="${createLink(action: 'build', params: [user: user?.id])}"
                                 text="${message(code: 'transaction.create')}"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>