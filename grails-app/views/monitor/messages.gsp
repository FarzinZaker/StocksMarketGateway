<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 9/6/14
  Time: 5:55 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="monitor.messages.title"/></title>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
</head>

<body><div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            %{--<h1><g:message code="monitor.messages.title"/></h1>--}%

            <div id="timer"></div>

            <div class="k-rtl">
                <div id="tabstrip">
                    <ul>
                        <li class="k-state-active">
                            <g:message code="monitor.messages.queued.title"/>
                        </li>
                        <li>
                            <g:message code="monitor.messages.sent.title"/>
                        </li>
                        <li>
                            <g:message code="monitor.messages.failed.title"/>
                        </li>
                    </ul>

                    <div>
                        <div id="grid-queued"></div>
                    </div>

                    <div>
                        <div id="grid-sent"></div>
                    </div>

                    <div>
                        <div id="grid-failed"></div>
                    </div>
                </div>
            </div>

            <script>
                $(document).ready(function () {
                    $("#tabstrip").kendoTabStrip({
                        animation: {
                            open: {
                                effects: "fadeIn"
                            }
                        }
                    });
                    $("#grid-queued").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'queuedMessagesJson')}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: { type: "string" },
                                        receiverNumber: { type: "string" },
                                        user: { type: "string" },
                                        timeCreated: { type: "string" },
                                        dateCreated: { type: "string" },
                                        retryCount: { type: "integer" },
                                        lastExecutionMessage: { type: "string" },
                                        body: { type: "string" }
                                    }
                                },
                                data: "data",
                                total: "total"
                            }
                        },
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'monitor.messages.id.label')}"
                            },
                            {
                                field: "receiverNumber",
                                title: "${message(code:'monitor.messages.receiverNumber.label')}"
                            },
                            {
                                field: "user",
                                title: "${message(code:'monitor.messages.user.label')}"
                            },
                            {
                                field: "timeCreated",
                                title: "${message(code:'monitor.messages.timeCreated.label')}"
                            },
                            {
                                field: "dateCreated",
                                title: "${message(code:'monitor.messages.dateCreated.label')}"
                            } ,
                            {
                                field: "retryCount",
                                title: "${message(code:'monitor.messages.retryCount.label')}"
                            } ,
                            {
                                field: "lastExecutionMessage",
                                title: "${message(code:'monitor.messages.lastExecutionMessage.label')}"
                            }   ,
                            {
                                field: "body",
                                title: "${message(code:'monitor.messages.body.label')}" ,
                                width: "30%"
                            }
                        ]
                    });

                    $("#grid-sent").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'sentMessagesJson')}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: { type: "string" },
                                        receiverNumber: { type: "string" },
                                        user: { type: "string" },
                                        timeCreated: { type: "string" },
                                        dateCreated: { type: "string" },
                                        retryCount: { type: "integer" },
                                        lastExecutionMessage: { type: "string" },
                                        body: { type: "string" }
                                    }
                                },
                                data: "data",
                                total: "total"
                            }
                        },
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'monitor.messages.id.label')}"
                            },
                            {
                                field: "receiverNumber",
                                title: "${message(code:'monitor.messages.receiverNumber.label')}"
                            },
                            {
                                field: "user",
                                title: "${message(code:'monitor.messages.user.label')}"
                            },
                            {
                                field: "timeCreated",
                                title: "${message(code:'monitor.messages.timeCreated.label')}"
                            },
                            {
                                field: "dateCreated",
                                title: "${message(code:'monitor.messages.dateCreated.label')}"
                            } ,
                            {
                                field: "retryCount",
                                title: "${message(code:'monitor.messages.retryCount.label')}"
                            } ,
                            {
                                field: "lastExecutionMessage",
                                title: "${message(code:'monitor.messages.lastExecutionMessage.label')}"
                            }   ,
                            {
                                field: "body",
                                title: "${message(code:'monitor.messages.body.label')}",
                                width: "30%"
                            }
                        ]
                    });

                    $("#grid-failed").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'failedMessagesJson')}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: { type: "string" },
                                        receiverNumber: { type: "string" },
                                        user: { type: "string" },
                                        timeCreated: { type: "string" },
                                        dateCreated: { type: "string" },
                                        retryCount: { type: "integer" },
                                        lastExecutionMessage: { type: "string" },
                                        body: { type: "string" }
                                    }
                                },
                                data: "data",
                                total: "total"
                            }
                        },
                        columns: [
                            {
                                field: "id",
                                title: "${message(code:'monitor.messages.id.label')}"
                            },
                            {
                                field: "receiverNumber",
                                title: "${message(code:'monitor.messages.receiverNumber.label')}"
                            },
                            {
                                field: "user",
                                title: "${message(code:'monitor.messages.user.label')}"
                            },
                            {
                                field: "timeCreated",
                                title: "${message(code:'monitor.messages.timeCreated.label')}"
                            },
                            {
                                field: "dateCreated",
                                title: "${message(code:'monitor.messages.dateCreated.label')}"
                            } ,
                            {
                                field: "retryCount",
                                title: "${message(code:'monitor.messages.retryCount.label')}"
                            } ,
                            {
                                field: "lastExecutionMessage",
                                title: "${message(code:'monitor.messages.lastExecutionMessage.label')}"
                            }   ,
                            {
                                field: "body",
                                title: "${message(code:'monitor.messages.body.label')}" ,
                                width: "30%"
                            }
                        ]
                    });
                });

                $(document).ready(function () {
                    $('#timer').timer({
                        delay: 2000,
                        repeat: true,
                        autostart: true,
                        callback: function (index) {
                            var grid = $('#grid-queued').data('kendoGrid');
                            grid.dataSource.read();
                            grid.refresh();

                            grid = $('#grid-sent').data('kendoGrid');
                            grid.dataSource.read();
                            grid.refresh();

                            grid = $('#grid-failed').data('kendoGrid');
                            grid.dataSource.read();
                            grid.refresh();
                        }
                    });
                });

            </script>
        </div>
    </div>
</div>
</body>
</html>