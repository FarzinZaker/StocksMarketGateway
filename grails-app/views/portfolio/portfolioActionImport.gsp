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

<form:form name="portfolioActionImportForm" controller="portfolioAction" action="portfolioActionImportResult">
    <input type="hidden" name="id" value="${params.id}"/>
    <g:if test="${portfolio.useBroker}">

        <form:field border="1" id="brokerconainer" fieldName="portfolioAction.broker" showHelp="0">
            <input id="fileImportBroker" name="broker" style="width: 480px" data-validation="requried">
        </form:field>
        <script language="JavaScript">
            $(document).ready(function () {
                $("#fileImportBroker").kendoComboBox({
                    autoBind: false,
                    dataTextField: "propertyTitle",
                    dataValueField: "propertyId",
                    placeholder: "${message(code:'please-select')}",
                    dataSource: {
                        type: "json",
                        serverFiltering: true,
                        transport: {
                            read: {
                                url: "${createLink(controller: 'portfolioAction', action: 'propertyList')}"// + currentPropertyId
                            },
                            parameterMap: function (option, operation) {
                                var result = option;
                                result.portfolioId =${portfolio?.id};
                                result.clazz = 'portfolioBrokerItem';
                                result.availOnly = 1;
                                result.id =${ params.id};
                                return result;
                            }
                        }
                    }
                });
            });
        </script>
    </g:if>

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
                    saveUrl: '${createLink(controller: 'portfolioAction', action: 'uploadFile', id: "portfolioActionImport${portfolio?.id}")}',
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
        <input class="k-button" type="submit" value="${message(code: 'portfolioActionImport.btn')}"
               style="float:left;"/>
        <input id="closePortfolioActionWindowBtn" class="k-button" type="button"
               value="${message(code: 'portfolioActionImport.cancel')}"/>
        <script language="javascript">
            $('#closePortfolioActionWindowBtn').click(function () {
                $(this).closest("[data-role=window]").kendoWindow("close");
            });
        </script>
    </div>
</form:form>
</body>
</html>