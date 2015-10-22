<asset:javascript src="scroll-pagination.js"/>
<div class="dashLet magenta">
    <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="twitter.home.materialList.title"/></h2>
    <ul class="clear-fix materialList" id="material-list">

    </ul>

    <div class="scroll-pager"></div>
    <form:loading id="materialLoading"/>
</div>

<script type="text/javascript">

    var materialListCurrentPage = 0;
    var materialListPageSize = 10;
    var materialListStillLoading = false;
    $('#materialLoading').show();

    function loadMoreMaterials() {
        if (!materialListStillLoading) {
            materialListStillLoading = true;
            $('#materialLoading').show();
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'homeJson')}',
                data: {skip: (materialListCurrentPage++) * materialListPageSize, limit: materialListPageSize}
            }).done(function (response) {
                $('#materialLoading').hide();
                $.each(response, function () {
                    $('#material-list').append('<li>' + this + '<div class="clear-fix"></div></li>');
                });
                if (response.length > 0) {
                    materialListStillLoading = false;
                    if (isElementInViewport($('#material-list + .scroll-pager')[0]))
                        loadMoreMaterials();
                }
            });
        }
    }

    $(document).ready(function () {


        $(window).on('DOMContentLoaded load resize scroll', function () {
            onVisibilityChange($('#material-list + .scroll-pager'), loadMoreMaterials)
        });


    });
</script>