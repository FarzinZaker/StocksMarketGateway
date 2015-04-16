<%@ page import="stocks.User" %>

<form:field fieldName="customSymbolPriority.name" showHelp="0">
    <form:textBox name="name" validation="required" style="width:450px;" value="${item?.name}"
                  readonly="${item ? 'true' : 'false'}"/>
</form:field>
<form:field fieldName="customSymbolPriority.symbol" showHelp="0">
    <form:select
            items="${stocks.portfolio.basic.CustomSymbol.findAllByOwnerAndDeleted(stocks.User.findByUsername(sec.username()), false).collect {
                [text: it.name, value: it.id]
            }}" name="symbol.id" id="symbolId" value="${item?.symbol?.id}" style="width:450px;"
            placeholder="${message(code: 'please-select')}" preSelect="false"/>
</form:field>