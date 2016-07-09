<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/14/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="portfolio.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"portfolio.help.title"), url:createLink(controller: 'help', action: 'portfolio')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h1 class="staticHeading">
                سبدگردانی
            </h1>
            <p>
                یکی از اقداماتی که زمان زیادی را از سرمایه گذاران به خود اختصاص می دهد، بررسی وضعیت سبد سهام است. محاسبه سود و زیان، محاسبه اقدامات شرکتی از جمله افزایش سرمایه، پیگیری مطالبات سود های نقدی، بررسی ارزش روز سبد، بررسی بازده سبد و محاسبه سود و زیان محقق شده و محقق نشده از موارد زمان بر و پیچیده ای است که سرمایه گذاران همواره با آن دست به گریبانند.            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'portfolio-01.png')}"/>
            </p>
            <p>
                مدیریت سبد ابزاری است که با دریافت اطلاعات خرید و فروش سرمایه گذاران به صورتهای متنوع کلیه محاسبات و گزارشات لازم را انجام داده و علاوه بر مدیریت دارایی های بورسی سرمایه گذار، دارایی های خارج از بورس، بانک، مطالبات و بدهی ها را نیز در بر می گیرد. مدیریت سبد این امکان را به کاربر می دهد تا به صورت لحظه ای وضعیت دارایی های خود را مشاهده نموده و گزارشات کاربردی مناسبی را دریافت نماید.
            </p>
        </div>
    </div>
</div>
</body>
</html>