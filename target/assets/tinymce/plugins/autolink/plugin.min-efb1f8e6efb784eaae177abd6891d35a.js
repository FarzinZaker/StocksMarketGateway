tinymce.PluginManager.add("autolink",function(n){function t(n){o(n,-1,"(",!0)}function e(n){o(n,0,"",!0)}function i(n){o(n,-1,"",!1)}function o(n,t,e){function i(n,t){if(0>t&&(t=0),3==n.nodeType){var e=n.data.length;t>e&&(t=e)}return t}function o(n,t){f.setStart(n,i(n,t))}function r(n,t){f.setEnd(n,i(n,t))}var f,d,a,s,c,l,u,g,h,C;if(f=n.selection.getRng(!0).cloneRange(),f.startOffset<5){if(g=f.endContainer.previousSibling,!g){if(!f.endContainer.firstChild||!f.endContainer.firstChild.nextSibling)return;g=f.endContainer.firstChild.nextSibling}if(h=g.length,o(g,h),r(g,h),f.endOffset<5)return;d=f.endOffset,s=g}else{if(s=f.endContainer,3!=s.nodeType&&s.firstChild){for(;3!=s.nodeType&&s.firstChild;)s=s.firstChild;3==s.nodeType&&(o(s,0),r(s,s.nodeValue.length))}d=1==f.endOffset?2:f.endOffset-1-t}a=d;do o(s,d>=2?d-2:0),r(s,d>=1?d-1:0),d-=1,C=f.toString();while(" "!=C&&""!==C&&160!=C.charCodeAt(0)&&d-2>=0&&C!=e);f.toString()==e||160==f.toString().charCodeAt(0)?(o(s,d),r(s,a),d+=1):0===f.startOffset?(o(s,0),r(s,a)):(o(s,d),r(s,a)),l=f.toString(),"."==l.charAt(l.length-1)&&r(s,a-1),l=f.toString(),u=l.match(/^(https?:\/\/|ssh:\/\/|ftp:\/\/|file:\/|www\.|(?:mailto:)?[A-Z0-9._%+\-]+@)(.+)$/i),u&&("www."==u[1]?u[1]="http://www.":/@$/.test(u[1])&&!/^mailto:/.test(u[1])&&(u[1]="mailto:"+u[1]),c=n.selection.getBookmark(),n.selection.setRng(f),n.execCommand("createlink",!1,u[1]+u[2]),n.selection.moveToBookmark(c),n.nodeChanged())}var r;return n.on("keydown",function(t){return 13==t.keyCode?i(n):void 0}),tinymce.Env.ie?void n.on("focus",function(){if(!r){r=!0;try{n.execCommand("AutoUrlDetect",!1,!0)}catch(t){}}}):(n.on("keypress",function(e){return 41==e.keyCode?t(n):void 0}),void n.on("keyup",function(t){return 32==t.keyCode?e(n):void 0}))});
