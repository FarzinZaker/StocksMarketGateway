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

%{--<form:field fieldName="document.tags">--}%
%{--<form:multiSelect name="tags" style="width:900px;" entity="${document}"--}%
%{--dataSourceUrl="${createLink(controller: 'tag', action: 'jsonSearch')}"/>--}%
%{--</form:field>--}%

<form:field fieldName="document.shareGroups">
    <twitter:shareGroups name="share" style="width:900px;" materialId="${document?.id}"/>
</form:field>
<form:field fieldName="document.shareTags">
    <twitter:shareTags name="shareTags" style="width:900px;" groupSelectorWidth="350" itemSelectorWidth="510" materialId="${document?.id}"/>
</form:field>