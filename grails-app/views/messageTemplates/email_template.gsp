<%@ page import="stocks.AuthenticationProvider" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<p>&nbsp;</p>

<div style="margin:auto;height:40px;width: 462px;background-image:url('http://4tablo.ir/images/logo-email.png') ">

</div>

<div style='color:black;width:360px;font-family:Tahoma,serif;padding-top:20px;padding-bottom:20px;padding-right:50px;padding-left:50px;direction:rtl;margin:auto;border:1px solid #EEEEEE;border-top:none;'>
    <table>
        <tr>
            <td style='font-size:12px;line-height:24px;text-align:justify;font-family: tahoma,serif'>
                <format:html value="${message}"/>
            </td>
        </tr>
    </table>

</div>

<p align="center" class="footer" style="font-size: 11px;font-family: Tahoma, Arial,serif;color:black;">
    لطفاً به این پیام پاسخ ندهید زیرا فقط برای آگاه سازی شما و به صورت اتوماتیک ایجاد شده است
</p>

<p align="center" class="footer" style="direction: rtl;font-family: Tahoma,Arial,serif;font-size: 11px;margin-bottom: 5px;margin-top: 30px;color:grey;">
    اگر مایل به دریافت نامه از
    <a href="${createLink(uri: '/', absolute: true)}">4تابلو</a>
    نیستید روی لینک
    <a href="${createLink(controller: 'subscription', action: 'remove', params: [email: email, source: source, token: AuthenticationProvider.md5("unsubscribe ${email} from ${source} of 4tablo")], absolute: true)}">درخواست عدم دریافت نامه</a>
    کلیک کنید.
</p>

<p align="center" class="footer" style="font-size: 11px;font-family: times, Arial,serif;margin-top:0;color:grey;">
    Don't want this? safely
    <a href="${createLink(controller: 'subscription', action: 'remove', params: [email: email, source: source, token: AuthenticationProvider.md5("unsubscribe ${email} from ${source} of 4tablo")], absolute: true)}">unsubscribe</a>
</p>
</body>
</html>