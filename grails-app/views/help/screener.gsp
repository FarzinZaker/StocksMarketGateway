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
    <title><g:message code="screener.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"screener.help.title"), url:createLink(controller: 'help', action: 'screener')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h1 class="staticHeading">
                تابلو ساز( غربال گر بازار)
                </h1>
            <p>
                با رشد بازار سرمایه طی چند سال اخیر بر تعداد نماد ها و شرکت های پذیرفته شده در بورس روز به روز افزوده می شود و همانگونه که از شواهد امر پیداست این روند ادامه دار بوده و در آینده نزدیک تعداد شرکت های پذیرفته شده در بورس ها بیشتر خواهند شد. این موضوع در کنار روش ها و عناصر متعدد تصمیم گیری سرمایه گذاران سبب شده است تا بررسی بازار برای افراد دشوار تر شود زیرا امکان بررسی کلیه عناصر تصمیم گیری در خصوص کلیه نماد ها کاری بس دشوار است و این بررسی ها به صورت همزمان در طی جریان معاملات بر روی همه نماد ها امکان ناپذیر است
            </p>
            <p>
                آنچه که در این شرایط به کمک سرمایه گذاران می آید نرم افزار های غربالگری است. تابلوساز نوعی غربالگر است که به سرمایه گذار این امکان را می دهد تا با تعریف چندین تابلو با ویژگی های متفاوت نسبت به غربال شرکت ها اقدام کند. ویژگی های هر تابلو به صورت کاملا اختصاصی مطابق نظر کاربر و بر اساس عناصر بنیادی و تکنیکی تاثیر گذار بر تصمیم سرمایه گذاران تنظیم می گردد. به محض آنکه سهام یک شرکت چه از نظر بنیادی و چه از نظر تکنیکی شرط یا شرط های یک تابلو را احراز کرد به آن تابلو اضافه شده و توسط کاربر قابل مشاهده است و تبع آن به محض آنکه یک سهم شرایط حضور در تابلو را از دست داد از تابلو حذف می گردد.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'screener-01.png')}"/>
            </p>
            <p>
                به این صورت کاربر تمام ویژگی هایی را که جهت تصمیم گیری موثر می داند در غربالگر تابلوی اختصاصی خود از پیش تعیین می کند و دیگر نیازی نیست در طول بازار به بررسی کل بازار بپردازد. به محض آنکه ویژگی های تعریف شده توسط یک سهم احراز شد علاوه بر اطلاع رسانی به شیوه های گوناگون، در تابلو وی نیز ظاهر می شود.
            </p>
        </div>
    </div>
</div>
</body>
</html>