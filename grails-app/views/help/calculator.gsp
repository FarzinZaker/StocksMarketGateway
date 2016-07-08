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
    <title><g:message code="calculator.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"calculator.help.title"), url:createLink(controller: 'help', action: 'calculator')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h1 class="staticHeading">
محاسبه گر آتی
            </h1>
            <p>
                بازار مشتقات مالی نسبت به بازار های نقدی ( spot ) دارای پیچیدگی های بیشتری است و این پیچیدگی ناشی از عوامل تاثیر گذار بر قیمت مشتقات مالی است که در هنگام قیمت گذاری دارایی در بازار های نقدی وجود ندارد. یکی از ابزار مشتقه های مطرح در بازار سرمایه ایران،  قرارداد های آتی در شرکت بورس کالای ایران با محوریت سکه طلا است.
            </p>
            <p>
                ماشین حساب در واقع یک آنالیزور محسوب می شود و همواره با رصد لحظه ای بازار نقد و آتی در سررسید های مختلف، اقدام به آنالیز و بررسی عملکرد و افکار بازار می نماید. در عین حال فرصت های سرمایه گذاری با هدف کسب سود بدون ریسک را با توجه به نرخ بازده مورد انتظار سرمایه گذار شناسایی کرده و راهکار های معاملاتی را در اختیار وی قرار می دهد.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'calculator-01.png')}"/>
            </p>
            <p>
                ماشین حساب توسط سامانه اطلاع رسان قابل قبولی پیشتیبانی شده و در هر لحظه اطلاعات مورد نیاز سرمایه گذار را با توجه به نیاز از پیش تعیین شده وی در کوتاه ترین زمان از طرق متنوع در اختیار سرمایه گذار قرار می دهد.
            </p>
            <p>
                نقطه قوت ماشین حساب در امکان تعریف کلیه پارامتر های مبنای محاسبات است و آنالیز دقیق آن ضریب اطمینان بالایی را برای آن فراهم آورده است.
            </p>
        </div>
    </div>
</div>
</body>
</html>