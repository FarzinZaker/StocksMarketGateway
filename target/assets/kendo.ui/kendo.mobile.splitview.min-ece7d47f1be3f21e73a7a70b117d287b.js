/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.mobile.application.min"],e)}(function(){return function(e){var t=window.kendo,n=t.mobile.ui,i=n.Widget,o="<div class='km-expanded-pane-shim' />",r=n.View,a=r.extend({init:function(r,a){var s,l=this;i.fn.init.call(l,r,a),r=l.element,e.extend(l,a),l._id(),l._layout(),l._overlay(),l._style(),t.mobile.init(r.children(t.roleSelector("modalview"))),l.panes=[],l._paramsHistory=[],l.element.children(t.roleSelector("pane")).each(function(){s=t.initWidget(this,{},n.roles),l.panes.push(s)}),l.expandedPaneShim=e(o).appendTo(l.element),l._shimUserEvents=new t.UserEvents(l.expandedPaneShim,{tap:function(){l.collapsePanes()}})},options:{name:"SplitView",style:"horizontal"},expandPanes:function(){this.element.addClass("km-expanded-splitview")},collapsePanes:function(){this.element.removeClass("km-expanded-splitview")},_layout:function(){var n=this,i=n.element;i.data("kendoView",n).addClass("km-view km-splitview"),n.transition=t.attrValue(i,"transition"),e.extend(n,{header:[],footer:[],content:i})},_style:function(){var t,n=this.options.style,i=this.element;n&&(t=n.split(" "),e.each(t,function(){i.addClass("km-split-"+this)}))},showStart:function(){var t=this;t.element.css("display",""),t.inited||(t.inited=!0,e.each(t.panes,function(){this.options.initial?this.navigateToInitial():this.navigate("")}),t.trigger("init",{view:t})),t.trigger("show",{view:t})}});n.plugin(a)}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
