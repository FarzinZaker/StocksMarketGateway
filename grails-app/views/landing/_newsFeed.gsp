<script language="javascript" type="text/javascript">

    function parseRSS() {
        $.ajax({
            url: '${createLink(controller: 'feed', action: 'news')}',
            success: function (data) {
                window.alert(data)
            }
        });
    }
    parseRSS();
</script>