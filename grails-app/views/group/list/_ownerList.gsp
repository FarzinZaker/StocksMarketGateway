<div class="ownerGridContainer k-rtl">

    <div id="ownerGrid"></div>
</div>

<script>
    $(document).ready(function () {
        $("#ownerGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'ownerJsonList')}",
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
            toolbar: [
                {
                    template: "<a class='k-button k-button-icontext k-grid-add' href='${createLink(action: 'build')}'>${message(code: 'twitter.group.create')}</a>"
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
                    title: "${message(code:'twitter.group.title.label')}"
                },
                {
                    field: "membershipType",
                    title: "${message(code:'twitter.group.membershipType.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "80px"
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
                            width: "70px",
                            template: "#=formatNumber(membership1MonthPrice)#"
                        },
                        {
                            field: "membership3MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.3Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "70px",
                            template: "#=formatNumber(membership3MonthPrice)#"
                        },
                        {
                            field: "membership6MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.6Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "70px",
                            template: "#=formatNumber(membership6MonthPrice)#"
                        },
                        {
                            field: "membership12MonthPrice",
                            title: "${message(code:'twitter.group.membershipPeriod.12Month')}",
                            attributes: {style: "text-align: center"},
                            headerAttributes: {style: "text-align: center"},
                            width: "70px",
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
                    command: {
                        text: "${message(code:'twitter.group.manageEditors.button')}",
                        click: manageOwnerGridItemEditors
                    },
                    title: "",
                    width: "105px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {
                        text: "${message(code:'twitter.group.manageAuthors.button')}",
                        click: manageOwnerGridItemAuthors
                    },
                    title: "",
                    width: "107px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {
                        text: "${message(code:'twitter.group.manageMembers.button')}",
                        click: manageOwnerGridItemMembers
                    },
                    title: "",
                    width: "87px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'edit')}", click: editOwnerGridItem},
                    title: "",
                    width: "90px",
                    headerAttributes: {style: "text-align: center"}
                },
                {
                    command: {text: "${message(code:'remove')}", click: removeOwnerGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });

    function manageOwnerGridItemEditors(e) {
        window.location.href = "${createLink(action: 'manageEditors')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function manageOwnerGridItemAuthors(e) {
        window.location.href = "${createLink(action: 'manageAuthors')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function manageOwnerGridItemMembers(e) {
        window.location.href = "${createLink(action: 'manageMembers')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    function editOwnerGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    var ownerIdForDelete = 0;
    function removeOwnerGridItem(e) {
        if (ownerIdForDelete == 0) {
            ownerIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.delete.confirm')}', deleteOwnerItem, cancelOwnerDelete);
        }

    }

    function cancelOwnerDelete() {
        ownerIdForDelete = 0;
    }

    function deleteOwnerItem() {
        if (ownerIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'ownerDelete')}',
                data: {id: ownerIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#ownerGrid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.delete.error')}');
                }
            });
            ownerIdForDelete = 0;
        }
    }
</script>