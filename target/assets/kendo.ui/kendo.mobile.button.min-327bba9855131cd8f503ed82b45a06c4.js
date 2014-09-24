/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.mobile.application.min","./kendo.userevents.min"],e)}(function(){return function(e,t){function n(t,n,i){e(n.target).closest(".km-button,.km-detail").toggleClass("km-state-active",i),c&&t.deactivateTimeoutID&&(clearTimeout(t.deactivateTimeoutID),t.deactivateTimeoutID=0)}function i(t){return e('<span class="km-badge">'+t+"</span>")}var o=window.kendo,r=o.mobile,a=r.ui,s=a.Widget,l=o.support,d=l.mobileOS,c=d.android&&d.flatVersion>=300,u="click",h="disabled",p="km-state-disabled",f=s.extend({init:function(e,t){var i=this;s.fn.init.call(i,e,t),i._wrap(),i._style(),i.options.enable=i.options.enable&&!i.element.attr(h),i.enable(i.options.enable),i._userEvents=new o.UserEvents(i.element,{press:function(e){i._activate(e)},tap:function(e){i._release(e)},release:function(e){n(i,e,!1)},end:function(e){(o.mobile.appLevelNativeScrolling()||i.viewHasNativeScrolling())&&e.preventDefault()}}),c&&i.element.on("move",function(e){i._timeoutDeactivate(e)})},destroy:function(){s.fn.destroy.call(this),this._userEvents.destroy()},events:[u],options:{name:"Button",icon:"",style:"",badge:"",enable:!0},badge:function(e){var t=this.badgeElement=this.badgeElement||i(e).appendTo(this.element);return e||0===e?(t.html(e),this):e===!1?(t.empty().remove(),this.badgeElement=!1,this):t.html()},enable:function(e){var n=this.element;t===e&&(e=!0),this.options.enable=e,e?n.removeAttr(h):n.attr(h,h),n.toggleClass(p,!e)},_timeoutDeactivate:function(e){this.deactivateTimeoutID||(this.deactivateTimeoutID=setTimeout(n,500,this,e,!1))},_activate:function(e){var t=document.activeElement,i=t?t.nodeName:"";this.options.enable&&(n(this,e,!0),("INPUT"==i||"TEXTAREA"==i)&&t.blur())},_release:function(n){var i=this;if(!(n.which>1))return i.options.enable?(i.trigger(u,{target:e(n.target),button:i.element})&&n.preventDefault(),t):(n.preventDefault(),t)},_style:function(){var t,n=this.options.style,i=this.element;n&&(t=n.split(" "),e.each(t,function(){i.addClass("km-"+this)}))},_wrap:function(){var t=this,n=t.options.icon,o=t.options.badge,r='<span class="km-icon km-'+n,a=t.element.addClass("km-button"),s=a.children("span:not(.km-icon)").addClass("km-text"),l=a.find("img").addClass("km-image");!s[0]&&a.html()&&(s=a.wrapInner('<span class="km-text" />').children("span.km-text")),!l[0]&&n&&(s[0]||(r+=" km-notext"),t.iconElement=a.prepend(e(r+'" />'))),(o||0===o)&&(t.badgeElement=i(o).appendTo(a))}}),g=f.extend({options:{name:"BackButton",style:"back"},init:function(e,n){var i=this;f.fn.init.call(i,e,n),t===i.element.attr("href")&&i.element.attr("href","#:back")}}),m=f.extend({options:{name:"DetailButton",style:""},init:function(e,t){f.fn.init.call(this,e,t)},_style:function(){var t,n=this.options.style+" detail",i=this.element;n&&(t=n.split(" "),e.each(t,function(){i.addClass("km-"+this)}))},_wrap:function(){var t=this,n=t.options.icon,i='<span class="km-icon km-'+n,o=t.element,r=o.children("span"),a=o.find("img").addClass("km-image");!a[0]&&n&&(r[0]||(i+=" km-notext"),o.prepend(e(i+'" />')))}});a.plugin(f),a.plugin(g),a.plugin(m)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
