<%@ page import="stocks.messaging.NewsLetterCategory" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="newsLetter.build.title"/></title>
    <form:datePickerResources/>
    <asset:stylesheet src="jquery-clockpicker.css"/>
    <asset:javascript src="jquery-clockpicker.js"/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsLetter.title'), url: createLink(controller: 'newsLetter')],
                    [text: '<i class="fa fa-bullhorn"></i>' + message(code: 'newsLetter.build.title'), url: createLink(controller: 'newsLetter', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 k-rtl">

            <form:form action="save" name="cityForm">
                <g:hiddenField name="id" value="${newsLetter?.id}"/>

                <form:field fieldName="newsLetter.subject">
                    <form:textBox name="subject" style="width:500px" entity="${newsLetter}" validation="required"/>
                </form:field>
                <form:field fieldName="newsLetter.body" showHelp="0">
                    <form:editor name="body" mode="full" style="width:790px" height="300" entity="${newsLetter}"
                                 validation="required"/>
                </form:field>
                <form:field fieldName="newsLetter.sendDate">
                    <form:datePicker name="sendDate_date" style="float:right;width:260px;"
                                     value="${format.jalaliDate(date: newsLetter?.sendDate ?: new Date())}"/>
                    <div class="input-group clockpicker" style="float:right;margin-right:10px;width:200px;">
                        <input name="sendDate_time" type="text" class="form-control"
                               style="padding-top:0;padding-bottom:0;height:30px;width:200px;"
                               value="${format.jalaliDate(date: newsLetter?.sendDate ?: new Date(), timeOnly: true)}">
                        <span class="input-group-addon" style="padding-top:0;padding-bottom:0;">
                            <span class="glyphicon glyphicon-time"></span>
                        </span>
                    </div>
                    <script language="javascript" type="text/javascript">
                        $(document).ready(function () {
                            $('.clockpicker').clockpicker({
                                placement: 'top',
                                align: 'right',
                                autoclose: true
                            });
                        });
                    </script>
                </form:field>
                <form:field fieldName="newsLetter.categories">
                    <div style="width: 510px;">
                    <g:each in="${newsLetter?.categories?.sort{it?.name}}" var="category">
                        <form:checkbox name="category_${category?.id}" text="${category.name}" checked="${true}" style="width: 250px;"/>
                    </g:each>
                    <g:each in="${NewsLetterCategory.findAllByDeleted(false)?.sort{it?.name}}" var="category">
                        <g:if test="${!newsLetter?.categories?.collect { it.id }?.contains(category?.id)}">
                            <form:checkbox name="category_${category?.id}" text="${category.name}" checked="${false}"
                                           style="width: 250px"/>
                        </g:if>
                    </g:each>
                    </div>
                </form:field>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'newsLetter.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>


