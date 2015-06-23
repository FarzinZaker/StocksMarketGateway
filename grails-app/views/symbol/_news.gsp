<g:each in="${news}" var="item" status="i">
    <div style="${i > 0 ? 'border-top:1px dashed #d9dadb;padding-top:10px;':''}">
        <div><span style="font-size:10px;display: inline-block;background-color:coral;padding:2px;border-radius:2px;padding-right:5px;padding-left:5px;color:white;margin-left:7px;">${item.type}</span> <a href="#">${item.title}</a></div>
        <span style="float:left">${item.date}</span>
        <div style="clear: both"></div>
    </div>
</g:each>