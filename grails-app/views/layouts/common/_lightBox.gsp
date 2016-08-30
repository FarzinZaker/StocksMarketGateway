<div id="imageWindow">
    <div class="imagePopup">
    </div>
    <i class="fa fa-close"></i>
</div>

<script language="javascript" type="text/javascript">
    var imageWindow;
    $(document).ready(function () {
        $('.materialList').on('click', 'li .description img', function () {
            var image = $(this);
            var content = image.parent();
            while (!content.hasClass('description'))
                content = content.parent();
            if(content.find('.description').length > 0)
                content = content.find('.description');
            console.log(content.html());
            var winElement = $('#imageWindow');
            var container = winElement.find('div');
            container.html(content.html());
            $.each(container.find('img'), function (index, item) {
                if (image.attr('src') == $(item).attr('src'))
                    $(item).remove();
            });
            container.html('<div class="realImageContainer"><img src="' + image.attr('src') + '"/></div>' + container.html());

            imageWindow = winElement.kendoWindow({
                modal: true,
                title: false
            }).data('kendoWindow')
                    .center().open();
            imageWindow.bind("refresh", function () {
                imageWindow.center();
                imageWindow.open();
            });
        });
        $('#imageWindow').find('.fa-close').click(function(){
            imageWindow.close();
        });
    });
</script>