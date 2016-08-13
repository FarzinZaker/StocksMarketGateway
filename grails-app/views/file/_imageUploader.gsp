<html>
<head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/kendo.ui', file: 'kendo.common.min.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/kendo.ui', file: 'kendo.metro.min.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/kendo.ui', file: 'kendo.rtl.min.css')}"/>
    <g:javascript library="jquery" plugin="jquery"/>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui', file: 'kendo.web.min.js')}"></script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui/jalali', file: 'fa-IR.js')}"></script>
    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js/kendo.ui/jalali', file: 'kendo.messages.fa-IR.js')}"></script>

    <script language="javascript" type="text/javascript"
            src="${resource(dir: 'js', file: 'kendo.corrections.js')}"></script>
    <asset:stylesheet src="kendo.corrections.less"/>
    <asset:stylesheet src="common.less"/>
</head>

<body style="background: white">

<div class='k-rtl'>
    <div id='uploader_container'>
        <input type='file' name='uploader' id='uploader'/>
    </div>
</div>
<script language='javascript' type='text/javascript'>
    $(document).ready(function () {
        $("#uploader").kendoUpload({
            async: {
                saveUrl: '${createLink(controller: 'image', action: 'uploadImage')}?name=uploader',
                autoUpload: true
            },
            select: function (e) {
                var container = $('#uploader_container');
                container.find('.k-file').remove();
                var fileReader = new FileReader();
                fileReader.onload = function (event) {
                    container.find('.k-file .k-progress').remove();
                    console.log(event);
                    var mapImage = event.target.result;
                    container.find('.k-file .k-icon').replaceWith("<img class='upload-thumbnail' id='uploader_preview'/>");
                    $("#uploader_preview").attr('src', mapImage);

                };
                fileReader.readAsDataURL(e.files[0].rawFile);

            },

            multiple: false,
            success: function (e) {
                parent.imageSelected('${createLink(controller: 'image', action: 'index', absolute: true)}/' + e.response);
            }
        });
    });
</script>
</body>
</html>