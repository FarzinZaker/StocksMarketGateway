 ⚡️ ${currency.name}

 آخرین: <g:if test="${currency.change != 0}">
 </g:if><g:formatNumber number="${currency.price}" type="number"/> <g:if test="${currency.change != 0}"><g:if test="${currency.change > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${currency.change}" type="number"/> (<g:formatNumber number="${currency.percent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

بیشترین: <g:formatNumber number="${currency.high}" type="number"/>
کمترین: <g:formatNumber number="${currency.low}" type="number"/>

 🕒<format:jalaliDate date="${new Date()}"/> <g:formatDate date="${new Date()}" format="hh:mm"/>

 🌍 http://www.4tablo.ir
 📱 @ChaharTabloBot