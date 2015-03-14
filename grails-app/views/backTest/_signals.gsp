<h2><g:message code="backTest.signals.title"/></h2>
<div class="k-rtl">
    <div id="signalsGrid"></div>
</div>
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
                            effect: {type: "number"}

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