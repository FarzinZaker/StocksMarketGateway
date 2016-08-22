<%@ page import="stocks.User" %>
<form class="talk" id="talkForm">
    <div class="infoBar">
        <img class="userImage"
             src="${createLink(controller: 'image', action: 'profile', params: [id: User.findByUsername(sec.username() as String)?.id, size: 100])}"/>

        <div class="toolbar">
            <form:button onclick="submitTalk();" text="${message(code: 'talk.button')}"/>
            <form:loading/>
        </div>
    </div>
    <twitter:tagSearch/>
    <twitter:authorSearch/>
    <form:editor name="body" mode="inline" height="136" hashTag="true" authorTag="true" tag="${tag}"/>
    <div class="clear-fix"></div>

    <div class="twit-prediction">
        <div class="extractedTagsList"></div>

        <div class="predictionOptions hidden">
            <div class="btn-group prediction-type" data-toggle="buttons">
                <label class="btn btn-primary" onclick="toggleTalkPrediction(this)">
                    <input type="radio" name="predictionType" value="benefit" autocomplete="off">
                    <g:message code="twitter.prediction.type.benefit"/>
                </label>
                <label class="btn btn-primary" onclick="toggleTalkPrediction(this)">
                    <input type="radio" name="predictionType" value="loss" autocomplete="off">
                    <g:message code="twitter.prediction.type.loss"/>
                </label>
                <label class="btn btn-danger hidden active" onclick="toggleTalkPrediction(this)">
                    <input type="radio" name="predictionType" value="-" id="cancelPrediction" autocomplete="off" checked>
                    <g:message code="twitter.prediction.type.cancel"/>
                </label>
            </div>

            <div class="predictionDetails hidden">
                <div class="btn-group prediction-period" data-toggle="buttons">
                    <label class="btn btn-primary disabled">
                        <g:message code="twitter.prediction.period.label"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="1d" autocomplete="off">
                        <g:message code="twitter.prediction.period.1day"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="1w" autocomplete="off">
                        <g:message code="twitter.prediction.period.1week"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="4w" autocomplete="off">
                        <g:message code="twitter.prediction.period.4weeks"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="12w" autocomplete="off">
                        <g:message code="twitter.prediction.period.12weeks"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="26w" autocomplete="off">
                        <g:message code="twitter.prediction.period.26weeks"/>
                    </label>
                </div>
            </div>

            <div class="predictionDetails hidden">
                <div class="btn-group prediction-riskLevel" data-toggle="buttons">
                    <label class="btn btn-primary disabled">
                        <g:message code="twitter.prediction.riskLevel.label"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionRiskLevel" value="1" autocomplete="off">
                        <g:message code="twitter.prediction.riskLevel.1"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionRiskLevel" value="2" autocomplete="off">
                        <g:message code="twitter.prediction.riskLevel.2"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionRiskLevel" value="3" autocomplete="off">
                        <g:message code="twitter.prediction.riskLevel.3"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionRiskLevel" value="4" autocomplete="off">
                        <g:message code="twitter.prediction.riskLevel.4"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionRiskLevel" value="5" autocomplete="off">
                        <g:message code="twitter.prediction.riskLevel.5"/>
                    </label>
                </div>
            </div>
        </div>
    </div>

    <div class="clear-fix"></div>
</form>
<script language="javascript" type="text/javascript">
    function submitTalk() {
        var content = strip(tinymce.activeEditor.getContent()).trim();
        if (content == '') {
            window.alert('${message(code:'talk.error.empty')}');
            return;
        }

        if ($('.extractedTagsList input:checked').length > 0
                && $('.prediction-type input:checked').val() != '-') {
            var periodIsSelected = $('.prediction-period input:checked').length > 0;
            var riskLevelIsSelected = $('.prediction-riskLevel input:checked').length > 0;
            if(!periodIsSelected && !riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noPeriodAndNoRiskLevel')}');
                return;
            }
            if(!periodIsSelected) {
                window.alert('${message(code:'talk.error.noPeriod')}');
                return;
            }
            if(!riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noRiskLevel')}');
                return;
            }
        }


        $('.talk .toolbar .k-button').hide();
        $('.talk .toolbar .loading').show();
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'talk', action: 'save')}',
            data: $('#talkForm').serialize()
        }).done(function (response) {
            if (response == "1") {
                tinymce.activeEditor.setContent('');
                updateHashTags();
                $('.talk .toolbar .k-button').show();
                $('.talk .toolbar .loading').hide();
                window.info('${message(code:'talk.save.success')}');
            }
            else {
                window.alert('${message(code:'backTest.delete.error')}');
            }
        });
    }

    function toggleTalkPrediction(button) {
        var checkbox = $('#cancelPrediction').parent();
        if ($(button).hasClass('btn-danger')) {
            checkbox.addClass('hidden');
            $('.predictionDetails').addClass('hidden');
        }
        else {
            checkbox.removeClass('hidden');
            $('.predictionDetails').removeClass('hidden');
        }
    }
</script>