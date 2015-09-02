${message}<br/><br/>

${inviter}<br/>

<a href="${createLink(uri: '/', absolute: true)}?invitation=${identifier}">
    بازدید از 4tablo
</a>
<br/>
<a href="${createLink(controller: 'user', action: 'registerInvited', absolute: true)}?invitation=${identifier}">
    ثبت نام در 4tablo
</a>