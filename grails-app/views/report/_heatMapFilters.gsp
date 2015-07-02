<div>
    <g:message code="report.heatmap.display.desc"/>
    <input type="radio" id="rbtn_size" name="mode" class="css-checkbox" value="size" checked> <label
        for="rbtn_size" class="css-label" style="margin-right:20px;margin-left:20px;"><g:message
            code="report.heatmap.display.value"/></label>
    <input type="radio" id="rbtn_count" name="mode" class="css-checkbox" value="count"> <label
        for="rbtn_count" class="css-label"><g:message code="report.heatmap.display.volume"/></label>

    <span style="margin-right:40px;margin-left:20px;display:inline-block;"><g:message
            code="report.heatmap.symbol.count"/></span>
    <form:numericTextBox name="symbolCount" value="10" min="1" max="100"/>
</div>

<div style="margin-top:10px;margin-bottom:10px;">
    <span style="margin-left:20px;display:inline-block;"><g:message
            code="report.heatmap.industryGroup.desc"/></span>
    <form:select name="industry" onchange="industryChanged"
                 items="${[[text: message(code: 'report.heatmap.industryGroup.all'), value: 0]] + industryGroups}"
                 allowUserInput="false"
                 style="width:300px;"/>
    <span style="margin-right:40px;margin-left:20px;display:inline-block;"><g:message
            code="report.heatmap.symbol.desc"/></span>
    <input type="text" class="k-input k-textbox" id="txtSymbolFilter"/>
</div>