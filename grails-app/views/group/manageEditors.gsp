<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.manageEditors.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: message(code: 'twitter.group.list.title'), url: createLink(action: 'list')],
                    [text: group.title, url: createLink(action: 'build', id: params.id)],
                    [text: '<i class="fa fa-user"></i> ' + message(code: "twitter.group.manageEditors.title"), url: createLink(action: 'manageEditors', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <div class="editorGridContainer k-rtl">

                <div id="editorGrid"></div>
            </div>
        </div>
    </div>
</div>

<div class="k-rtl hidden">
    <div id="addEditorWindow">
        <div class="container">
            <form:form name="addEditorForm">
                <form:field fieldName="editor">
                    <form:textBox name="editor.id" id="editor" validation="required" style="width:300px"/>

                    <script language="javascript" type="text/javascript">
                        $(document).ready(function () {
                            $("#editor").removeClass('k-textbox').width('290').kendoComboBox({
                                dataTextField: "name",
                                dataValueField: "value",
                                filter: "contains",
                                dataSource: {
                                    serverFiltering: true,
                                    transport: {
                                        type: 'odata',
                                        read: {
                                            url: "${createLink(controller: 'user', action: 'autoComplete')}",
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
                        });
                    </script>
                </form:field>
                <div class="toolbar" style="margin-top:10px;margin-bottom:0;">
                    <form:button name="submit" text="${message(code: 'add')}" style="float:right"
                                 onclick="saveEditor();"/>
                    <form:button name="cancel" text="${message(code: 'close')}" style="float:left"
                                 onclick="closeEditorWindow();"/>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script>

    var wnd;

    function saveEditor() {
        if ($('#addEditorForm').isValid())
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'addEditor')}',
                data: {id: '${params.id}', editorId: $('#editor').data('kendoComboBox').value()}
            }).done(function (response) {
                var grid = $('#editorGrid').data('kendoGrid');
                grid.dataSource.read();
                grid.refresh();
                wnd.close();
            });
    }

    function closeEditorWindow() {
        wnd.close();
    }
    function addEditor() {
        $('#editor').data('kendoComboBox').value('');
        if (!wnd)
            wnd = $("#addEditorWindow")
                    .kendoWindow({
                        title: "${message(code: 'twitter.group.editors.add')}",
                        modal: true,
                        visible: false,
                        resizable: false,
                        width: 650
                    }).data("kendoWindow");
        wnd.center().open();
    }

    $(document).ready(function () {
        $("#editorGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'manageEditorsJsonList', id: params.id)}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "string"},
                            title: {type: "string"}
                        }
                    },
                    data: "data", // records are returned in the "data" field of the response
                    total: "total"
                },
                pageSize: 20,
                serverPaging: false,
                serverFiltering: false,
                serverSorting: false
            },
            filterable: false,
            sortable: true,
            pageable: true,
            toolbar: [
                {
                    template: "<a class='k-button k-button-icontext k-grid-add' href='javascript:addEditor();'>${message(code: 'twitter.group.editors.add')}</a>"
                }
            ],
            columns: [
                %{--{--}%
                %{--field: "id",--}%
                %{--title: "${message(code:'twitter.group.id.label')}",--}%
                %{--width: "70px",--}%
                %{--attributes: {style: "text-align: center"},--}%
                %{--headerAttributes: {style: "text-align: center"}--}%
                %{--},--}%
                {
                    field: "title",
                    title: "${message(code:'user.firstName.label')}"
                },
                {
                    command: {text: "${message(code:'remove')}", click: removeEditorGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });


    var editorIdForDelete = 0;
    function removeEditorGridItem(e) {
        if (editorIdForDelete == 0) {
            editorIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.editor.delete.confirm')}', deleteEditorItem, cancelEditorDelete);
        }

    }

    function cancelEditorDelete() {
        editorIdForDelete = 0;
    }

    function deleteEditorItem() {
        if (editorIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'deleteEditor')}',
                data: {id: '${params.id}', editorId: editorIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var grid = $('#editorGrid').data('kendoGrid');
                    grid.dataSource.read();
                    grid.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.editor.delete.error')}');
                }
            });
            editorIdForDelete = 0;
        }
    }
</script>
</body>
</html>