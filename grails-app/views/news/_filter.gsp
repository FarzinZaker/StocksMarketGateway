<div class="dashLet magenta">
    <h2 style="float:right"><i class="fa fa-filter"></i> <g:message code="newsFeed.filterTitle"/></h2>

    <form id="filterForm">

        <h3><g:message code="newsFeed.category"/></h3>

        <div class="subPanel">
            <g:each in="${categories}" var="category">
                <div>
                    <form:checkbox name="category" id="source_${category}"
                                   text="${message(code: "newsCategory.${category}")}" checked="${true}"/>
                </div>
            </g:each>
        </div>

        <h3><g:message code="newsFeed.source"/></h3>

        <div class="subPanel">
            <g:each in="${sources}" var="source">
                <div>
                    <form:checkbox name="source" id="source_${source}"
                                   text="${message(code: "newsSource.${source}")}" checked="${true}"/>
                </div>
            </g:each>
        </div>

        <h3><g:message code="newsFeed.date"/></h3>

        <div class="subPanel">
            <form:datePickerResources/>
            <form:datePicker name="date" value="${format.jalaliDate(date: new Date())}" style="width: 100%"/>
        </div>

        <h3><g:message code="newsFeed.search"/></h3>

        <div class="subPanel">
            <form:textBox name="search" style="width: 100%"/>
        </div>
    </form>

    <div class="dashletFooter">
        <a href="javascript://void(0)" onclick="parseRSS(false);"><g:message code="newsFeed.filterButton"/></a>
    </div>
</div>