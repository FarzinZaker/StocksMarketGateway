<div class="container-fluid" style="padding:0">
    <div class="row-fluid" id="suggestListContainer">

    </div>
</div>

<twitter:followScript/>

<script language="javascript" type="text/javascript">

    function removeFollowableFromList(btn, id) {
        var item = $('.followable_' + id.replace(':', '_'))
        var container = item.parent();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'twitter', action: 'suggest')}',
            data: {skip: suggestListSkip, limit: 1},
            dataType: 'json'
        }).done(function (response) {
            $.each(response, function () {
                var newItemHtml = this;
                item.fadeOut(function () {
                    container.html(newItemHtml);
                    container.find('.followable').fadeIn();
                });
            });
            suggestListSkip++;
        });
    }


    var suggestListSkip = 0;
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'twitter', action: 'suggest')}',
            data: {skip: suggestListSkip, limit: 3},
            dataType: 'json'
        }).done(function (response) {
            $.each(response, function () {
                $('#suggestListContainer').append('<div class="col-sm-4">' + this + '</div>');
            });
            suggestListSkip += response.length;
        });
    });
</script>

