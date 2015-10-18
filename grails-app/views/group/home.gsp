<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 10/13/2015
  Time: 5:55 PM
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${group.title}</title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: message(code: 'twitter.group.select'), url: createLink(controller: 'group', action: 'select')],
                    [text: '<i class="fa fa-users"></i> ' + group.title, url: createLink(controller: 'group', action: 'home', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3 k-rtl">
            <g:render template="home/header"/>
        </div>
        <div class="col-xs-6 k-rtl">
            <g:render template="home/materialList"/>
        </div>
        <div class="col-xs-3 k-rtl">
            <g:render template="home/authorList"/>
            <g:render template="home/tagCloud"/>
        </div>
    </div>
</div>

</body>
</html>