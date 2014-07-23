<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/6/14
  Time: 2:44 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="site"/>
    <title>${user}</title>
    <asset:javascript src="scroll-pagination.js"/>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-xs-12">
            <g:render template="/user/banner" model="${[user: user]}"/>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-xs-3">
            <layout:panel title="${message(code: 'topArticles.title')}">
                <stocks:userTopArticles user="${user}"/>
            </layout:panel>
            <g:render template="/user/followingList" model="${[user: user]}"/>
            <layout:panel title="${message(code: 'tagCloud.title')}">
                <stocks:tagCloud/>
            </layout:panel>
        </div>

        <div class="col-xs-9">
            <ul id="document-list">

            </ul>

            <form:loading id="loading"/>

            <div class="loading" id="nomoreresults"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#document-list').scrollPagination({
            'contentPage': '${createLink(action: 'wallJson', id:user.id)}', // the url you are fetching the results
            'contentData': {skip: $('#document-list').children('li').size()}, // these are the variables you can pass to the request, for example: children().size() to know which page you are
            'scrollTarget': $(window), // who gonna scroll? in this example, the full window
            'heightOffset': 10, // it gonna request when scroll is 10 pixels before the page ends
            'beforeLoad': function () { // before load function, you can display a preloader div
                $('#loading').fadeIn();
            },
            'afterLoad': function (elementsLoaded) { // after loading content, you can use this function to animate your new elements
                $('#loading').fadeOut();
                var i = 0;
                $(elementsLoaded).fadeInWithDelay();
//                if ($('#content').children().size() > 100) { // if more than 100 results already loaded, then stop pagination (only for testing)
//                    $('#nomoreresults').fadeIn();
//                    $('#content').stopScrollPagination();
//                }
            }
        });

        // code for fade in element by element
        $.fn.fadeInWithDelay = function () {
            var delay = 0;
            return this.each(function () {
                $(this).delay(delay).animate({opacity: 1}, 200);
                delay += 100;
            });
        };

    });
</script>
</body>
</html>