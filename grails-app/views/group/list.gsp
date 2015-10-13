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
    <title><g:message code="twitter.group.list.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: '<i class="fa fa-users"></i> ' + message(code: "twitter.group.list.title"), url: createLink(action: 'list')]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12 k-rtl">

            <div id="tabstrip">
                <ul>
                    <li class="k-state-active">
                        <g:message code="twitter.group.admin.groupList.title"/>
                    </li>
                    <li>
                        <g:message code="twitter.group.editor.groupList.title"/>
                    </li>
                    <li>
                        <g:message code="twitter.group.author.groupList.title"/>
                    </li>
                    <li>
                        <g:message code="twitter.group.member.groupList.title"/>
                    </li>
                </ul>

                <div>
                    <g:render template="list/ownerList"/>
                </div>

                <div>
                    <g:render template="list/editorList"/>
                </div>

                <div>
                    <g:render template="list/authorList"/>
                </div>

                <div>
                    <g:render template="list/memberList"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    function formatNumber(data) {
        return Math.abs(Math.round(data)).toString().replace(/./g, function (c, i, a) {
            return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
        });
    }


    $(document).ready(function () {
        $("#tabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });
    });
</script>
</body>
</html>