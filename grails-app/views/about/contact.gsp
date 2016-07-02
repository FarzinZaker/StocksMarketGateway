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
    <title><g:message code="about.contact.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url:createLink(uri:'/')],
                    [text: message(code:'about'), url:createLink(controller: 'about')],
                    [text: '<i class="fa fa-info"></i> ' + message(code:"about.contact.title"), url:createLink(controller: 'about', action: 'contact')]
            ]}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h2 class="staticHeading">
            آدرس
            </h2>
            <p>
                تهران، میدان ونک، خیابان ملاصدار، خیابان پردیس، پلاک 14، واحد 5
            </p>

            <h2 class="staticHeading">
                تلفن تماس
            </h2>
            <p class="ltr text-right">
                (021) 886 53 508
            </p>

            <h2 class="staticHeading">
            فکس
            </h2>
            <p class="ltr text-right">
                (021) 886 44 357
            </p>
        </div>
    </div>
</div>
</body>
</html>