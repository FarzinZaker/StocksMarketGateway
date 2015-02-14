<span class="filterParameter">
    <form:select items="${[[0.02,0.2]].collect { [text: it.join(','), value: it.join(',')] }}" name="parameter" id="${id}"
                 style="width:100px;text-align: center;" value="${value}" onchange="parameterChange"/>
</span>
