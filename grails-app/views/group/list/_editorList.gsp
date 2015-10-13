<div class="editorGridContainer k-rtl">

    <div id="editorGrid"></div>
</div>

<script>
    $(document).ready(function () {
        $("#editorGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'editorJsonList')}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "string"},
                            title: {type: "string"},
                            membershipType: {type: "string"},
                            membership1MonthPrice: {type: "number"},
                            membership3MonthPrice: {type: "number"},
                            membership6MonthPrice: {type: "number"},
                            membership12MonthPrice: {type: "number"},
                            allowExceptionalUsers: {type: "boolean"}
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
                    title: "${message(code:'twitter.group.title.label')}"
                },
                {
                    field: "membershipType",
                    title: "${message(code:'twitter.group.membershipType.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "100px"
                },
                {
                    title: "${message(code:'twitter.group.membershipPrice.label')} (${message(code: 'rial')})",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    columns: [
                        {
                            field: "membership1MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.1Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "100px",
                            template: "#=formatNumber(membership1MonthPrice)#"
                        },
                        {
                            field: "membership3MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.3Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "100px",
                            template: "#=formatNumber(membership3MonthPrice)#"
                        },
                        {
                            field: "membership6MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.6Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "100px",
                            template: "#=formatNumber(membership6MonthPrice)#"
                        },
                        {
                            field: "membership12MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.12Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "100px",
                            template: "#=formatNumber(membership12MonthPrice)#"
                        }
                    ]
                },
                {
                    field: "allowExceptionalUsers",
                    title: "${message(code:'twitter.group.allowExceptionalUsers.label')}",
                    template: "<img width='16px' src=\"#:allowExceptionalUsers?'${resource(dir:'images', file: 'yes.png')}':'${resource(dir:'images', file: 'no.png')}'#\" />",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'twitter.group.manageAuthors.button')}", click: manageEditorGridItemAuthors},
                    title: "",
                    width: "107px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'twitter.group.manageMembers.button')}", click: manageEditorGridItemMembers},
                    title: "",
                    width: "87px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'cancelMembership')}", click: cancelEditorGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });

    function manageEditorGridItemAuthors(e) {
        window.location.href = "${createLink(action: 'manageAuthors')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function manageEditorGridItemMembers(e) {
        window.location.href = "${createLink(action: 'manageMembers')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function editEditorGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    var editorIdForDelete = 0;
    function cancelEditorGridItem(e) {
        if (editorIdForDelete == 0) {
            editorIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.cancel.confirm')}', deleteEditorItem, cancelEditorDelete);
        }

    }

    function cancelEditorDelete() {
        editorIdForDelete = 0;
    }

    function deleteEditorItem() {
        if (editorIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'editorDelete')}',
                data: {id: editorIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#editorGrid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.cancel.error')}');
                }
            });
            editorIdForDelete = 0;
        }
    }
</script>