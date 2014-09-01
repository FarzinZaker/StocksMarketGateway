/*
* Kendo UI Complete v2014.1.318 (http://kendoui.com)
* Copyright 2014 Telerik AD. All rights reserved.
*
* Kendo UI Complete commercial licenses may be obtained at
* http://www.telerik.com/purchase/license-agreement/kendo-ui-complete
* If you do not own a commercial license, this file shall be governed by the trial license terms.
*/
!function(e,define){define(["./kendo.core.min"],e)}(function(){return function(e){function t(e,t){var n=e.x.location,i=e.y.location,r=t.x.location,o=t.y.location,a=n-r,s=i-o;return{center:{x:(n+r)/2,y:(i+o)/2},distance:Math.sqrt(a*a+s*s)}}function n(e){var t,n,i,r=[],o=e.originalEvent,s=e.currentTarget,u=0;if(e.api)r.push({id:2,event:e,target:e.target,currentTarget:e.target,location:e});else if(e.type.match(/touch/))for(n=o?o.changedTouches:[],t=n.length;t>u;u++)i=n[u],r.push({location:i,event:e,target:i.target,currentTarget:s,id:i.identifier});else a.pointers||a.msPointers?r.push({location:o,event:e,target:e.target,currentTarget:s,id:o.pointerId}):r.push({id:1,event:e,target:e.target,currentTarget:s,location:e});return r}function i(t){var n,i,r;t.preventDefault(),n=e(t.data.root),i=n.closest(".k-widget").parent(),i[0]||(i=n.parent()),r=e.extend(!0,{},t,{target:n[0]}),i.trigger(e.Event(t.type,r))}function r(e){for(var t=o.eventMap.up.split(" "),n=0,i=t.length;i>n;n++)e(t[n])}var o=window.kendo,a=o.support,s=window.document,u=o.Class,l=o.Observable,c=e.now,d=e.extend,f=a.mobileOS,h=f&&f.android,p=800,g=a.browser.msie?5:0,m="press",v="hold",y="select",_="start",b="move",w="end",x="cancel",F="tap",k="release",S="gesturestart",M="gesturechange",C="gestureend",T="gesturetap",D=u.extend({init:function(e,t){var n=this;n.axis=e,n._updateLocationData(t),n.startLocation=n.location,n.velocity=n.delta=0,n.timeStamp=c()},move:function(e){var t=this,n=e["page"+t.axis],i=c(),r=i-t.timeStamp||1;(n||!h)&&(t.delta=n-t.location,t._updateLocationData(e),t.initialDelta=n-t.startLocation,t.velocity=t.delta/r,t.timeStamp=i)},_updateLocationData:function(e){var t=this,n=t.axis;t.location=e["page"+n],t.client=e["client"+n],t.screen=e["screen"+n]}}),z=u.extend({init:function(e,t,n){d(this,{x:new D("X",n.location),y:new D("Y",n.location),userEvents:e,target:t,currentTarget:n.currentTarget,initialTouch:n.target,id:n.id,pressEvent:n,_moved:!1,_finished:!1})},press:function(){this._holdTimeout=setTimeout(e.proxy(this,"_hold"),this.userEvents.minHold),this._trigger(m,this.pressEvent)},_hold:function(){this._trigger(v,this.pressEvent)},move:function(e){var t=this;if(!t._finished){if(t.x.move(e.location),t.y.move(e.location),!t._moved){if(t._withinIgnoreThreshold())return;if(E.current&&E.current!==t.userEvents)return t.dispose();t._start(e)}t._finished||t._trigger(b,e)}},end:function(e){var t=this;t.endTime=c(),t._finished||(t._finished=!0,t._moved?t._trigger(w,e):t._trigger(F,e),clearTimeout(t._holdTimeout),t._trigger(k,e),t.dispose())},dispose:function(){var t=this.userEvents,n=t.touches;this._finished=!0,this.pressEvent=null,clearTimeout(this._holdTimeout),n.splice(e.inArray(this,n),1)},skip:function(){this.dispose()},cancel:function(){this.dispose()},isMoved:function(){return this._moved},_start:function(e){clearTimeout(this._holdTimeout),this.startTime=c(),this._moved=!0,this._trigger(_,e)},_trigger:function(e,t){var n=this,i=t.event,r={touch:n,x:n.x,y:n.y,target:n.target,event:i};n.userEvents.notify(e,r)&&i.preventDefault()},_withinIgnoreThreshold:function(){var e=this.x.initialDelta,t=this.y.initialDelta;return Math.sqrt(e*e+t*t)<=this.userEvents.threshold}}),E=l.extend({init:function(t,n){var i,u,c,f=this,h=o.guid();n=n||{},i=f.filter=n.filter,f.threshold=n.threshold||g,f.minHold=n.minHold||p,f.touches=[],f._maxTouches=n.multiTouch?2:1,f.allowSelection=n.allowSelection,f.captureUpIfMoved=n.captureUpIfMoved,f.eventNS=h,t=e(t).handler(f),l.fn.init.call(f),d(f,{element:t,surface:n.global?e(s.documentElement):e(n.surface||t),stopPropagation:n.stopPropagation,pressed:!1}),f.surface.handler(f).on(o.applyEventMap("move",h),"_move").on(o.applyEventMap("up cancel",h),"_end"),t.on(o.applyEventMap("down",h),i,"_start"),(a.pointers||a.msPointers)&&t.css("-ms-touch-action","pinch-zoom double-tap-zoom"),n.preventDragEvent&&t.on(o.applyEventMap("dragstart",h),o.preventDefault),t.on(o.applyEventMap("mousedown selectstart",h),i,{root:t},"_select"),f.captureUpIfMoved&&a.eventCapture&&(u=f.surface[0],c=e.proxy(f.preventIfMoving,f),r(function(e){u.addEventListener(e,c,!0)})),f.bind([m,v,F,_,b,w,k,x,S,M,C,T,y],n)},preventIfMoving:function(e){this._isMoved()&&e.preventDefault()},destroy:function(){var e,t=this;t._destroyed||(t._destroyed=!0,t.captureUpIfMoved&&a.eventCapture&&(e=t.surface[0],r(function(n){e.removeEventListener(n,t.preventIfMoving)})),t.element.kendoDestroy(t.eventNS),t.surface.kendoDestroy(t.eventNS),t.element.removeData("handler"),t.surface.removeData("handler"),t._disposeAll(),t.unbind(),delete t.surface,delete t.element,delete t.currentTarget)},capture:function(){E.current=this},cancel:function(){this._disposeAll(),this.trigger(x)},notify:function(e,n){var i=this,r=i.touches;if(this._isMultiTouch()){switch(e){case b:e=M;break;case w:e=C;break;case F:e=T}d(n,{touches:r},t(r[0],r[1]))}return this.trigger(e,n)},press:function(e,t,n){this._apiCall("_start",e,t,n)},move:function(e,t){this._apiCall("_move",e,t)},end:function(e,t){this._apiCall("_end",e,t)},_isMultiTouch:function(){return this.touches.length>1},_maxTouchesReached:function(){return this.touches.length>=this._maxTouches},_disposeAll:function(){for(var e=this.touches;e.length>0;)e.pop().dispose()},_isMoved:function(){return e.grep(this.touches,function(e){return e.isMoved()}).length},_select:function(e){(!this.allowSelection||this.trigger(y,{event:e}))&&i(e)},_start:function(t){var i,r,o=this,a=0,s=o.filter,u=n(t),l=u.length,c=t.which;if(!(c&&c>1||o._maxTouchesReached()))for(E.current=null,o.currentTarget=t.currentTarget,o.stopPropagation&&t.stopPropagation();l>a&&!o._maxTouchesReached();a++)r=u[a],i=s?e(r.currentTarget):o.element,i.length&&(r=new z(o,i,r),o.touches.push(r),r.press(),o._isMultiTouch()&&o.notify("gesturestart",{}))},_move:function(e){this._eachTouch("move",e)},_end:function(e){this._eachTouch("end",e)},_eachTouch:function(e,t){var i,r,o,a,s=this,u={},l=n(t),c=s.touches;for(i=0;c.length>i;i++)r=c[i],u[r.id]=r;for(i=0;l.length>i;i++)o=l[i],a=u[o.id],a&&a[e](o)},_apiCall:function(t,n,i,r){this[t]({api:!0,pageX:n,pageY:i,clientX:n,clientY:i,target:e(r||this.element)[0],stopPropagation:e.noop,preventDefault:e.noop})}});E.minHold=function(e){p=e},o.getTouches=n,o.touchDelta=t,o.UserEvents=E}(window.kendo.jQuery),window.kendo},"function"==typeof define&&define.amd?define:function(e,t){t()});
