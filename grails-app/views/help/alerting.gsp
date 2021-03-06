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
    <title><g:message code="alerting.help.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'support'), url:createLink(controller: 'help')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"alerting.help.title"), url:createLink(controller: 'help', action: 'alerting')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h1 class="staticHeading">
                پیام رسان
            </h1>
            <p>
                یکی از اصلی ترین شروط موفقیت در بازار های مالی، اطلاع از وقایع و رخدادهای مهم است و هر قدر این آگاهی سریعتر باشد امکان استفاده بهینه از این اطلاعات، بهتر صورت می گیرد. پیچیدگی های امروزی بازار سرمایه باعث شده است سرمایه گذاران همواره در حال رصد و بررسی کل مجموعه بازار باشند. این کار بسیار وقت گیر بوده و عملا کنترل همه اطلاعات در تمامی ساعات غیر ممکن است. زیرا گستردگی اطلاعات و نیز عدم دسترسی به بسترهای ارتباطی مناسب در هر زمان این امکان را از سرمایه گذاران گرفته است.
            </p>
            <p>
                بنابراین سامانه های پیام رسان و هشدار با پیشرفت تکنولوژی پا به عرصه بازار سرمایه گذاشتند. هم اکنون بسیاری از کارگزاران و شرکت های فعال در بازار سرمایه از روش های متنوعی برای پیام رسانی به مشتریان خود اقدام می کنند که شامل پیام کوتاه، ایمیل، انواع اپلیکیشن تلفن همراه و امثالهم می باشد. اما ضعف سیستم های جاری در عدم سفارشی بودن اطلاعات ارسال برای هر فرد است.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'alerting-01.jpg')}"/>
            </p>
            <p>
                در حال حاضر سرمایه گذاران امکان تعیین نوع اطلاعات دریافتی خود را بر روی این سامانه های پیام رسان ندارند و عموما این اطلاعات به صورت کلی و برای تمامی مخاطبان و در قالب یکسان ارسال می شود. این امر باعث شده است که بسیاری از معامله گران یا از عضویت این سامانه ها خارج شوند و یا با اطلاعات ارسالی آن همانند هرزنامه های الکترونیکی برخورد کنند.             </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'alerting-02.jpg')}"/>
            </p>
            <p>
                یک سامانه پیام رسان ایده آل، سامانه ای است که اطلاعات مورد نیاز هر کاربر را با توجه به سلایق شخصی و بازارهای هدف وی و حتی در زمان های تعیین شده توسط او ارسال نماید. همچنین این سامانه باید طیف وسیعی از اطلاعات در بخش های مختلف بازار را پوشش دهد و بسترهای ارتباطی آن نیز از تنوع خوبی برای شرایط زمانی و مکانی مختلف برخودار باشد. این دقیقا همان سامانه ی است که برای اولین بار توسط سایت 4 تابلو طراحی و در اختیار کاربران قرار گرفته است.
            </p>
            <p>
                کاربران می توانند تمامی اطلاعات موجود در پایگاه داده این سایت (که از منابع اصلی اطلاعات بازار سرمایه نظیر پایگاه داده کدال،  شرکت مدیریت فناوری بورس تهران، شرکت بورس کالای ایران، شرکت بورس انرژی، و سایر پیاگاه های اطلاعاتی نظیر خبری، تحلیلی و ... گرد آوری شده است) با استفاده از پارامترهای مربوطه شامل انواع  فیلتر، محاسبه، جستجو و ... برای ایجاد خبرنانه، پیام رسان و یا هشدار سفارشی سازی نموده و در زمان های مورد نظر و یا به محض وقوع از طریق بستر ارتباطی منتخب خود از آن مطلع شود.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'alerting-03.png')}"/>
            </p>
            <p>
                همچنین همین قابلیت در ارتباط با اطلاعات سبد سرمایه گذاری کاربران در صورتی که از سیستم مدیریت سبد استفاده شود در دسترس خواهد بود. بنابراین با استفاده از این خدمت کاربران می توانند بدون محدودیت زمانی و مکانی دقیقا اطلاعاتی را که مورد نیازشان است بلادرنگ دریافت نمایند.
            </p>
            <p class="text-center">
                <img src="${resource(dir:'images/help', file: 'alerting-04.png')}"/>
            </p>
            <p>
                موتور پردازش اطلاعات به کار گرفته شده در این سامانه این اطمینان را به کاربران خواهد داد که هیچ اطلاعاتی از چشم سرمایگذاران و فعالان بازار دور نخواهد ماند. سامانه پیام رسان با اعمال عناصر بنیادی و تکنیکی و پردازش محتوی از ارسال هرزنامه خودداری نموده و دقیقا اطلاعات مورد نیاز کاربر را در اختیار وی در زمانهای تعیین شده توسط کاربر قرار می دهد.
            </p>
        </div>
    </div>
</div>
</body>
</html>