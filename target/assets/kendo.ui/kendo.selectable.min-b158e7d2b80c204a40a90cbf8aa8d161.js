/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min","./kendo.userevents.min"],e)}(function(){return function(e,t){function n(e,t){var n=r.getOffset(e),i=t.left+t.width,o=t.top+t.height;return n.right=n.left+e.outerWidth(),n.bottom=n.top+e.outerHeight(),!(n.left>i||t.left>n.right||n.top>o||t.top>n.bottom)}var i,r=window.kendo,o=r.ui.Widget,a=e.proxy,s=Math.abs,l="aria-selected",u="k-state-selected",d="k-state-selecting",c="k-selectable",f="change",h=".kendoSelectable",p="k-state-unselecting",g=!1;!function(e){!function(){e('<div class="parent"><span /></div>').on("click",">*",function(){g=!0}).find("span").click().end().off()}()}(e),i=o.extend({init:function(t,n){var i,s=this;o.fn.init.call(s,t,n),s._marquee=e("<div class='k-marquee'><div class='k-marquee-color'></div></div>"),s._lastActive=null,s.element.addClass(c),s.relatedTarget=s.options.relatedTarget,i=s.options.multiple,s.userEvents=new r.UserEvents(s.element,{global:!0,allowSelection:!0,filter:(g?"":"."+c+" ")+s.options.filter,tap:a(s._tap,s)}),i&&s.userEvents.bind("start",a(s._start,s)).bind("move",a(s._move,s)).bind("end",a(s._end,s)).bind("select",a(s._select,s))},events:[f],options:{name:"Selectable",filter:">*",multiple:!1,relatedTarget:e.noop},_isElement:function(e){var t,n=this.element,i=n.length,r=!1;for(e=e[0],t=0;i>t;t++)if(n[t]===e){r=!0;break}return r},_tap:function(t){var n,i=e(t.target),r=this,o=t.event.ctrlKey||t.event.metaKey,a=r.options.multiple,s=a&&t.event.shiftKey,l=t.event.which,d=t.event.button;!r._isElement(i.closest("."+c))||l&&3==l||d&&2==d||(n=i.hasClass(u),a&&o||r.clear(),i=i.add(r.relatedTarget(i)),s?r.selectRange(r._firstSelectee(),i):(n&&o?(r._unselect(i),r._notify(f)):r.value(i),r._lastActive=r._downTarget=i))},_start:function(n){var i,r=this,o=e(n.target),a=o.hasClass(u),s=n.event.ctrlKey||n.event.metaKey;return r._downTarget=o,r._isElement(o.closest("."+c))?(r.options.useAllItems?r._items=r.element.find(r.options.filter):(i=o.closest(r.element),r._items=i.find(r.options.filter)),r._marquee.appendTo(document.body).css({left:n.x.client+1,top:n.y.client+1,width:0,height:0}),s||r.clear(),o=o.add(r.relatedTarget(o)),a&&(r._selectElement(o,!0),s&&o.addClass(p)),t):(r.userEvents.cancel(),t)},_move:function(e){var t=this,n={left:e.x.startLocation>e.x.location?e.x.location:e.x.startLocation,top:e.y.startLocation>e.y.location?e.y.location:e.y.startLocation,width:s(e.x.initialDelta),height:s(e.y.initialDelta)};t._marquee.css(n),t._invalidateSelectables(n,e.event.ctrlKey||e.event.metaKey),e.preventDefault()},_end:function(){var e,t=this;t._marquee.remove(),t._unselect(t.element.find(t.options.filter+"."+p)).removeClass(p),e=t.element.find(t.options.filter+"."+d),e=e.add(t.relatedTarget(e)),t.value(e),t._lastActive=t._downTarget,t._items=null},_invalidateSelectables:function(e,t){var i,r,o,a,s=this._downTarget[0],l=this._items;for(i=0,r=l.length;r>i;i++)a=l.eq(i),o=a.add(this.relatedTarget(a)),n(a,e)?a.hasClass(u)?t&&s!==a[0]&&o.removeClass(u).addClass(p):a.hasClass(d)||a.hasClass(p)||o.addClass(d):a.hasClass(d)?o.removeClass(d):t&&a.hasClass(p)&&o.removeClass(p).addClass(u)},value:function(e){var n=this,i=a(n._selectElement,n);return e?(e.each(function(){i(this)}),n._notify(f),t):n.element.find(n.options.filter+"."+u)},_firstSelectee:function(){var e,t=this;return null!==t._lastActive?t._lastActive:(e=t.value(),e.length>0?e[0]:t.element.find(t.options.filter)[0])},_selectElement:function(t,n){var i=e(t),r=!n&&this._notify("select",{element:t});i.removeClass(d),r||(i.addClass(u),this.options.aria&&i.attr(l,!0))},_notify:function(e,t){return t=t||{},this.trigger(e,t)},_unselect:function(e){return e.removeClass(u),this.options.aria&&e.attr(l,!1),e},_select:function(t){var n="input,a,textarea,.k-multiselect-wrap,select",i=r.support.browser.msie;e(t.event.target).is(n)?(this.userEvents.cancel(),this._downTarget=null):(!i||i&&!e(r._activeElement()).is(n))&&t.preventDefault()},clear:function(){var e=this.element.find(this.options.filter+"."+u);this._unselect(e)},selectRange:function(t,n){var i,r,o,a=this;for(a.clear(),a.element.length>1&&(o=a.options.continuousItems()),o&&o.length||(o=a.element.find(a.options.filter)),t=e.inArray(e(t)[0],o),n=e.inArray(e(n)[0],o),t>n&&(r=t,t=n,n=r),a.options.useAllItems||(n+=a.element.length-1),i=t;n>=i;i++)a._selectElement(o[i]);a._notify(f)},destroy:function(){var e=this;o.fn.destroy.call(e),e.element.off(h),e.userEvents.destroy(),e._marquee=e._lastActive=e.element=e.userEvents=null}}),r.ui.plugin(i)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
