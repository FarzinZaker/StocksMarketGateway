moxman.require(["moxman/PluginManager","moxman/vfs/FileSystemManager","moxman/util/JsonRpc"],function(e,t,n){e.add("favorites",function(e){function r(){n.exec("favorites.add",{paths:e.getSelectedFiles().toPathArray()})}function i(){var t=[];e.getSelectedFiles().each(function(e){t.push(e.info.link)});n.exec("favorites.remove",{paths:t},function(){e.refresh()})}function s(){t.getFile(e.getSelectedFiles()[0].info.link,function(t){e.open(t)})}e.on("BeforeRenderManageMenu",function(t){var n=t.menu;if(e.currentDir.path=="/Favorites"){t.preventDefault();n.append({text:"Remove favorite",onclick:i});n.append({text:"Goto file",onclick:s})}});e.addMenuItem({text:"Add favorite",icon:"favorites",onclick:r,contexts:["manage.tools"]})})})