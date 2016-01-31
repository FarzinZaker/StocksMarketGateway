%{--<div class="rocketchat-widget" data-state="closed">--}%
%{--<div class="rocketchat-container">--}%
%{--<iframe id="rocketchat-iframe" allowtransparency="true"--}%
%{--style=""--}%
%{--src="http://www.4tablo.ir:3000/livechat">--}%
%{--</iframe>--}%
%{--</div>--}%
%{--</div>--}%

<!-- Start of Rocket.Chat Livechat Script -->
%{--<script type="text/javascript">--}%
%{--(function(w, d, s, f, u) {--}%
%{--w[f] = w[f] || [];--}%
%{--w[f].push(u);--}%
%{--var h = d.getElementsByTagName(s)[0],--}%
%{--j = d.createElement(s);--}%
%{--j.async = true;--}%
%{--j.src = 'http://www.4tablo.ir:3000/packages/rocketchat_livechat/assets/rocket-livechat.js';--}%
%{--h.parentNode.insertBefore(j, h);--}%
%{--})(window, document, 'script', 'initRocket', 'http://www.4tablo.ir:3000/livechat');--}%
%{--</script>--}%
<!-- End of Rocket.Chat Livechat Script -->
<div class="k-rtl onlineSupportContainer">
    <div id="onlineSupportWindow"></div>
</div>

<script language="javascript" type="text/javascript">
    function openOnlineSupport() {
        var container = $("#onlineSupportWindow");
        if (!container.data('kendoWindow')) {
            container.kendoWindow({
                iframe: true,
                modal: true,
                width: "500px",
                height: "300px",
                actions  : ["Minimize", "Maximize", "Close"],
                title: "${message(code:'support.online')}",
                //data: { startdate: urldate, eeid: eeid, deptid: deptid },
                content: "http://www.4tablo.ir:3000/livechat",
                type: "GET"
            });
            FixWindowPos(container);
        }
        container.data('kendoWindow').center().open();
    }
</script>