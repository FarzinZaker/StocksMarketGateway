/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.scheduler.view.min"],e)}(function(){return function(e){function t(e,t){return e.slice(t).concat(e.slice(0,t))}function n(e,t){for(var n=t.firstDay,i=new Date(e.getFullYear(),e.getMonth(),0,e.getHours(),e.getMinutes(),e.getSeconds(),e.getMilliseconds());i.getDay()!=n;)r.date.setTime(i,-1*u);return i}function i(e,t,n){var i,r=t,o=n;return i=e,i>=r&&o>=i}var r=window.kendo,o=r.ui,a=o.SchedulerView,s=".kendoMonthView",l=e.extend,d=e.proxy,c=r.date.getDate,u=r.date.MS_PER_DAY,p=6,f=7,h=r.template('<span class="k-link k-nav-day">#:kendo.toString(date, "dd")#</span>'),m='<div role="gridcell" aria-selected="false" data-#=ns#uid="#=uid#"#if (resources[0]) { #style="background-color:#=resources[0].color #; border-color: #=resources[0].color#"class="k-event#=inverseColor ? " k-event-inverse" : ""#"#} else {#class="k-event"#}#><span class="k-event-actions"># if(data.tail || data.middle) {#<span class="k-icon k-i-arrow-w"></span>#}## if(data.isException()) {#<span class="k-icon k-i-exception"></span># } else if(data.isRecurring()) {#<span class="k-icon k-i-refresh"></span>#}#</span>{0}<span class="k-event-actions">#if (showDelete) {#<a href="\\#" class="k-link k-event-delete"><span class="k-icon k-si-close"></span></a>#}## if(data.head || data.middle) {#<span class="k-icon k-i-arrow-e"></span>#}#</span># if(resizable && !data.tail && !data.middle) {#<span class="k-resize-handle k-resize-w"></span>#}## if(resizable && !data.head && !data.middle) {#<span class="k-resize-handle k-resize-e"></span>#}#</div>',g=r.template('<div title="#=title.replace(/"/g,"&\\#34;")#"><div class="k-event-template">#:title#</div></div>'),v=r.template('<div style="width:#=width#px;left:#=left#px;top:#=top#px" class="k-more-events k-button"><span>...</span></div>');o.MonthView=a.extend({init:function(e,t){var n=this;a.fn.init.call(n,e,t),n.title=n.options.title,n.name="month",n._templates(),n._editable(),n._renderLayout(n.options.date),n._groups()},_updateDirection:function(e,t,n,i,r){var o,a,s,l,d;n&&(o=t[0].start,a=t[t.length-1].end,s=o.index===a.index,l=o.collectionIndex===a.collectionIndex,d=r?s&&l||l:s&&l,d&&(e.backward=i))},_changeViewPeriod:function(e,t,n){var i=n?7:1;return t&&(i*=-1),e.start=r.date.addDays(e.start,i),e.end=r.date.addDays(e.end,i),(!n||n&&this._isVerticallyGrouped())&&(e.groupIndex=t?this.groups.length-1:0),e.events=[],!0},_continuousSlot:function(e,t,n){var i=e.backward?0:t.length-1,r=this.groups[e.groupIndex];return r.continuousSlot(t[i].start,n)},_changeGroupContinuously:function(e,t,n,i){var r,o,a,s;return n||(r=e.groupIndex,o=this.groups.length-1,a=this._isVerticallyGrouped(),s=this.groups[r],!t&&a?(t=s[i?"lastSlot":"firstSlot"](),r+=i?-1:1):t&&!a&&(r=i?o:0),(0>r||r>o)&&(r=i?o:0,t=null),e.groupIndex=r),t},_normalizeHorizontalSelection:function(e,t,n){var i;return i=n?t[0].start:t[t.length-1].end},_normalizeVerticalSelection:function(e,t){var n;return n=e.backward?t[0].start:t[t.length-1].end},_templates:function(){var e=this.options,t=l({},r.Template,e.templateSettings);this.eventTemplate=this._eventTmpl(e.eventTemplate),this.dayTemplate=r.template(e.dayTemplate,t)},dateForTitle:function(){return r.format(this.options.selectedDateFormat,this._firstDayOfMonth,this._lastDayOfMonth)},nextDate:function(){return r.date.nextDay(this._lastDayOfMonth)},previousDate:function(){return r.date.previousDay(this._firstDayOfMonth)},startDate:function(){return this._startDate},endDate:function(){return this._endDate},_renderLayout:function(t){var i=this;this._firstDayOfMonth=r.date.firstDayOfMonth(t),this._lastDayOfMonth=r.date.lastDayOfMonth(t),this._startDate=n(t,this.calendarInfo()),this.createLayout(this._layout()),this._content(),this.refreshLayout(),this.content.on("click"+s,".k-nav-day,.k-more-events",function(t){var n=e(t.currentTarget).offset(),r=i._slotByPosition(n.left,n.top);t.preventDefault(),i.trigger("navigate",{view:"day",date:r.startDate()})})},_editable:function(){this.options.editable&&!this._isMobilePhoneView()&&(this._isMobile()?this._touchEditable():this._mouseEditable())},_mouseEditable:function(){var t=this;t.element.on("click"+s,".k-scheduler-monthview .k-event a:has(.k-si-close)",function(n){t.trigger("remove",{uid:e(this).closest(".k-event").attr(r.attr("uid"))}),n.preventDefault()}),t.options.editable.create!==!1&&t.element.on("dblclick"+s,".k-scheduler-monthview .k-scheduler-content td",function(n){var i,r=e(n.currentTarget).offset(),o=t._slotByPosition(r.left,r.top);o&&(i=t._resourceBySlot(o),t.trigger("add",{eventInfo:l({isAllDay:!0,start:o.startDate(),end:o.startDate()},i)})),n.preventDefault()}),t.options.editable.update!==!1&&t.element.on("dblclick"+s,".k-scheduler-monthview .k-event",function(n){t.trigger("edit",{uid:e(this).closest(".k-event").attr(r.attr("uid"))}),n.preventDefault()})},_touchEditable:function(){var t=this;t.options.editable.create!==!1&&(t._addUserEvents=new r.UserEvents(t.element,{filter:".k-scheduler-monthview .k-scheduler-content td",tap:function(n){var i,r=e(n.target).offset(),o=t._slotByPosition(r.left,r.top);o&&(i=t._resourceBySlot(o),t.trigger("add",{eventInfo:l({isAllDay:!0,start:o.startDate(),end:o.startDate()},i)})),n.preventDefault()}})),t.options.editable.update!==!1&&(t._editUserEvents=new r.UserEvents(t.element,{filter:".k-scheduler-monthview .k-event",tap:function(n){0===e(n.event.target).closest("a:has(.k-si-close)").length&&(t.trigger("edit",{uid:e(n.target).closest(".k-event").attr(r.attr("uid"))}),n.preventDefault())}}))},selectionByElement:function(t){var n=e(t).offset();return this._slotByPosition(n.left,n.top)},_columnCountForLevel:function(e){var t=this.columnLevels[e];return t?t.length:0},_rowCountForLevel:function(e){var t=this.rowLevels[e];return t?t.length:0},_content:function(){var e,t="<tbody>",n=1,i=this.groupedResources;for(i.length&&this._isVerticallyGrouped()&&(n=this._rowCountForLevel(i.length-1)),e=0;n>e;e++)t+=this._createCalendar(e);t+="</tbody>",this.content.find("table").html(t)},_createCalendar:function(e){var t,n,i,o,a=this.startDate(),s=f*p,l=f,d=[a],c="",u=1,h=this._isVerticallyGrouped(),m=this.groupedResources;for(m.length&&(h||(u=this._columnCountForLevel(m.length-1))),this._slotIndices={},t=0,n=s/l;n>t;t++){for(c+="<tr>",d.push(a),i=t*l,o=0;u>o;o++)c+=this._createRow(a,i,l,h?e:o);a=r.date.addDays(a,l),c+="</tr>"}return this._weekStartDates=d,this._endDate=r.date.previousDay(a),c},_createRow:function(e,t,n,i){var o,a=this,s=a._firstDayOfMonth,l=a._lastDayOfMonth,d=a.dayTemplate,u="",p="",f=function(){return a._resourceBySlot({groupIndex:i})};for(o=0;n>o;o++)u="",r.date.isToday(e)&&(u+="k-today"),r.date.isInDateRange(e,s,l)||(u+=" k-other-month"),p+="<td ",""!==u&&(p+='class="'+u+'"'),p+=">",p+=d({date:e,resources:f}),p+="</td>",a._slotIndices[c(e).getTime()]=t+o,e=r.date.nextDay(e);return p},_layout:function(){var n,i,r,o=this.calendarInfo(),a=this._isMobile()?o.days.namesShort:o.days.names,s=t(a,o.firstDay),l=e.map(s,function(e){return{text:e}}),d=this.groupedResources;if(d.length)if(this._isVerticallyGrouped()){for(i=[],r=0;6>r;r++)i.push({text:"<div>&nbsp;</div>",className:"k-hidden k-slot-cell"});n=this._createRowsLayout(d,i)}else l=this._createColumnsLayout(d,l);return{columns:l,rows:n}},_eventTmpl:function(e){var t,n=this.options,i=l({},r.Template,n.templateSettings),o=i.paramName,a="",s=typeof e,c={storage:{},count:0};return"function"===s?(c.storage["tmpl"+c.count]=e,a+="#=this.tmpl"+c.count+"("+o+")#",c.count++):"string"===s&&(a+=e),t=r.template(r.format(m,a),i),c.count>0&&(t=d(t,c.storage)),t},_createEventElement:function(t){var n=this.options,i=n.editable,o=this._isMobile();return t.showDelete=i&&i.destroy!==!1&&!o,t.resizable=i&&i.resize!==!1&&!o,t.ns=r.ns,t.resources=this.eventResources(t),t.inverseColor=t.resources&&t.resources[0]?this._shouldInverseResourceColor(t.resources[0]):!1,e(this.eventTemplate(t))},_isInDateSlot:function(e){var t=this.groups[0],n=t.firstSlot().start,o=t.lastSlot().end-1,a=r.date.toUtcTime(e.start),s=r.date.toUtcTime(e.end);return(i(a,n,o)||i(s,n,o)||i(n,a,s)||i(o,a,s))&&(!i(s,n,n)||i(s,a,a)||e.isAllDay)},_slotIndex:function(e){return this._slotIndices[c(e).getTime()]},_positionMobileEvent:function(t,n,i){var o,s,l,d,c,u,p,f=t.start;t.start.offsetLeft>t.end.offsetLeft&&(f=t.end),o=t.start.index,s=o,l=3,d=a.collidingEvents(t.events(),o,s),d.push({element:n,start:o,end:s}),c=a.createRows(d),u=t.collection.at(o),p=u.container,p||(p=e(r.format('<div class="k-events-container" style="top:{0};left:{1};width:{2}"/>',f.offsetTop+f.firstChildTop+f.firstChildHeight-3+"px",f.offsetLeft+"px",f.offsetWidth+"px")),u.container=p,this.content[0].appendChild(p[0])),l>=c.length&&(t.addEvent({element:n,start:o,end:s,groupIndex:f.groupIndex}),i._continuousEvents.push({element:n,uid:n.attr(r.attr("uid")),start:t.start,end:t.end}),p[0].appendChild(n[0]))},_positionEvent:function(t,n,i){var o,s,l,d,c,u,p,f,h,m,g,_,k,b,w,y=this.options.eventHeight,C=t.start;for(t.start.offsetLeft>t.end.offsetLeft&&(C=t.end),o=t.start.index,s=t.end.index,l=C.eventCount,d=a.collidingEvents(t.events(),o,s),c=o!==s?5:4,d.push({element:n,start:o,end:s}),u=a.createRows(d),p=0,f=Math.min(u.length,l);f>p;p++)for(h=u[p].events,m=C.offsetTop+C.firstChildHeight+p*y+3*p+"px",g=0,_=h.length;_>g;g++)h[g].element[0].style.top=m;if(u.length>l)for(k=o;s>=k;k++){if(b=t.collection,w=b.at(k),w.more)return;w.more=e(v({ns:r.ns,start:k,end:k,width:w.clientWidth-2,left:w.offsetLeft+2,top:w.offsetTop+w.firstChildHeight+l*y+3*l})),this.content[0].appendChild(w.more[0])}else t.addEvent({element:n,start:o,end:s,groupIndex:C.groupIndex}),n[0].style.width=t.innerWidth()-c+"px",n[0].style.left=C.offsetLeft+2+"px",n[0].style.height=y+"px",i._continuousEvents.push({element:n,uid:n.attr(r.attr("uid")),start:t.start,end:t.end}),this.content[0].appendChild(n[0])},_slotByPosition:function(e,t){var n,i,r=this.content.offset();for(e-=r.left,t-=r.top,t+=this.content[0].scrollTop,e+=this.content[0].scrollLeft,e=Math.ceil(e),t=Math.ceil(t),n=0;this.groups.length>n;n++)if(i=this.groups[n].daySlotByPosition(e,t))return i;return null},_createResizeHint:function(e){var t=e.startSlot().offsetLeft,n=e.start.offsetTop,i=e.innerWidth(),r=e.start.clientHeight-2,o=a.fn._createResizeHint.call(this,t,n,i,r);o.appendTo(this.content),this._resizeHint=this._resizeHint.add(o)},_updateResizeHint:function(e,t,n,i){var o,a,s;for(this._removeResizeHint(),o=this.groups[t],a=o.ranges(n,i,!0,e.isAllDay),s=0;a.length>s;s++)this._createResizeHint(a[s]);this._resizeHint.find(".k-label-top,.k-label-bottom").text(""),this._resizeHint.first().addClass("k-first").find(".k-label-top").text(r.toString(r.timezone.toLocalDate(n),"M/dd")),this._resizeHint.last().addClass("k-last").find(".k-label-bottom").text(r.toString(r.timezone.toLocalDate(i),"M/dd"))},_updateMoveHint:function(e,t,n){var i,o,a,s,l,d=r.date.toUtcTime(e.start)+n,c=d+e.duration(),u=this.groups[t],p=u.ranges(d,c,!0,e.isAllDay);for(this._removeMoveHint(),i=0;p.length>i;i++)o=p[i],a=o.startSlot(),s=o.endSlot(),l=this._createEventElement(e.clone({head:o.head,tail:o.tail})),l.css({left:a.offsetLeft+2,top:a.offsetTop+a.firstChildHeight,height:this.options.eventHeight,width:o.innerWidth()-(a.index!==s.index?5:4)}),l.addClass("k-event-drag-hint"),l.appendTo(this.content),this._moveHint=this._moveHint.add(l)},_groups:function(){var e,t,n,i,o,a,s,l,d,c,u,h,m,g,v,_,k,b=this._groupCount(),w=f,y=p;for(this.groups=[],e=0;b>e;e++)this._addResourceView(e);for(t=this.content[0].getElementsByTagName("tr"),n=0;b>n;n++)for(i=0,o=0,this._isVerticallyGrouped()&&(o=n),a=o*y;(o+1)*y>a;a++)for(s=this.groups[n],l=s.addDaySlotCollection(r.date.addDays(this.startDate(),i),r.date.addDays(this.startDate(),i+w)),d=t[a],c=d.children,u=0,d.setAttribute("role","row"),this._isVerticallyGrouped()||(u=n),h=u*w;(u+1)*w>h;h++)m=c[h],g=m.clientHeight,v=m.children.length?m.children[0].offsetHeight+3:0,_=r.date.toUtcTime(r.date.addDays(this.startDate(),i)),i++,k=Math.floor((g-v-this.options.moreButtonHeight)/(this.options.eventHeight+3)),m.setAttribute("role","gridcell"),m.setAttribute("aria-selected",!1),l.addDaySlot(m,_,_+r.date.MS_PER_DAY,k)},render:function(e){this.content.children(".k-event,.k-more-events,.k-events-container").remove(),this._groups(),e=new r.data.Query(e).sort([{field:"start",dir:"asc"},{field:"end",dir:"desc"}]).toArray();var t=this.groupedResources;t.length?this._renderGroups(e,t,0,1):this._renderEvents(e,0),this.refreshLayout(),this.trigger("activate")},_renderEvents:function(e,t){var n,i,r,o,a,s,l,d,c,u,p,f=this._isMobilePhoneView();for(i=0,r=e.length;r>i;i++)if(n=e[i],this._isInDateSlot(n))for(o=this.groups[t],o._continuousEvents||(o._continuousEvents=[]),a=o.slotRanges(n,!0),s=a.length,l=0;s>l;l++)d=a[l],c=n.start,u=n.end,s>1&&(0===l?u=d.end.endDate():l==s-1?c=d.start.startDate():(c=d.start.startDate(),u=d.end.endDate())),p=n.clone({start:c,end:u,head:d.head,tail:d.tail}),f?this._positionMobileEvent(d,this._createEventElement(p),o):this._positionEvent(d,this._createEventElement(p),o)},_renderGroups:function(e,t,n,i){var o,s,l,d,c=t[0];if(c)for(o=c.dataSource.view(),s=0;o.length>s;s++)l=this._resourceValue(c,o[s]),d=new r.data.Query(e).filter({field:c.field,operator:a.groupEqFilter(l)}).toArray(),t.length>1?n=this._renderGroups(d,t.slice(1),n++,i+1):this._renderEvents(d,n++);return n},_groupCount:function(){var e=this.groupedResources;return e.length?this._isVerticallyGrouped()?this._rowCountForLevel(e.length-1):this._columnCountForLevel(e.length)/this._columnOffsetForResource(e.length):1},_columnOffsetForResource:function(e){return this._columnCountForLevel(e)/this._columnCountForLevel(e-1)},destroy:function(){this.table&&this.table.removeClass("k-scheduler-monthview"),this.content&&this.content.off(s),this.element&&this.element.off(s),a.fn.destroy.call(this),this._isMobile()&&!this._isMobilePhoneView()&&this.options.editable&&(this.options.editable.create!==!1&&this._addUserEvents.destroy(),this.options.editable.update!==!1&&this._editUserEvents.destroy())},events:["remove","add","edit","navigate"],options:{title:"Month",name:"month",eventHeight:25,moreButtonHeight:13,editable:!0,selectedDateFormat:"{0:y}",dayTemplate:h,eventTemplate:g}})}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
