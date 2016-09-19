<%--
  Created by IntelliJ IDEA.
  User: farzin
  Date: 8/8/2015
  Time: 4:30 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.transfer" args="${[group?.title]}"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: group.title, url: createLink(action: 'build', id: params.id)],
                    [text: '<i class="fa-group fa"></i> ' + message(code: "twitter.group.transfer", args: [group?.title]), url: createLink(action: 'transfer', id: group?.idNumber)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 k-rtl">
            <form:form action="doTransfer" name="transferForm">
                <form:hidden name="id" value="${params.id}"/>

                <form:field fieldName="transfer.receiver" showHelp="1" border="1">
                    <input name="receiver" id="receiver" class="k-textbox" data-validation="required"/>
                </form:field>
                <script language="javascript">

                    $("#receiver").removeClass('k-textbox').width('462px').kendoComboBox({
                        dataTextField: "name",
                        dataValueField: "value",
                        filter: "contains",
                        dataSource: {
                            serverFiltering: true,
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(controller: 'message', action: 'userAutoComplete')}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                data: "data" // records are returned in the "data" field of the response
                            }
                        },
                        change: function (e) {
                            if (this.value() && this.selectedIndex == -1) {
                                var dt = this.dataSource._data[0];
                                this.text(dt[this.options.dataTextField]);
                                this._selectItem();
                            }
                        }
                    });
                </script>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'twitter.group.transfer.submit.label')}"
                           class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>