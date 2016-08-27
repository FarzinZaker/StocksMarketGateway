<div class="twitter-top-list row">
    <g:if test="${id=='mostFollowedUsers'}">
        <twitter:mostFollowedUsers/>
    </g:if>
    <g:elseif test="${id=='mostActiveUsers'}">
        <twitter:mostActiveUsers/>
    </g:elseif>
    <g:elseif test="${id=='mostRatedUsers'}">
        <twitter:mostRatedUsers/>
    </g:elseif>
    <g:elseif test="${id=='mostRated4tabloUsers'}">
        <twitter:mostRated4tabloUsers/>
    </g:elseif>
    <g:elseif test="${id=='mostFollowedGroups'}">
        <twitter:mostFollowedGroups/>
    </g:elseif>
    <g:elseif test="${id=='mostActiveGroups'}">
        <twitter:mostActiveGroups/>
    </g:elseif>
    <g:elseif test="${id=='mostRatedGroups'}">
        <twitter:mostRatedGroups/>
    </g:elseif>
    <g:elseif test="${id=='mostRated4tabloGroups'}">
        <twitter:mostRated4tabloGroups/>
    </g:elseif>
</div>