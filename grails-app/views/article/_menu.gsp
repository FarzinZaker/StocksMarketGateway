<asset:stylesheet src="smint.less"/>
<asset:javascript src="smint.js"/>

<div class="subMenu">
    <div class="inner">
        <a href="#article" class="subNavBtn">${message(code:'article.menu.article')}</a>
        <a href="#articleTags" class="subNavBtn">${message(code:'article.menu.tags')}</a>
        <a href="#submitComment" class="subNavBtn">${message(code:'article.menu.comment.submit')}</a>
    </div>
</div>

<script type="text/javascript">


    $(document).ready(function () {
        $('.subMenu').smint({
            'scrollSpeed': 1000
        });
    });


</script>