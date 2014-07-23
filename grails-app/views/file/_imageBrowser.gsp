<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 6/26/14
  Time: 2:38 PM
--%>

<g:javascript library="jquery" plugin="jquery"/>
<asset:stylesheet src="kendo.ui/kendo.common.min.css"/>
<asset:stylesheet src="kendo.ui/kendo.metro.min.css" rel="stylesheet"/>
<asset:stylesheet src="kendo.ui/kendo.rtl.min.css" rel="stylesheet"/>
<asset:stylesheet src="kendo.ui/kendo.menu.min.css" rel="stylesheet"/>
<asset:javascript src="kendo.ui/kendo.core.min.js"/>
<asset:javascript src="kendo.ui/kendo.menu.min.js"/>
<asset:javascript src="kendo.ui/kendo.popup.min.js"/>
<asset:javascript src="kendo.ui/kendo.data.min.css"/>
<asset:javascript src="kendo.ui/kendo.popup.min.css"/>
<asset:javascript src="kendo.ui/kendo.list.min.css"/>
<asset:javascript src="kendo.ui/kendo.combobox.min.css"/>
<asset:javascript src="kendo.ui/kendo.dropdownlist.min.css"/>
<asset:javascript src="kendo.ui/kendo.userevents.min.css"/>
<asset:javascript src="kendo.ui/kendo.draganddrop.min.css"/>
<asset:javascript src="kendo.ui/kendo.window.min.css"/>
<asset:javascript src="kendo.ui/kendo.slider.min.css"/>
<asset:javascript src="kendo.ui/kendo.colorpicker.min.css"/>
<asset:javascript src="kendo.ui/kendo.listview.min.css"/>
<asset:javascript src="kendo.ui/kendo.upload.min.css"/>
<asset:javascript src="kendo.ui/kendo.binder.min.css"/>
<asset:javascript src="kendo.ui/kendo.validator.min.css"/>
<asset:javascript src="kendo.ui/kendo.editable.min.css"/>
<asset:javascript src="kendo.ui/kendo.selectable.min.css"/>
<asset:javascript src="kendo.ui/kendo.imagebrowser.min.css"/>
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