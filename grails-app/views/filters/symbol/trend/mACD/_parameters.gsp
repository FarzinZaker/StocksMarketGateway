<span class="filterParameter">
    <form:select name="parameter" id="${id}"
                 style="width:90px;text-align: center;" value="${value}" onchange="parameterChange"
                 items="${[[12, 26, 0]].collect { [text: it.join(','), value: it.join(',')] }}"/>
</span>
