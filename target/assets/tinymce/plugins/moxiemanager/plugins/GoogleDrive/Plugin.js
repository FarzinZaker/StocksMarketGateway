moxman.require(["moxman/PluginManager","moxman/util/Path","moxman/util/Loader","moxman/util/JsonRpc"],function(e,t,n,r){e.add("googledrive",function(e){function i(){r.exec("googledrive.getClientId",{},function(i){n.load({js:["//www.google.com/jsapi","//apis.google.com/js/client.js"]},function(){google.load("picker","1",{callback:function(){gapi.client.load("drive","v2");gapi.auth.authorize({client_id:i,scope:"https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/drive.file"},function(){(new google.picker.PickerBuilder).setAppId(i).setOAuthToken(gapi.auth.getToken().access_token).addView(google.picker.ViewId.DOCS).setCallback(function(n){var i,s;if(n.docs){for(var o=0;o<n.docs.length;o++){i=n.docs[o];s=gapi.client.drive.files.get({fileId:i.id});s.execute(function(n){var i=t.join(e.currentDir.path,n.title);if(n.exportLinks){n.downloadUrl=n.exportLinks["application/pdf"];n.title=n.title+".pdf"}e.showThrobber();r.exec("importFromUrl",{url:n.downloadUrl+"&access_token="+gapi.auth.getToken().access_token,path:i},function(){e.refresh(function(){e.selectByPath(i);e.hideThrobber()})})})}}}).build().setVisible(true);var n=document.getElementsByTagName("div");for(var s=0;s<n.length;s++){if(/modal\-dialog\-bg|picker\-dialog/.test(n[s].className)){n[s].style.zIndex=1e5}}})}})})})}e.addMenuItem({text:"Google Drive",icon:"cloud-download",onclick:i,contexts:["upload"]})})})