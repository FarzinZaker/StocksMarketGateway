<asset:javascript src="scroll-pagination.js"/>
<asset:javascript src="jquery.plugin.js"/>
<asset:javascript src="jquery.timer.js"/>
<div class="dashLet white">
    <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="twitter.home.materialList.title"/></h2>
    <form:loading id="newMaterialTimer"/>
    %{--<form:loading id="newMaterialLoading"/>--}%
    <ul class="clear-fix materialList" id="material-list">

    </ul>

    <div class="scroll-pager"></div>
    <form:loading id="materialLoading"/>
</div>

<script type="text/javascript">

    var materialListMinDate = ${new Date().time};
    var materialListMaxDate = ${new Date().time};
    var materialListPageSize = 10;
    var materialListStillLoading = false;
    $('#materialLoading').show();

    function loadOldMaterials() {
        if (!materialListStillLoading) {
            materialListStillLoading = true;
            $('#materialLoading').show();
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'homeOldJson')}',
                data: {minDate: materialListMinDate, limit: 1000}
            }).done(function (response) {
                $('#materialLoading').hide();
                $.each(response.data, function () {
                    $('#material-list').append('<li>' + this + '<div class="clear-fix"></div></li>');
                });
                if (response.data.length > 0) {
                    materialListStillLoading = false;
                    if (isElementInViewport($('#material-list + .scroll-pager')[0]))
                        loadOldMaterials();
                    if (response.minDate < materialListMinDate)
                        materialListMinDate = response.minDate;
                    if (response.maxDate > materialListMaxDate)
                        materialListMaxDate = response.maxDate;
                }
            });
        }
    }
    function loadNewMaterials() {
//        $('#newMaterialLoading').show();
        $.ajax({
            type: "POST",
            url: '${createLink(action: 'homeNewJson')}',
            data: {maxDate: materialListMaxDate, limit: materialListPageSize}
        }).done(function (response) {
//            $('#newMaterialLoading').hide();
            $.each(response.data, function () {
                $('#material-list').prepend('<li>' + this + '<div class="clear-fix"></div></li>');
            });
            if (response.data.length > 0) {
                if (response.minDate < materialListMinDate)
                    materialListMinDate = response.minDate;
                if (response.maxDate > materialListMaxDate)
                    materialListMaxDate = response.maxDate;
            }
        });
    }

    $(document).ready(function () {
        $(window).on('DOMContentLoaded load resize scroll', function () {
            onVisibilityChange($('#material-list + .scroll-pager'), loadOldMaterials)
        });



        $('#newMaterialTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                var timer = $('#newMaterialTimer');
                timer.timer('stop');
                loadNewMaterials();
                timer.timer('start');
            }
        });
    });
</script>