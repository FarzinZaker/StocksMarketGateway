(function(e){var t=e.getOptions(),n=t.plotOptions,r=e.seriesTypes,i=e.merge,s=function(){},o=e.each;n.funnel=i(n.pie,{animation:!1,center:["50%","50%"],width:"90%",neckWidth:"30%",height:"100%",neckHeight:"25%",reversed:!1,dataLabels:{connectorWidth:1,connectorColor:"#606060"},size:!0,states:{select:{color:"#C0C0C0",borderColor:"#000000",shadow:!1}}});r.funnel=e.extendClass(r.pie,{type:"funnel",animate:s,singularTooltips:!0,translate:function(){var e=function(e,t){return/%$/.test(e)?t*parseInt(e,10)/100:parseInt(e,10)},t=0,n=this.chart,r=this.options,i=r.reversed,u=n.plotWidth,a=n.plotHeight,f=0,n=r.center,l=e(n[0],u),c=e(n[0],a),h=e(r.width,u),p,d,v=e(r.height,a),m=e(r.neckWidth,u),g=e(r.neckHeight,a),y=v-g,e=this.data,b,w,E=r.dataLabels.position==="left"?1:0,S,x,T,N,C,k,L;this.getWidthAt=d=function(e){return e>v-g||v===g?m:m+(h-m)*((v-g-e)/(v-g))};this.getX=function(e,t){return l+(t?-1:1)*(d(i?a-e:e)/2+r.dataLabels.distance)};this.center=[l,c,v];this.centerX=l;o(e,function(e){t+=e.y});o(e,function(e){L=null;w=t?e.y/t:0;x=c-v/2+f*v;C=x+w*v;p=d(x);S=l-p/2;T=S+p;p=d(C);N=l-p/2;k=N+p;x>y?(S=N=l-m/2,T=k=l+m/2):C>y&&(L=C,p=d(y),N=l-p/2,k=N+p,C=y);i&&(x=v-x,C=v-C,L=L?v-L:null);b=["M",S,x,"L",T,x,k,C];L&&b.push(k,L,N,L);b.push(N,C,"Z");e.shapeType="path";e.shapeArgs={d:b};e.percentage=w*100;e.plotX=l;e.plotY=(x+(L||C))/2;e.tooltipPos=[l,e.plotY];e.slice=s;e.half=E;f+=w})},drawPoints:function(){var e=this,t=e.options,n=e.chart.renderer;o(e.data,function(r){var i=r.graphic,s=r.shapeArgs;i?i.animate(s):r.graphic=n.path(s).attr({fill:r.color,stroke:t.borderColor,"stroke-width":t.borderWidth}).add(e.group)})},sortByAngle:function(e){e.sort(function(e,t){return e.plotY-t.plotY})},drawDataLabels:function(){var e=this.data,t=this.options.dataLabels.distance,n,i,s,o=e.length,u,a;for(this.center[2]-=2*t;o--;)s=e[o],i=(n=s.half)?1:-1,a=s.plotY,u=this.getX(a,n),s.labelPos=[0,a,u+(t-5)*i,a,u+t*i,a,n?"right":"left",0];r.pie.prototype.drawDataLabels.call(this)}});t.plotOptions.pyramid=e.merge(t.plotOptions.funnel,{neckWidth:"0%",neckHeight:"0%",reversed:!0});e.seriesTypes.pyramid=e.extendClass(e.seriesTypes.funnel,{type:"pyramid"})})(Highcharts)