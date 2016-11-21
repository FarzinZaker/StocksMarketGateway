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
    <title><g:message code="twitter.home.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-twitter"></i> ' + message(code: 'twitter.home.title'), url: createLink(controller: 'user', action: 'wall', id: params.id)]
            ]}"/>
        </div>
    </div>


    <div class="row">
        <div class="col-xs-6 k-rtl">
            <g:render template="/talk/write"/>
            <g:render template="home/materialList"/>
        </div>

        <div class="col-xs-6 k-rtl">
            <g:render template="home/suggestList"/>
            <div class="row">
                <div class="col-sm-12 k-rtl">
                    <g:render template="/user/home/topArticles"/>
                    <g:render template="home/topUsers"/>
                    <g:render template="home/topGroups"/>
                </div>
            </div>
            <div class="row">

                <div class="col-sm-6 k-rtl">
                    <g:render template="home/tagCloud"/>
                </div>

                <div class="col-sm-6 k-rtl">
                    <g:render template="home/groupList"/>
                    <g:render template="home/followList"/>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>