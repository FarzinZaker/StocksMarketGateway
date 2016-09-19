<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="twitter.group.manageMembers.title"/></title>
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
                    [text: '<i class="fa fa-user"></i> ' + message(code: "twitter.group.manageMembers.title"), url: createLink(action: 'manageMembers', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <div class="memberGridContainer k-rtl">

                <div id="memberGrid"></div>
            </div>
        </div>
    </div>
</div>

<div class="k-rtl hidden">
    <div id="addMemberWindow">
        <form:form name="addMemberForm">
            <form:field fieldName="member">
                <form:textBox name="member.id" id="member" validation="required" style="width:290px"/>

                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {
                        $("#member").removeClass('k-textbox').width('280').kendoComboBox({
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
            <form:datePickerResources/>
            <form:field fieldName="member.startDate">
                <form:datePicker name="startDate" value="${format.jalaliDate(date: new Date())}"
                                 style="width:140px;" placeholder="${message(code: 'fromDate')}"/>
                <form:datePicker name="endDate" style="width:140px;" placeholder="${message(code: 'toDate')}"/>
            </form:field>
            <div class="toolbar" style="margin-top:10px;margin-bottom:0;">
                <form:button name="submit" text="${message(code: 'add')}" style="float:right"
                             onclick="saveMember();"/>
                <form:button name="cancel" text="${message(code: 'close')}" style="float:left"
                             onclick="closeMemberWindow();"/>
            </div>
        </form:form>
    </div>

    <div id="inviteMemberWindow">
        <form:form name="inviteMemberForm">
            <form:field fieldName="member">
                <form:textBox name="member.id" id="invitedMember" validation="required" style="width:290px"/>

                <script language="javascript" type="text/javascript">
                    $(document).ready(function () {
                        $("#invitedMember").removeClass('k-textbox').width('280').kendoComboBox({
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
            <form:datePickerResources/>
            <form:field fieldName="member.startDate">
                <form:datePicker name="startDate" id="inviteStartDate" value="${format.jalaliDate(date: new Date())}"
                                 style="width:140px;" placeholder="${message(code: 'fromDate')}"/>
                <form:datePicker name="endDate" id="inviteEndDate" style="width:140px;"
                                 placeholder="${message(code: 'toDate')}"/>
            </form:field>
            <div class="toolbar" style="margin-top:10px;margin-bottom:0;">
                <form:button name="submit" text="${message(code: 'add')}" style="float:right"
                             onclick="saveInviteMember();"/>
                <form:button name="cancel" text="${message(code: 'close')}" style="float:left"
                             onclick="closeInviteMemberWindow();"/>
            </div>
        </form:form>
    </div>
</div>


<script>

    var wnd;

    function saveMember() {
        if ($('#addMemberForm').isValid())
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'addMember')}',
                data: {
                    id: '${params.id}',
                    memberId: $('#member').data('kendoComboBox').value(),
                    startDate: $('#startDate').val(),
                    endDate: $('#endDate').val()
                }
            }).done(function (response) {
                var grid = $('#memberGrid').data('kendoGrid');
                grid.dataSource.read();
                grid.refresh();
                wnd.close();
            });
    }

    function closeMemberWindow() {
        wnd.close();
    }
    function addMember() {
        $('#member').data('kendoComboBox').value('');
        if (!wnd)
            wnd = $("#addMemberWindow")
                    .kendoWindow({
                        title: "${message(code: 'twitter.group.members.add')}",
                        modal: true,
                        visible: false,
                        resizable: false,
                        width: 650
                    }).data("kendoWindow");
        wnd.center().open();
    }

    var wndInvite;

    function saveInviteMember() {
        if ($('#inviteMemberForm').isValid())
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'inviteMember')}',
                data: {
                    id: '${params.id}',
                    memberId: $('#invitedMember').data('kendoComboBox').value(),
                    startDate: $('#inviteStartDate').val(),
                    endDate: $('#inviteEndDate').val()
                }
            }).done(function (response) {
                var grid = $('#memberGrid').data('kendoGrid');
                grid.dataSource.read();
                grid.refresh();
                wndInvite.close();
            });
    }

    function closeInviteMemberWindow() {
        wndInvite.close();
    }
    function inviteMember() {
        $('#member').data('kendoComboBox').value('');
        if (!wndInvite)
            wndInvite = $("#inviteMemberWindow")
                    .kendoWindow({
                        title: "${message(code: 'twitter.group.members.invite')}",
                        modal: true,
                        visible: false,
                        resizable: false,
                        width: 650
                    }).data("kendoWindow");
        wndInvite.center().open();
    }

    $(document).ready(function () {
        $("#memberGrid").kendoGrid({

            dataSource: {
                transport: {
                    type: 'odata',
                    read: {
                        url: "${createLink(action: 'manageMembersJsonList', id: params.id)}",
                        dataType: "json",
                        type: "POST"

                    }
                },
                schema: {
                    model: {
                        fields: {
                            id: {type: "string"},
                            title: {type: "string"},
                            startDate: {type: "string"},
                            endDate: {type: "string"},
                            type: {type: "string"}
                        }
                    },
                    data: "data", // records are returned in the "data" field of the response
                    total: "total"
                },
                pageSize: 20,
                serverPaging: true,
                serverFiltering: false,
                serverSorting: false
            },
            filterable: false,
            sortable: false,
            pageable: true,
            <g:if test="${group.allowExceptionalUsers}">
            toolbar: [
                {
                    template: "<a class='k-button k-button-icontext k-grid-add' href='javascript:addMember();'>${message(code: 'twitter.group.members.add')}</a>"
                }
            ],
            </g:if>
            <g:if test="${group.membershipType == 'closed'}">
            toolbar: [
                {
                    template: "<a class='k-button k-button-icontext k-grid-add' href='javascript:inviteMember();'>${message(code: 'twitter.group.members.invite')}</a>"
                }
            ],
            </g:if>
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
                    title: "${message(code:'twitter.group.member.title.label')}"
                },
                {
                    field: "startDate",
                    title: "${message(code:'twitter.group.member.startDate.label')}"
                },
                {
                    field: "endDate",
                    title: "${message(code:'twitter.group.member.endDate.label')}"
                },
                {
                    field: "type",
                    title: "${message(code:'twitter.group.member.type.label')}"
                },
                <g:if test="${group.allowExceptionalUsers}">
                {
                    command: {text: "${message(code:'remove')}", click: removeMemberGridItem},
                    title: "",
                    width: "85px",
                    headerAttributes: {style: "text-align: center"}
                }
                </g:if>
            ]
        });
    });


    var memberIdForDelete = 0;
    function removeMemberGridItem(e) {
        if (memberIdForDelete == 0) {
            memberIdForDelete = this.dataItem($(e.currentTarget).closest("tr")).id;
            confirm('${message(code:'twitter.group.member.delete.confirm')}', deleteMemberItem, cancelMemberDelete);
        }

    }

    function cancelMemberDelete() {
        memberIdForDelete = 0;
    }

    function deleteMemberItem() {
        if (memberIdForDelete != 0) {
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'deleteMember')}',
                data: {id: '${params.id}', memberId: memberIdForDelete}
            }).done(function (response) {
                if (response == "1") {
                    var grid = $('#memberGrid').data('kendoGrid');
                    grid.dataSource.read();
                    grid.refresh();
                }
                else {
                    window.alert('${message(code:'twitter.group.member.delete.error')}');
                }
            });
            memberIdForDelete = 0;
        }
    }
</script>
</body>
</html>