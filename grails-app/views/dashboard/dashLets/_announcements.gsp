<div class="dashLet crimson">
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

            <div>
                <g:render template="dashLets/announcements/codal"/>
            </div>

            <div>
                <g:render template="dashLets/announcements/supervisorMessgae"/>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">

    function loadAnnouncements(){

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
        $("#announcementTabstrip").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
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