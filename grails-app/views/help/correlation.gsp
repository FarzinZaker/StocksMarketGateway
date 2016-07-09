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
    <title><g:message code="correlation.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"correlation.help.title"), url:createLink(controller: 'help', action: 'correlation')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h1 class="staticHeading">
                همبستگی
            </h1>
            <p>
                شناخت نوع ارتباط اطلاعات مختلف با یکدیگر و اثرگذاری روند قیمتی یک دارایی در بازار، بر دارایی دیگر به عنوان یکی از مولفه های پیشتیبان اخذ تصمیم از اهمیت بالایی برخوردار است. استفاده از ضریب همبستگی به عنوان یکی از ابزارهای شناخت چنین ارتباطاتی بسیار مفید و کارآمد خواهد بود. ضریب همبستگی، شاخصی است که میزان رابطه بین متغیرها را به صورت کمی بیان می کند.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'correlation-01.png')}"/>
            </p>
            <p>
                در چهار تابلو با استفاده از ابزار محاسبه¬گر ضریب همبستگی می توان بر اساس داده های تاریخی، ضریب همبستگی قیمتی بین دارایی های مختلف را به صورت یک به یک و یا یک به چند به صورت آن لاین محاسبه و مشاهده کرد. که از جمله این دارایی ها می توان به قیمت انواع سهام، شاخص ها ، سکه طلا، فلزات، ارز و غیره اشاره کرد.
            </p>
        </div>
    </div>
</div>
</body>
</html>