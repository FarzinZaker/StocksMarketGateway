/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.mobile.application.min"],e)}(function(){return function(e,t){function n(n,i){var r=i.find("["+o.attr("align")+"="+n+"]");return r[0]?e('<div class="km-'+n+'item" />').append(r).prependTo(i):t}function i(t){var n=t.siblings(),i=!!t.children("ul")[0],o=!!n[0]&&""===e.trim(t.text());t.prevAll().toggleClass("km-absolute",i),t.toggleClass("km-show-title",o),t.toggleClass("km-fill-title",o&&!e.trim(t.html())),t.toggleClass("km-no-title",i),t.toggleClass("km-hide-title","hidden"==t.css("visibility")&&!n.children().is(":visible"))}var o=window.kendo,r=o.mobile,a=r.ui,s=o.roleSelector,l=a.Widget,d=l.extend({init:function(t,i){var o=this;l.fn.init.call(o,t,i),t=o.element,o.container().bind("show",e.proxy(this,"refresh")),t.addClass("km-navbar").wrapInner(e('<div class="km-view-title km-show-title" />')),o.leftElement=n("left",t),o.rightElement=n("right",t),o.centerElement=t.find(".km-view-title")},options:{name:"NavBar"},title:function(e){this.element.find(s("view-title")).text(e),i(this.centerElement)},refresh:function(e){var t=e.view;t.options.title?this.title(t.options.title):i(this.centerElement)},destroy:function(){l.fn.destroy.call(this),o.destroy(this.element)}});a.plugin(d)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
