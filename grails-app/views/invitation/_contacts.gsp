<div id="filter"><g:message code="grails.plugin.invitation.contacts.search"/><g:textField class="k-textbox"
                                                                                          name="filterField"
                                                                                          id="filterField"
                                                                                          value=""></g:textField>
    <div id="selectionHelpers">
        <a id="selectAll" href="javascript:void(0);"><g:message
                code="grails.plugin.invitation.contacts.selectAll"/></a> |
        <a id="clearSelection" href="javascript:void(0);"><g:message
                code="grails.plugin.invitation.contacts.clearSelection"/></a>
    </div>
</div>

<div id="invitation-friends" class="friends">
    <g:each in="${contacts.findAll { it.name != '' }.sort { it.name } + contacts.findAll { it.name == '' }.sort {
        it.address
    }}" var="contact" status="index">
        <div class="friend" data-email="${contact.address}">
            <g:if test="${contact.photo}">
                <img src="${contact.photo}" onerror="reloadContactPhoto(this);" data-index="${index}" alt=" "/>
            </g:if><g:else>
            <img src="${resource(dir: 'images', file: 'user-noImage.png')}"/>
        </g:else>
            <span>${contact.name ? contact.name : contact.address}</span>
        </div>
    </g:each>
</div>

<script language="javascript">

    var retriesCountMap = {};
    function reloadContactPhoto(image) {
        image.onerror = null;
        if (!eval('retriesCountMap.contactPhoto' + $(image).attr('data-index')))
            eval('retriesCountMap.contactPhoto' + $(image).attr('data-index') + '=1');
        else
            eval('retriesCountMap.contactPhoto' + $(image).attr('data-index') + '++');
        console.log(eval('retriesCountMap.contactPhoto' + $(image).attr('data-index')));
        if (eval('retriesCountMap.contactPhoto' + $(image).attr('data-index') + '<5')) {
            setTimeout(function () {
                $(image).on('error', function () {
                    reloadContactPhoto(this);
                });
                image.src += '&' + new Date;
            }, getRandomInt(1000, 5000));
        }
        else {
            image.src = '${resource(dir: 'images', file: 'user-noImage.png')}'
        }
    }

    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
</script>