/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.draganddrop.min"],e)}(function(){return function(e,t){function n(t,n){try{return e.contains(t,n)||t==n}catch(i){return!1}}function i(e){return e.clone()}function r(e){return e.clone().removeAttr("id").css("visibility","hidden")}var o=window.kendo,a=o.ui.Widget,s="start",l="beforeMove",u="move",d="end",c="change",f="cancel",h="sort",p="remove",g="receive",m=">*",v=-1,_=a.extend({init:function(e,t){var n=this;a.fn.init.call(n,e,t),n.options.placeholder||(n.options.placeholder=r),n.options.hint||(n.options.hint=i),n._draggable=n._createDraggable(),n.floating=!1},events:[s,l,u,d,c,f],options:{name:"Sortable",hint:null,placeholder:null,filter:m,holdToDrag:!1,disabled:null,container:null,connectWith:null,handler:null,cursorOffset:null,axis:null,cursor:"auto"},destroy:function(){this._draggable.destroy(),a.fn.destroy.call(this)},_createDraggable:function(){var t=this,n=t.element,i=t.options;return new o.ui.Draggable(n,{filter:i.filter,hint:o.isFunction(i.hint)?i.hint:e(i.hint),holdToDrag:i.holdToDrag,container:i.container?e(i.container):null,cursorOffset:i.cursorOffset,axis:i.axis,dragstart:e.proxy(t._dragstart,t),dragcancel:e.proxy(t._dragcancel,t),drag:e.proxy(t._drag,t),dragend:e.proxy(t._dragend,t)})},_dragstart:function(t){var n=this.draggedElement=t.currentTarget,i=t.target||o.elementUnderCursor(t),r=this.options.disabled,a=this.options.handler,l=this.options.placeholder,u=this.placeholder=o.isFunction(l)?e(l.call(this,n)):e(l);r&&n.is(r)?t.preventDefault():a&&!e(i).is(a)?t.preventDefault():this.trigger(s,{item:n,draggableEvent:t})?t.preventDefault():(this.floating=this._isFloating(n),n.css("display","none"),n.before(u),this._setCursor())},_dragcancel:function(){this._cancel(),this.trigger(f,{item:this.draggedElement}),this._resetCursor()},_drag:function(n){var i,r,o,a,s,l=this.draggedElement,u=this._findTarget(n),d={left:n.x.location,top:n.y.location},c={x:n.x.delta,y:n.y.delta},f=this.options.axis,h={item:l,list:this,draggableEvent:n};if("x"===f||"y"===f)return this._movementByAxis(f,d,c[f],h),t;if(u){if(i=this._getElementCenter(u.element),r={left:Math.round(d.left-i.left),top:Math.round(d.top-i.top)},e.extend(h,{target:u.element}),u.appendToBottom)return this._movePlaceholder(u,null,h),t;if(this.floating?0>c.x&&0>r.left?o="prev":c.x>0&&r.left>0&&(o="next"):0>c.y&&0>r.top?o="prev":c.y>0&&r.top>0&&(o="next"),o){for(s="prev"===o?jQuery.fn.prev:jQuery.fn.next,a=s.call(u.element);a.length&&!a.is(":visible");)a=s.call(a);a[0]!=this.placeholder[0]&&this._movePlaceholder(u,o,h)}}},_dragend:function(n){var i,r,o,a,s=this.placeholder,l=this.draggedElement,u=this.indexOf(l),f=this.indexOf(s),m=this.options.connectWith;return this._resetCursor(),o={action:h,item:l,oldIndex:u,newIndex:f,draggableEvent:n},f>=0?r=this.trigger(d,o):(i=s.parents(m).getKendoSortable(),o.action=p,a=e.extend({},o,{action:g,oldIndex:v,newIndex:i.indexOf(s)}),r=!(!this.trigger(d,o)&&!i.trigger(d,a))),r||f===u?(this._cancel(),t):(s.replaceWith(l),l.show(),this._draggable.dropped=!0,o={action:this.indexOf(l)!=v?h:p,item:l,oldIndex:u,newIndex:this.indexOf(l),draggableEvent:n},this.trigger(c,o),i&&(a=e.extend({},o,{action:g,oldIndex:v,newIndex:i.indexOf(l)}),i.trigger(c,a)),t)},_findTarget:function(n){var i,r,o=this._findElementUnderCursor(n),a=this.options.connectWith;return e.contains(this.element[0],o)?(i=this.items(),r=i.filter(o)[0]||i.has(o)[0],r?{element:e(r),sortable:this}:null):this.element[0]==o&&this.isEmpty()?{element:this.element,sortable:this,appendToBottom:!0}:a?this._searchConnectedTargets(o,n):t},_findElementUnderCursor:function(e){{var t=o.elementUnderCursor(e),i=e.sender;this.items()}return n(i.hint[0],t)&&(i.hint.hide(),t=o.elementUnderCursor(e),t||(t=o.elementUnderCursor(e)),i.hint.show()),t},_searchConnectedTargets:function(t,n){var i,r,o,a,s=e(this.options.connectWith);for(a=0;s.length>a;a++)if(i=s.eq(a).getKendoSortable(),e.contains(s[a],t)){if(i)return r=i.items(),o=r.filter(t)[0]||r.has(t)[0],o?(i.placeholder=this.placeholder,{element:e(o),sortable:i}):null}else if(s[a]==t&&(i&&i.isEmpty()||this._isCursorAfterLast(i,n)))return{element:s.eq(a),sortable:i,appendToBottom:!0}},_isCursorAfterLast:function(e,t){var n,i,r=e.items().last(),a={left:t.x.location,top:t.y.location};return n=o.getOffset(r),n.top+=r.outerHeight(),n.left+=r.outerWidth(),i=this.floating?n.left-a.left:n.top-a.top,0>i?!0:!1},_movementByAxis:function(t,n,i,r){var o,a="x"===t?n.left:n.top,s=0>i?this.placeholder.prev():this.placeholder.next();s.length&&!s.is(":visible")&&(s=0>i?s.prev():s.next()),e.extend(r,{target:s}),o=this._getElementCenter(s),o&&(o="x"===t?o.left:o.top),s.length&&0>i&&0>a-o?this._movePlaceholder({element:s,sortable:this},"prev",r):s.length&&i>0&&a-o>0&&this._movePlaceholder({element:s,sortable:this},"next",r)},_movePlaceholder:function(e,t,n){var i=this.placeholder;e.sortable.trigger(l,n)||(t?"prev"===t?e.element.before(i):"next"===t&&e.element.after(i):e.element.append(i),e.sortable.trigger(u,n))},_setCursor:function(){var t,n=this.options.cursor;n&&"auto"!==n&&(t=e(document.body),this._originalCursorType=t.css("cursor"),t.css({cursor:n}),this._cursorStylesheet||(this._cursorStylesheet=e("<style>* { cursor: "+n+" !important; }</style>")),this._cursorStylesheet.appendTo(t))},_resetCursor:function(){this._originalCursorType&&(e(document.body).css("cursor",this._originalCursorType),this._originalCursorType=null,this._cursorStylesheet.remove())},_getElementCenter:function(e){var t=e.length?o.getOffset(e):null;return t&&(t.top+=e.outerHeight()/2,t.left+=e.outerWidth()/2),t},_isFloating:function(e){return/left|right/.test(e.css("float"))||/inline|table-cell/.test(e.css("display"))},_cancel:function(){this.draggedElement.show(),this.placeholder.remove()},_items:function(){var e,t=this.options.filter;return e=t?this.element.find(t):this.element.children()},indexOf:function(e){var t=this._items(),n=this.placeholder,i=this.draggedElement;return n&&e[0]==n[0]?t.not(i).index(e):t.not(n).index(e)},items:function(){var e=this.placeholder,t=this._items();return e&&(t=t.not(e)),t},isEmpty:function(){return!this.items().not(":hidden").length}});o.ui.plugin(_)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
