/**
 * Created by farzin on 11/03/2015.
 */

var backTestChart;

var backTestStatus;
var signals;
var portfolioLogs;

var visibleSignals;
var visiblePortfolioLogs;

var dataUrl;
var summaryUrl;

var dataLoadTimer;
var dataShowTimer;

var signalIndex = 0;
var logIndex = 0;

var backTestStartTime;

var dataLoadStatus = 'in-progress';

$(document).ready(function () {
    visibleSignals = signals;
    visiblePortfolioLogs = portfolioLogs;
    signalIndex = visibleSignals.length;
    logIndex = 0;
    for (var i = 0; i < visiblePortfolioLogs.length; i++)
        if (visiblePortfolioLogs[i][1] > 0)
            logIndex = i;

    if (backTestStatus != 'status_finished') {

        dataLoadTimer = $('#dataLoadTimer').timer({
            delay: 1000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                loadTestData();
            }
        });
        dataShowTimer = $('#dataShowTimer').timer({
            delay: 100,
            repeat: true,
            autostart: true,
            callback: function (index) {
                stepForward();
            }
        });
    }

});

function loadTestData() {
    $('#dataLoadTimer').timer('pause');
    for (var i = 0; i < portfolioLogs.length; i++)
        if (portfolioLogs[i][1] > 0)
            backTestStartTime = portfolioLogs[i][0]
    $.ajax({
            dataType: "json",
            url: dataUrl + "?startDate=" + backTestStartTime,
            success: function (data) {
                appendToArray(signals, data.signalList);
                modifyArray(portfolioLogs, data.logs);
                if (data.status == 'status_finished') {
                    dataLoadStatus = 'finished';
                }
                else
                    $('#dataLoadTimer').timer('resume');
            }
        }
    );
}

function stepForward() {
    $('#dataShowTimer').timer('pause');
    if (backTestChart) {
        var dataSource = $("#signalsGrid").data('kendoGrid').dataSource;
        if (logIndex >= portfolioLogs.length && dataLoadStatus == 'finished') {
            dataSource.data = signals;
            dataSource.sync();

            $.ajax({
                type: "POST",
                url: summaryUrl
            }).done(function (response) {

                $('html, body').animate({
                    scrollTop: $('#pageHeader').offset().top
                }, 500, function(){
                    $('#pnlSummary').hide().html(response).slideDown();
                });
            });

            return;
        }

        var currentPortfolioLog = portfolioLogs[logIndex];
        if (currentPortfolioLog && (logIndex == 0 || currentPortfolioLog[1] != 0)) {
            logIndex++;

            var tempList = copyArray(portfolioLogs);
            tempList = tempList.splice(0, logIndex);
            modifyArray(visiblePortfolioLogs, tempList);

            portfolioTotalValue = [];
            portfolioStockValue = [];
            for (i = 0; i < visiblePortfolioLogs.length; i++) {
                portfolioTotalValue.push([
                    visiblePortfolioLogs[i][0],
                    visiblePortfolioLogs[i][1]
                ]);

                portfolioStockValue.push([
                    visiblePortfolioLogs[i][0],
                    visiblePortfolioLogs[i][2]
                ]);
            }
            backTestChart.series[2].setData(portfolioTotalValue);
            backTestChart.series[3].setData(portfolioStockValue);

            while (signals[signalIndex] && signals[signalIndex].time && (signals[signalIndex].time <= visiblePortfolioLogs[logIndex][0])) {
                dataSource.add(signals[signalIndex]);
                dataSource.sync();
                signalIndex++;
            }
        }
    }
    $('#dataShowTimer').timer('resume');
}

function modifyArray(to, from) {
    for (var i = 0; i < from.length; i++)
        for (var j = 0; j < to.length; j++)
            if (to[j][0] == from[i][0])
                to[j] = from[i];
}

function appendToArray(to, from) {
    for (var i = 0; i < from.length; i++)
        if (to.length == 0 || to[to.length - 1].time != from[i].time)
            to.push(from[i])
}

function copyArray(source) {
    var target = [];
    for (var i = 0; i < source.length; i++)
        target.push(source[i]);
    return target;
}