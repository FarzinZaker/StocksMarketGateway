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
    <title><g:message code="monitor.dataSources.title"/></title>
    <asset:javascript src="jquery.plugin.js"/>
    <asset:javascript src="jquery.timer.js"/>
</head>

<body><div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            %{--<h1><g:message code="monitor.dataSources.title"/></h1>--}%

            <div id="timer"></div>

            <div class="k-rtl">
                <div id="grid"></div>
            </div>

            <script>

                function formatJSON(model) {
                    var data = JSON.parse(model.statusData);
                    if (!data)
                        return '-';
                    <g:if test="${params.st != '1'}">
                    if (data.stackTrace)
                        data.stackTrace = 'use ?st=1';
                    </g:if>
                    return '<pre style="direction:ltr;text-align:left;">' + JSON.stringify(data, null, 4) + '</pre>';
                }

                $(document).ready(function () {
                    $("#grid").kendoGrid({

                        dataSource: {
                            transport: {
                                type: 'odata',
                                read: {
                                    url: "${createLink(action: 'dataSourcesJson')}",
                                    dataType: "json",
                                    type: "POST"

                                }
                            },
                            schema: {
                                model: {
                                    fields: {
                                        id: {type: "string"},
                                        jobName: {type: "string"},
                                        previousFireDate: {type: "string"},
                                        previousFireTime: {type: "string"},
                                        nextFireDate: {type: "string"},
                                        nextFireTime: {type: "string"},
                                        timesTriggered: {type: "string"},
                                        status: {type: "boolean"},
                                        statusData: {type: "string"}
                                    }
                                },
                                data: "data",
                                total: "total"
                            }
                        },
                        columns: [
                            {
                                field: "status",
                                title: "${message(code:'monitor.dataSources.status.label')}",
                                template: "<img src='#: status ? \'${assetPath(src: 'monitor/healthy.png')}\' : \'${assetPath(src: 'monitor/error.png')}\' #'/>"
                            },
                            {
                                field: "jobName",
                                title: "${message(code:'monitor.dataSources.jobName.label')}"
                            },
                            {
                                field: "previousFireDate",
                                title: "${message(code:'monitor.dataSources.previousFireDate.label')}"
                            },
                            {
                                field: "previousFireTime",
                                title: "${message(code:'monitor.dataSources.previousFireTime.label')}"
                            },
                            {
                                field: "nextFireDate",
                                title: "${message(code:'monitor.dataSources.nextFireDate.label')}"
                            },
                            {
                                field: "nextFireTime",
                                title: "${message(code:'monitor.dataSources.nextFireTime.label')}"
                            },
                            {
                                field: "timesTriggered",
                                title: "${message(code:'monitor.dataSources.timesTriggered.label')}"
                            },
                            {
                                field: "statusData",
                                title: "${message(code:'monitor.dataSources.statusData.label')}",
                                template: "#= formatJSON(data) #",
                                width: "500px"
                            },
                            {
                                command: {text: "${message(code:'restart')}", click: restartJob},
                                title: "",
                                width: "124px",
                                headerAttributes: {style: "text-align: center"}
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
                            var grid = $('#grid').data('kendoGrid');
                            grid.dataSource.read();
                            grid.refresh();
                        }
                    });
                });

                var idForRestart = '';
                function restartJob(e) {
                    if (idForRestart == '') {
                        idForRestart = this.dataItem($(e.currentTarget).closest("tr")).id;
                        confirm('${message(code:'job.restart.confirm')}', restart, cancelRestart);
                    }

                }

                function cancelRestart() {
                    idForRestart = '';
                }

                function restart() {
                    if (idForRestart != '') {
                        $.ajax({
                            type: "POST",
                            url: '${createLink(action: 'restartJob')}',
                            data: {name: idForRestart}
                        }).done(function (response) {
                            if (response != "1") {
                                window.alert('${message(code:'job.restart.error')}');
                            }
                        });
                        idForRestart = '';
                    }
                }

            </script>
        </div>
    </div>
</div>
</body>
</html>