<div class="dashLet magenta">
    <h2 style="float:right"><i class="fa fa-filter"></i> <g:message code="analysisFeed.filterTitle"/></h2>

    <form id="filterForm">

        <h3><g:message code="analysisFeed.category"/></h3>

        <div class="subPanel">
            <g:each in="${categories}" var="category">
                <div>
                    <form:checkbox name="category_${category}" id="category_${category}"
                                   text="${message(code: "analysisCategory.${category}")}" checked="${true}"/>
                </div>
            </g:each>
        </div>

        <h3><g:message code="analysisFeed.source"/></h3>

        <div class="subPanel">
            <g:each in="${sources}" var="source">
                <div>
                    <form:checkbox name="source_${source}" id="source_${source}"
                                   text="${message(code: "analysisSource.${source}")}" checked="${true}"/>
                </div>
            </g:each>
        </div>

        <h3><g:message code="analysisFeed.date"/></h3>

        <div class="subPanel">
            <form:datePickerResources/>
            <form:datePicker name="date" value="${format.jalaliDate(date: new Date())}" style="width: 100%"/>
        </div>

        <h3><g:message code="analysisFeed.search"/></h3>

        <div class="subPanel">
            <form:textBox name="search" style="width: 100%"/>
        </div>
    </form>

    <div class="dashletFooter">
        <a href="javascript://void(0)" onclick="refreshData();"><g:message code="analysisFeed.filterButton"/></a>
    </div>
</div>