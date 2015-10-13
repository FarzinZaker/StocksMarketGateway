<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.manageAuthors.title"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'twitter.title'), url: ''],
                    [text: message(code: 'twitter.group.list.title'), url: createLink(action: 'list')],
                    [text: group.title, url: createLink(action: 'build', id: params.id)],
                    [text: '<i class="fa fa-user"></i> ' + message(code: "twitter.group.manageAuthors.title"), url: createLink(action: 'manageAuthors', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-12">

            <div class="authorGridContainer k-rtl">

                <div id="authorGrid"></div>
            </div>
        </div>
    </div>
</div>

<div class="k-rtl hidden">
    <div id="addAuthorWindow">
        <div class="container-fluid">
            <form:form name="addAuthorForm">
                <form:field fieldName="author">
                    <form:textBox name="author.id" id="author" validation="required" style="width:300px"/>

                    <script language="javascript" type="text/javascript">
                        $(document).ready(function () {
                            $("#author").removeClass('k-textbox').width('290').kendoComboBox({
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
                                 onclick="saveAuthor();"/>
                    <form:button name="cancel" text="${message(code: 'close')}" style="float:left"
                                 onclick="closeAuthorWindow();"/>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script>

    var wnd;

    function saveAuthor() {
        if ($('#addAuthorForm').isValid())
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'addAuthor')}',
                data: {id: '${params.id}', authorId: $('#author').data('kendoComboBox').value()}
            }).done(function (response) {
                var grid = $('#authorGrid').data('kendoGrid');
                grid.dataSource.read();
                grid.refresh();
                wnd.close();
            });
    }

    function closeAuthorWindow() {
        wnd.close();
    }
    function addAuthor() {
        $('#author').data('kendoComboBox').value('');
        if (!wnd)
            wnd = $("#addAuthorWindow")
                    .kendoWindow({
                        title: "${message(code: 'twitter.group.authors.add')}",
                        modal: true,
                        visible: false,
                        resizable: false,
                        width: 650
                    }).data("kendoWindow");
        wnd.center().open();
    }

    $(document).ready(function () {
        $("#authorGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'manageAuthorsJsonList', id: params.id)}",
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
                    template: "<a class='k-button k-button-icontext k-grid-add' href='javascript:addAuthor();'>${message(code: 'twitter.group.authors.add')}</a>"
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
                    command: {text: "${message(code:'remove')}", click: removeAuthorGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });


    var authorIdForDelete = 0;
    function removeAuthorGridItem(e) {
        if (authorIdForDelete == 0) {
            authorIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.author.delete.confirm')}', deleteAuthorItem, cancelAuthorDelete);
        }

    }

    function cancelAuthorDelete() {
        authorIdForDelete = 0;
    }

    function deleteAuthorItem() {
        if (authorIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'deleteAuthor')}',
                data: {id: '${params.id}', authorId: authorIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var grid = $('#authorGrid').data('kendoGrid');
                    grid.dataSource.read();
                    grid.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.author.delete.error')}');
                }
            });
            authorIdForDelete = 0;
        }
    }
</script>
</body>
</html>