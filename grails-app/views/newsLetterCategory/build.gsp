<%@ page import="stocks.messaging.NewsLetterCategory" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="newsLetterCategory.build.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <layout:breadcrumb items="${[
                    [text: '', url: createLink(uri: '/')],
                    [text: message(code: 'newsLetter.title'), url: createLink(controller: 'newsLetter')],
                    [text: '<i class="fa fa-bullhorn"></i>' + message(code: 'newsLetterCategory.build.title'), url: createLink(controller: 'newsLetterCategory', action: 'build', id: params.id)]
            ]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 k-rtl">

            <form:form action="save" name="cityForm">
                <g:hiddenField name="id" value="${newsLetterCategory?.id}"/>

                <form:field fieldName="newsLetterCategory.name">
                    <form:textBox name="name" style="width:500px" entity="${newsLetterCategory}" validation="required"/>
                </form:field>

                <div class="toolbar">
                    <input type="submit" value="${message(code: 'newsLetterCategory.build.button')}" class="k-button"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>


