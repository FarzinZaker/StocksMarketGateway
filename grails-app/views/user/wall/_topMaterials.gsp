<div class="topsLet white hideBeforeLoad noPlaceReservation">
    <h2 style="float:right"><i class="fa fa-line-chart"></i> <g:message code="topMaterials.title"/></h2>

    <div class="headerItem">
        <form:loading id="topMaterialsLoading"/>
        <form:select name="topMaterialsPeriod" value="7"
                     items="${[1, 7, 30, 0].collect { [text: message(code: "tops.period.${it}"), value: it] }}"
                     style="width:80px;" onchange="loadTopMaterials"/>
    </div>

    <div id="topMaterialsTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="topMaterialsTabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="topMaterials.topScored.title"/>
                <li>
                    <g:message code="topMaterials.mostVisited.title"/>
                </li>
                <li>
                    <g:message code="topMaterials.topRated.title"/>
                </li>
                <li>
                    <g:message code="topMaterials.mostCommented.title"/>
                </li>
            </ul>

            <div class="k-state-active">
                <ul id="topMaterials_topScored" class="materialList">

                </ul>
            </div>

            <div>
                <ul id="topMaterials_mostVisited" class="materialList">
                </ul>

            </div>

            <div>
                <ul id="topMaterials_topRated" class="materialList">
                </ul>

            </div>

            <div>
                <ul id="topMaterials_mostCommented" class="materialList">
                </ul>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $("#topMaterialsTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            }
        });
        $('#topMaterialsTimer').timer({
            delay: 120000,
            repeat: true,
            autostart: false,
            callback: function (index) {
                $('#topMaterialsTimer').timer('stop');
                loadTopMaterials();
            }
        });
        loadTopMaterials();
    });

    function loadTopMaterials() {
        $('#topMaterialsLoading').show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'user', action: 'topMaterials', id: groupId)}',
            data: {period: $('#topMaterialsPeriod').data('kendoComboBox').value()}
        }).done(function (response) {
            $('#topMaterials_topScored').html(response.topScored);
            $('#topMaterials_mostVisited').html(response.mostVisited);
            $('#topMaterials_topRated').html(response.topRated);
            $('#topMaterials_mostCommented').html(response.mostCommented);
            $('#topMaterialsLoading').hide();
            $('#topMaterialsTimer').timer('start');
        });

    }
</script>