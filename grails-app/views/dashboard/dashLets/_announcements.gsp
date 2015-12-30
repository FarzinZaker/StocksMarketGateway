<div class="dashLet pink dark noPadding hideBeforeLoad noPlaceReservation">
    <h2 style="float:right"><i class="fa fa-bullhorn"></i> <g:message code="announcements.title"/></h2>

    <div id="announcementsTimer"></div>

    <div class="k-rtl clear-fix">
        <div id="announcementTabstrip">
            <ul>
                <li class="k-state-active">
                    <g:message code="announcements.codal.title"/>
                </li>
                <li>
                    <g:message code="announcements.supervisorMessage.title"/>
                </li>
            </ul>

            <div class="noPadding">
                <g:render template="dashLets/announcements/codal"/>
            </div>

            <div class="noPadding">
                <g:render template="dashLets/announcements/supervisorMessgae"/>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function loadAnnouncements() {
        $.ajax({
            type: "POST",
            url: '${createLink(action: 'announcements')}?t=' + new Date().getTime()
        }).done(function (data) {
            $('#announcementsTimer').timer('start');
            fillCodal(data.codal);
            fillSupervisorMessages(data.supervisorMessages);
        });
    }

    $(document).ready(function () {

        $('#announcementTabstrip li + li').data('config', {
            margin: 5,
            padding: 0
        });

        $("#announcementTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "none"
                }
            },
            show: function (e) {
                var container = $('#' + $(e.item).attr('aria-controls')).find('.announcementFeedItems');
                container.find('.mix').css('opacity', 0).animate({'opacity': 1}, 700);
                container.animatedSort($(e.item).data('config'));
                $('html, body').stop().animate({
                    scrollTop: container.parent().parent().parent().parent().parent().offset().top - 120
                }, 1000);
            }
        });
        $('#announcementsTimer').timer({
            delay: 10000,
            repeat: true,
            autostart: true,
            callback: function (index) {
                $('#announcementsTimer').timer('stop');
                loadAnnouncements();
            }
        });
        loadAnnouncements();
    });
</script>