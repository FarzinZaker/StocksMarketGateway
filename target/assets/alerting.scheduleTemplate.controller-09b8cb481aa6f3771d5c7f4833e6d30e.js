var alertingQuery=angular.module("stocks",[]);alertingQuery.controller("alertingScheduleTemplateController",function(e,t){e.intervalSteps=intervalSteps;e.intervalStepCounter=intervalSteps.length;e.addIntervalStep=function(){e.intervalSteps[e.intervalSteps.length]={value:Math.max.apply(Math,$.map(e.intervalSteps,function(e){return e.value}))*2};e.intervalStepCounter+=1;return false};e.removeIntervalStep=function(t){if(e.intervalSteps.length==1)return;e.intervalSteps.splice(parseInt(t),1);e.intervalStepCounter-=1;return false}})