<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6" ng-app='stocks' xmlns:ng="http://angularjs.org" id="ng-app"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7" ng-app='stocks' xmlns:ng="http://angularjs.org" id="ng-app"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8" ng-app='stocks' xmlns:ng="http://angularjs.org" id="ng-app"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9" ng-app='stocks' xmlns:ng="http://angularjs.org" id="ng-app"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js" ng-app='stocks' xmlns:ng="http://angularjs.org"
                                     id="ng-app"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Stock Admin"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <g:javascript library="jquery" plugin="jquery"/>
    <asset:javascript src="dotdotdot.js"/>
    <asset:javascript src="form-validator/form-validator.js"/>
    <asset:stylesheet src="common.less"/>
    <asset:stylesheet src="site.less"/>

    <g:layoutHead/>
</head>

<body>
<div class="page-wrap">
    <g:render template="/layouts/common/userInfo"/>
    <div id="header" role="banner" class="k-rtl">
        <a href="${createLink(uri: '/')}" id="logo">
            <asset:image src="logo.png" alt="stocks"/>
        </a>
        <g:render template="/layouts/site/menu"/>
        <asset:image src="watermark.png" class="watermark"/>
        <div class="clear-fix"></div>
    </div>
    <g:layoutBody/>

    <g:render template="/layouts/common/confirm"/>
    <g:render template="/layouts/common/alert"/>
    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt"
                                                                       default="Loading&hellip;"/></div>

</div>
<g:render template="/layouts/common/footer"/>
<script language="javascript" type="text/javascript"
        src="${resource(dir: 'js/bootstrap', file: 'bootstrap.js')}"></script>
<script language="javascript" type="text/javascript">
    var likeUrl = '${createLink(controller: 'like')}';
</script>
<asset:javascript src="like.js"/>
<asset:javascript src="common.js"/>
<g:render template="/layouts/common/lightBox"/>
</body>
</html>
