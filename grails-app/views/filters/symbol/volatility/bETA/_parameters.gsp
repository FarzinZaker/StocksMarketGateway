<span class="filterParameter">
    <form:select items="${[5].collect { [text: it.toString(), value: it] }}" name="parameter" id="${id}"
                 style="width:60px;text-align: center;" value="${value}" onchange="parameterChange"/>
</span>
