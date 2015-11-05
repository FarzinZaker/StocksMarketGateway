 ⚡️ ${oil.name}

 آخرین: <g:if test="${oil.change != 0}">
 </g:if><g:formatNumber number="${oil.price}" type="number"/> <g:if test="${oil.change != 0}"><g:if test="${oil.change > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${oil.change}" type="number"/> (<g:formatNumber number="${oil.percent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

بیشترین: <g:formatNumber number="${oil.high}" type="number"/>
کمترین: <g:formatNumber number="${oil.low}" type="number"/>

 🕒<format:jalaliDate date="${new Date()}"/> <g:formatDate date="${new Date()}" format="hh:mm"/>

 🌍 http://www.4tablo.ir
 📱 @ChaharTabloBot