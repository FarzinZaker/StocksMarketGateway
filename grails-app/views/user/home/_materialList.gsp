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
<div class="modal fade" tabindex="-1" role="dialog" id="imageModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">-</h4>
            </div>
            <div class="modal-body">
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">

    var materialListMinDate = ${new Date().time};
    var materialListMaxDate = ${new Date().time};
    var materialListPageSize = 10;
    var materialListStillLoading = false;
    $('#materialLoading').show();
    function showLargeImage(img){
        $('#imageModal .modal-body').html('<img src="'+$(img).attr('src')+'">')
        $('#imageModal').modal('show')
    }
    function loadOldMaterials() {
        if (!materialListStillLoading) {
            materialListStillLoading = true;
            $('#materialLoading').show();
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'homeOldJson')}',
                data: {minDate: materialListMinDate, limit: 50}
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
            delay: 60000,
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