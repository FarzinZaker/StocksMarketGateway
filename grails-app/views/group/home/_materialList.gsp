<asset:javascript src="scroll-pagination.js"/>
<div class="dashLet magenta">
    <h2 style="float:right"><i class="fa fa-clock-o"></i> <g:message code="twitter.group.materialList.title"/></h2>
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
                url: '${createLink(action: 'homeJson', id:group.idNumber)}',
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

        %{--materialList.scrollPagination({--}%
        %{--'contentPage': '${createLink(action: 'homeJson', id:group.idNumber)}', // the url you are fetching the results--}%
        %{--'skip': {skip: materialList.children('li').size()}, // these are the variables you can pass to the request, for example: children().size() to know which page you are--}%
        %{--'scrollTarget': $(window), // who gonna scroll? in this example, the full window--}%
        %{--'limit': 10, // it gonna request when scroll is 10 pixels before the page ends--}%
        %{--'beforeLoad': function () { // before load function, you can display a preloader div--}%
        %{--$('#materialLoading').fadeIn();--}%
        %{--},--}%
        %{--'afterLoad': function (elementsLoaded) { // after loading content, you can use this function to animate your new elements--}%
        %{--$('#materialLoading').fadeOut();--}%
        %{--var i = 0;--}%
        %{--$(elementsLoaded).fadeInWithDelay();--}%
        %{--//                if ($('#content').children().size() > 100) { // if more than 100 results already loaded, then stop pagination (only for testing)--}%
        %{--//                    $('#nomoreresults').fadeIn();--}%
        %{--//                    $('#content').stopScrollPagination();--}%
        %{--//                }--}%
        %{--}--}%
        %{--});--}%


    });
</script>