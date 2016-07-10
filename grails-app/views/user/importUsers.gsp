<%--
  Created by IntelliJ IDEA.
  User: Farzin
  Date: 7/10/2016
  Time: 1:43 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<form:form name="userImportForm" controller="user" action="importResult">

    <form:field border="1" showLabel="0" showHelp="0">
        <div class='k-rtl' style="width:480px;">
            <div id='file_container'>
                <input type='hidden' name='file.id' id='image_hidden'/>
                <input type='file' name='file' id='file'/>
            </div>
        </div>
    </form:field>

    <script language='javascript' type='text/javascript'>
        $(document).ready(function () {
            $("#file").kendoUpload({
                async: {
                    saveUrl: '${createLink(controller: 'user', action: 'uploadFile', id: "userImport")}',
                    autoUpload: true
                },
                select: function (e) {

                },

                multiple: false,
                success: function (e) {
                    $("#image_hidden").attr('value', e.response);
                }
            });

        });
    </script>

    <div>
        <input class="k-button" type="submit" value="${message(code: 'userImport.btn')}"
               style="float:left;"/>
        <input id="closeUserWindowBtn" class="k-button" type="button"
               value="${message(code: 'userImport.cancel')}"/>
        <script language="javascript">
            $('#closeUserWindowBtn').click(function () {
                $(this).closest("[data-role=window]").kendoWindow("close");
            });
        </script>
    </div>
</form:form>
</body>
</html>