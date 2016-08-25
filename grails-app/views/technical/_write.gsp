<%@ page import="stocks.User" %>
<div class="clearfix" style="margin-top:10px;"></div>

<form class="talk" id="talkForm">
    <input type="hidden" name="propertyTitle" value="${tag?.title}"/>
    <input type="hidden" name="propertyType" value="${tag?.clazz}"/>
    <input type="hidden" name="propertyId" value="${tag?.id}"/>
    <input type="hidden" name="data"/>
    <input type="hidden" name="image"/>

    <div class="infoBar">
        <img class="userImage"
             src="${createLink(controller: 'image', action: 'profile', params: [id: User.findByUsername(sec.username() as String)?.id, size: 100])}"/>

        <div class="toolbar" style="margin-top:15px;">
            <form:button onclick="submitTalk();" text="${message(code: 'talk.button')}"/>
            <form:button onclick="submitAnalysis();" text="${message(code: 'technicalShare.button')}"
                         style="margin-top:5px;"/>
            <form:loading/>
        </div>
    </div>
    <twitter:tagSearch/>
    <twitter:authorSearch/>
    <div style="margin-right:130px;">
        <form:editor name="body" height="165" hashTag="true" authorTag="true" tag="${tag}"/>
    </div>

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
                    <input type="radio" name="predictionType" value="-" id="cancelPrediction" autocomplete="off"
                           checked>
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
                        <g:message code="twitter.prediction.period.1d"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="1w" autocomplete="off">
                        <g:message code="twitter.prediction.period.1w"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="4w" autocomplete="off">
                        <g:message code="twitter.prediction.period.4w"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="12w" autocomplete="off">
                        <g:message code="twitter.prediction.period.12w"/>
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="predictionPeriod" value="26w" autocomplete="off">
                        <g:message code="twitter.prediction.period.26w"/>
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
            if (!periodIsSelected && !riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noPeriodAndNoRiskLevel')}');
                return;
            }
            if (!periodIsSelected) {
                window.alert('${message(code:'talk.error.noPeriod')}');
                return;
            }
            if (!riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noRiskLevel')}');
                return;
            }
        }


        $('.talk .toolbar .k-button').hide();
        $('.talk .toolbar .loading').show();
        var formData = $('#talkForm').serializeArray();
        $.each(formData, function (key, data) {
            if (this.name == "body")
                this.value = tinymce.activeEditor.getContent();
        });
        $.ajax({
            type: "POST",
            url: '${createLink(controller: 'talk', action: 'save')}',
            data: formData
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

    function submitAnalysis() {
        var content = strip(tinymce.activeEditor.getContent()).trim();
        if (content == '') {
            window.alert('${message(code:'talk.error.empty')}');
            return;
        }

        if ($('.extractedTagsList input:checked').length > 0
                && $('.prediction-type input:checked').val() != '-') {
            var periodIsSelected = $('.prediction-period input:checked').length > 0;
            var riskLevelIsSelected = $('.prediction-riskLevel input:checked').length > 0;
            if (!periodIsSelected && !riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noPeriodAndNoRiskLevel')}');
                return;
            }
            if (!periodIsSelected) {
                window.alert('${message(code:'talk.error.noPeriod')}');
                return;
            }
            if (!riskLevelIsSelected) {
                window.alert('${message(code:'talk.error.noRiskLevel')}');
                return;
            }
        }


        $('.talk .toolbar .k-button').hide();
        $('.talk .toolbar .loading').show();


        var frameScope = $('#tv_chart_container').find('iframe')[0].contentWindow;
        frameScope.takeSnapshot(frameScope.Z5, function (result) {
            var formData = $('#talkForm').serializeArray();
            $.each(formData, function (key, data) {
                if (this.name == "body")
                    this.value = tinymce.activeEditor.getContent();
                if (this.name == "data")
                    this.value = JSON.stringify(frameScope.saver.saveToJSON());
                if (this.name == "image")
                    this.value = result;

            });
            $.ajax({
                type: "POST",
                url: '${createLink(controller: 'technical', action: 'save')}',
                data: formData
            }).done(function (response) {
                if (response == "1") {
//                    tinymce.activeEditor.setContent('');
                    updateHashTags();
                    $('.talk .toolbar .k-button').show();
                    $('.talk .toolbar .loading').hide();
                    window.info('${message(code:'talk.save.success')}');
                }
                else {
                    window.alert('${message(code:'backTest.delete.error')}');
                }
            });
        }, function () {
        }, {snapshotUrl: '${createLink(controller: 'technical', action: 'image')}'});
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