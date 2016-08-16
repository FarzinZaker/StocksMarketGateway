<%@ page import="grails.converters.JSON" %>
<g:if test="${flash.message}">
    <div class="errorMessage">
        ${flash.message}
    </div>
</g:if>

<form:hidden name="id" entity="${document}"/>

<form:field fieldName="document.title">
    <form:textBox name="title" style="width:900px" entity="${document}"/>
</form:field>

<form:field fieldName="document.summary">
    <form:editor name="summary" width="900" mode="simple" entity="${document}"/>
</form:field>

<form:field fieldName="document.body">
    <twitter:tagSearch/>
    <twitter:authorSearch/>
    <form:editor name="body" width="900" height="400" entity="${document}" mode="full" hashTag="true" authorTag="true"/>
</form:field>

<form:field fieldName="document.image">
    <form:imageUpload name="image" style="width:900px;" entity="${document}"
                      saveUrl="${createLink(controller: 'image', action: 'uploadImage')}"/>
</form:field>

<form:field fieldName="document.files">
    <div id="fileIds">
        <g:each in="${document?.files}" var="file">
            <input type="hidden" name="files" data-name="${file?.name}" value="${file?.id}"/>
        </g:each>
    </div>
    <input type="hidden" name="fileIds" value=""/>

    <div class="k-rtl" style="width: 900px;">
        <input name="fileUpload" id="fileUpload" type="file"/>
    </div>
</form:field>
<script>
    $(document).ready(function () {
        $("#fileUpload").kendoUpload({
            async: {
                saveUrl: "${createLink(controller: 'file', action: 'uploadFile')}",
                removeUrl: "${createLink(controller: 'file', action: 'removeFile')}",
                autoUpload: true
            },
            files: <format:html value="${document?.files?.collect{
            [
                name: it?.name,
//                extension: 'dat',
//                size: 0
            ]
            } as JSON}"/>,
            success: function (e) {
                if (e.response.result == 'add')
                    $("#fileIds").append('<input type="hidden" name="files" data-name="' + e.response.name + '" value="' + e.response.id + '"/>');
            },
            remove: function (e) {
                var name = e.files[0].name;
                $('#fileIds').find('input[type="hidden"]').each(function () {
                    if ($(this).attr('data-name') == name)
                        $(this).remove();
                });
            }
        });
    });
</script>

%{--<form:field fieldName="document.tags">--}%
%{--<form:multiSelect name="tags" style="width:900px;" entity="${document}"--}%
%{--dataSourceUrl="${createLink(controller: 'tag', action: 'jsonSearch')}"/>--}%
%{--</form:field>--}%

<form:field fieldName="document.shareGroups">
    <twitter:shareGroups name="share" style="width:900px;" materialId="${document?.id}"/>
</form:field>
%{--<form:field fieldName="document.shareTags">--}%
%{--<twitter:shareTags name="shareTags" style="width:900px;" groupSelectorWidth="350" itemSelectorWidth="510"--}%
%{--materialId="${document?.id}"/>--}%
%{--</form:field>--}%