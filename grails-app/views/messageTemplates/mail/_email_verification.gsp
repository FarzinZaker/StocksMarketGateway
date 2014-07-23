
<g:message code="user.title.${user.sex}"/> ${user.lastName}
<br/>
از ثبت نام شما در <g:message code="title"/> سپاسگزاریم. حساب کاربری شما ایجاد گردیده اما لازمست قبل از ورود به سایت، حساب کاربری خود را از طریق کلیک بر روی لینک زیر فعال نمایید.
<br/>
<a href="${createLink(absolute: true, controller: 'user', action: 'activate', params: [id:user.id, code: "${new Date().timeString}_activate_${user.id}".encodeAsBase64()])}">
    فعال سازی حساب کاربری
</a>
<br/>
در صورتیکه این درخواست عضویت از جانب شما نبوده، لطفا بدون انجام هیچ اقدامی از صفحه خارج شوید.