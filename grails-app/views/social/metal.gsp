 ⚡️ ${metal.name}

 آخرین: <g:if test="${metal.change != 0}">
</g:if><g:formatNumber number="${metal.price}" type="number"/> <g:if test="${metal.change != 0}"><g:if test="${metal.change > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${metal.change}" type="number"/> (<g:formatNumber number="${metal.percent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

بیشترین: <g:formatNumber number="${metal.high}" type="number"/>
کمترین: <g:formatNumber number="${metal.low}" type="number"/>

 🕒<format:jalaliDate date="${new Date()}"/> <g:formatDate date="${new Date()}" format="hh:mm"/>

 🌍 http://www.4tablo.ir
 📱 @ChaharTabloBot