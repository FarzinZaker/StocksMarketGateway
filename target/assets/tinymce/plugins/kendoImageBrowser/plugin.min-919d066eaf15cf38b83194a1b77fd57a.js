/**
 * Created by root on 6/26/14.
 */
var kendoImageBrowserSrc;
var kendoImageBrowserWindow;
function openKendoImageBrowser(p) {

    kendoImageBrowserWindow = tinyMCE.activeEditor.windowManager.open({
        url : getKendoImageBrowserUrl(),
        width : 738,
        height : 530,
        title: 'انتخاب تصویر'
    }, {
        custom_param : 1
    });

    kendoImageBrowserSrc = $($($(p.currentTarget).parent().context).find('input').first());
}

function imageSelected(file){
    kendoImageBrowserSrc.attr('value', file);
    kendoImageBrowserSrc.focus();
    kendoImageBrowserWindow.close();
}



