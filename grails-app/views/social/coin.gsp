 ⚡️ ${coin.name}

 آخرین: <g:if test="${coin.change != 0}">
 </g:if><g:formatNumber number="${coin.price}" type="number"/> <g:if test="${coin.change != 0}"><g:if test="${coin.change > 0}">⬆️</g:if><g:else>⬇️</g:else> <g:formatNumber number="${coin.change}" type="number"/> (<g:formatNumber number="${coin.percent}" type="number"/>%)</g:if><g:else> (بدون تغییر)</g:else>

بیشترین: <g:formatNumber number="${coin.high}" type="number"/>
کمترین: <g:formatNumber number="${coin.low}" type="number"/>

 🕒<format:jalaliDate date="${new Date()}"/> <g:formatDate date="${new Date()}" format="hh:mm"/>

 🌍 http://www.4tablo.ir
 📱 @ChaharTabloBot