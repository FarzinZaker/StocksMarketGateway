<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 05/01/2015
  Time: 15:22
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="tools.correlation.title"/></title>
</head>

<body>
<div class="container k-rtl correlation">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'menu.tools'), url:createLink(controller: 'tools')],
                    [text: '<i class="fa fa-link"></i> ' + message(code:'menu.tools.correlation'), url:createLink(controller: 'tools', action: 'correlation')]
            ]}"/>
        </div>
    </div>
    %{--<div class="row">--}%
        %{--<div class="col-xs-12">--}%
            %{--<h1 class="cyan">--}%
                %{--<i class="fa fa-link"></i>--}%
                %{--<g:message code="tools.correlation.title"/>--}%
            %{--</h1>--}%

            %{--<p><g:message code="tools.correlation.description"/></p>--}%
        %{--</div>--}%
    %{--</div>--}%

    <div class="row">
        <div class="col-xs-12">
            <form id="form">
                <g:render template="correlation/sourceGroupSelect"/>
                <g:render template="correlation/targetGroupSelect"/>
                <g:render template="correlation/timePeriod"/>
                <g:render template="correlation/adjustmentType"/>
            </form>

            <div class="toolbar">
                <input type="button" id="btnCalculate" value="${message(code: 'tools.correlation.calculate')}"
                       class="k-button"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div id="resultPane">
                <div class="info">
                    <g:message code="tools.correlation.notCalculatedYet"/>
                </div>
            </div>

            <div id="loading" style="display: none;padding:10px;">
                <asset:image src="loading.gif" style="margin-left: 10px;"/>
                <g:message code="loading"/>
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btnCalculate').click(function () {
            $('#resultPane').fadeOut(200, function () {
                $('#loading').fadeIn();
            });
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'correlationGrid')}',
                data: $('#form').serialize()
            }).done(function (response) {
                $('#loading').fadeOut(500, function () {
                    $('#resultPane').html(response).fadeIn();
                })
            });
        });
    });
</script>
</body>
</html>