<%@ page import="grails.util.Environment" %>
<h2><g:message code="backTest.signals.title"/></h2>

<div class="k-rtl">
    <div id="signalsGrid"></div>
</div>
<script type="text/x-kendo-template" id="signalTemplate">

<div class="container-fluid">
    <div class="row-fluid">
        <if test="${Environment.isDevelopmentMode()}">
            <div class="col-xs-4"><label>#= formatNumber(id) #</label></div>
        </if>

        <div class="col-xs-4"><g:message
                code="backTest.signal.totalTradeCount"/>: <label>#= formatNumber(totalTradeCount) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.totalTradeVolume"/>: <label>#= formatNumber(totalTradeVolume) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.totalTradeValue"/>: <label>#= formatNumber(totalTradeValue) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.closingPrice"/>: <label>#= formatNumber(closingPrice) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.firstTradePrice"/>: <label>#= formatNumber(firstTradePrice) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.lastTradePrice"/>: <label>#= formatNumber(lastTradePrice) #</label></div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.priceChange"/>: <label>#= formatNumber(priceChange) #</label></div>

        <div class="col-xs-4"><g:message code="backTest.signal.minPrice"/>: <label>#= formatNumber(minPrice) #</label>
        </div>

        <div class="col-xs-4"><g:message code="backTest.signal.maxPrice"/>: <label>#= formatNumber(maxPrice) #</label>
        </div>

        <div class="col-xs-4"><g:message
                code="backTest.signal.yesterdayPrice"/>: <label>#= formatNumber(yesterdayPrice) #</label></div>
        <g:each in="${indicators}" var="indicator">
            <div class="col-xs-4"><span class="ltr inline-block"><g:message code="${indicator.name}"/><g:if
                    test="${indicator.parameter}">(${indicator.parameter})</g:if></span>: <label>#= formatNumber(Math.round(${indicator.name.replace('.', '_')}_${indicator.parameter})) #</label>
            </div>
        </g:each>
    </div>
</div>
</script>
<script>

    function formatEffect(model) {
        if (model.effect > 0) {
            return Math.abs(model.effect).toString().replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    }) + "<i class='fa fa-icon fa-arrow-up' style='color: green;float:left;line-height:22px;'></i>";
        } else if (model.effect < 0) {
            return Math.abs(model.effect).toString().replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    }) + "<i class='fa fa-icon fa-arrow-down' style='color: red;float:left;;line-height:22px;'></i>";
        } else {
            return "-";
        }
    }

    var signalsGrid;
    $(document).ready(function () {
        signalsGrid = $("#signalsGrid").kendoGrid({
            dataSource: {
                data: signals,
                schema: {
                    model: {
                        fields: {
                            id: {type: "number"},
                            date: {type: "string"},
                            reason: {type: "string"},
                            price: {type: "number"},
                            stockCount: {type: "number"},
                            value: {type: "number"},
                            wage: {type: "number"},
                            tax: {type: "number"},
                            //details
                            totalTradeCount: {type: "number"},
                            totalTradeVolume: {type: "number"},
                            totalTradeValue: {type: "number"},
                            closingPrice: {type: "number"},
                            firstTradePrice: {type: "number"},
                            lastTradePrice: {type: "number"},
                            priceChange: {type: "number"},
                            minPrice: {type: "number"},
                            maxPrice: {type: "number"},
                            yesterdayPrice: {type: "number"}
                            <g:each in="${indicators}" var="indicator">
                            , ${indicator.name.replace('.','_')}_${indicator.parameter}: {type: "number"}
                            </g:each>
                        }
                    }
                },
                sort: {
                    field: "date",
                    dir: "desc"
                },
                pageSize: 10
            },
            scrollable: false,
            sortable: true,
            filterable: false,
            pageable: true,
            detailTemplate: kendo.template($("#signalTemplate").html()),
            columns: [
                {field: "date", title: "${message(code:'backTest.signal.date')}"},
                {field: "reason", title: "${message(code:'backTest.signal.reason')}"},
                {field: "price", title: "${message(code:'backTest.signal.price')}", format: "{0:n0}"},
                {field: "stockCount", title: "${message(code:'backTest.signal.stockCount')}", format: "{0:n0}"},
                {field: "value", title: "${message(code:'backTest.signal.value')}", format: "{0:n0}"},
                {field: "wage", title: "${message(code:'backTest.signal.wage')}", format: "{0:n0}"},
                {field: "tax", title: "${message(code:'backTest.signal.tax')}", format: "{0:n0}"},
                {
                    field: "effect",
                    title: "${message(code:'backTest.signal.effect')}",
                    template: "#= formatEffect(data) #"
                }
            ]
        });
    });

</script>