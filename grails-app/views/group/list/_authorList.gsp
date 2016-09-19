<div class="authorGridContainer k-rtl">

    <div id="authorGrid"></div>
</div>

<script>
    $(document).ready(function () {
        $("#authorGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'authorJsonList')}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "string"},
                            title: {type: "string"},
                            authorType: {type: "string"},
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
                    field: "authorType",
                    title: "${message(code:'twitter.group.authorType.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "100px"
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
                    command: {text: "${message(code:'twitter.group.manageMembers.button')}", click: manageEditorGridItemMembers},
                    title: "",
                    width: "87px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'cancelMembership')}", click: cancelAuthorGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });

    function manageEditorGridItemMembers(e) {
        window.location.href = "${createLink(action: 'manageMembers')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function editAuthorGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    var authorIdForDelete = 0;
    function cancelAuthorGridItem(e) {
        if (authorIdForDelete == 0) {
            authorIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.cancel.confirm')}', deleteAuthorItem, cancelAuthorDelete);
        }

    }

    function cancelAuthorDelete() {
        authorIdForDelete = 0;
    }

    function deleteAuthorItem() {
        if (authorIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'authorDelete')}',
                data: {id: authorIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#authorGrid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.cancel.error')}');
                }
            });
            authorIdForDelete = 0;
        }
    }
</script>