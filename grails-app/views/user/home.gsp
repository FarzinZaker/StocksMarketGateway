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
    <title><g:message code="twitter.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: '<i class="fa fa-twitter"></i> ' + message(code: 'twitter.title'), url: createLink(controller: 'user', action: 'wall', id: params.id)]
            ]}"/>
        </div>
    </div>


    <div class="row-fluid">
        %{--<div class="col-xs-2 k-rtl">--}%
        %{--<g:render template="home/header"/>--}%
        %{--</div>--}%
        <div class="col-xs-6 k-rtl">
            <g:render template="home/materialList"/>
        </div>

        <div class="col-xs-6 k-rtl">

            <div>

                <div class="row-fluid">

                    <div class="row-fluid">
                        <div class="col-xs-12" style="padding:0">
                            <g:render template="home/suggestList"/>
                        </div>
                    </div>

                    <div class="col-xs-6 k-rtl">
                        <div class="dashLet cyan">
                            <h2><i class="fa fa-star"></i> <g:message code="twitter.home.topMaterial.title"/></h2>
                            <twitter:topFollowedMaterials id="${user?.id}"/>
                        </div>
                        <g:render template="home/tagCloud"/>
                    </div>

                    <div class="col-xs-6 k-rtl">
                        <g:render template="home/groupList"/>
                        <g:render template="home/followList"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>