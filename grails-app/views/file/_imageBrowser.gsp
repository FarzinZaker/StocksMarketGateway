<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/26/14
  Time: 2:38 PM
--%>

<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.common.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.metro.min.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css/kendo.ui', file: 'kendo.rtl.min.css')}"/>
<g:javascript library="jquery" plugin="jquery"/>
<script language="javascript" type="text/javascript" src="${resource(dir:'js/kendo.ui', file:'kendo.web.min.js')}"></script>
<asset:stylesheet src="kendo.corrections.less"/>
<asset:stylesheet src="common.less"/>
<div class="k-rtl" id="imgBrowser" style="margin:10px;"></div>
<script language="javascript" type="text/javascript">
    $("#imgBrowser").kendoImageBrowser({
        apply: function (p){
            parent.imageSelected(p.sender.options.transport.imageUrl.replace('{0}', p.sender.path() + $('.k-tile.k-state-selected strong').text()));
        },
        messages: {
            dropFilesHere: "${message(code: 'editor.dropFilesHere')}",
            upload: "${message(code: 'editor.upload')}"
        },
        transport: {
            read: {
                url: "${imageBrowserPath}/Read",
                dataType: "json"
            },
            destroy: {
                url: "${imageBrowserPath}/Destroy",
                type: "POST"
            },
            create: {
                url: "${imageBrowserPath}/Create",
                type: "POST"
            },
            thumbnailUrl: "${imageBrowserPath}/Thumbnail",
            uploadUrl: "${imageBrowserPath}/Upload",
            imageUrl: "${imageBrowserPath}/Image/${userId}?path={0}"
        }
    });
</script>