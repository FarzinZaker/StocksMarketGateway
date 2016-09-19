<div class="memberGridContainer k-rtl">

    <div id="memberGrid"></div>
</div>

<script>
    $(document).ready(function () {
        $("#memberGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'memberJsonList')}",
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
                            allowExceptionalUsers: {type: "boolean"},
                            type: {type: "string"},
                            startDate: {type: "string"},
                            endDate: {type: "string"}
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
                    field: "type",
                    title: "${message(code:'twitter.group.membershipType.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "150px"
                },
                {
                    field: "startDate",
                    title: "${message(code:'twitter.group.member.startDate.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "150px"
                },
                {
                    field: "endDate",
                    title: "${message(code:'twitter.group.member.endDate.label')}",
                    attributes: {style: "text-align: center"},
                    headerAttributes: {style: "text-align: center"},
                    width: "150px"
                },
                {
                    command: {text: "${message(code:'cancelMembership')}", click: cancelMemberGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
            ]
        });
    });

    function editMemberGridItem(e) {
        window.location.href = "${createLink(action: 'build')}/" + this.dataItem($(e.currentTarget).closest("tr")).id
    }

    var memberIdForDelete = 0;
    function cancelMemberGridItem(e) {
        if (memberIdForDelete == 0) {
            memberIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.cancel.confirm')}', deleteMemberItem, cancelMemberDelete);
        }

    }

    function cancelMemberDelete() {
        memberIdForDelete = 0;
    }

    function deleteMemberItem() {
        if (memberIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'memberDelete')}',
                data: {id: memberIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#memberGrid').data('kendoGrid');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.cancel.error')}');
                }
            });
            memberIdForDelete = 0;
        }
    }
</script>