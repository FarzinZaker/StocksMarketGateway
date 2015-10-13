/**
 * Created by root on 8/26/14.
 */
$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('#header').addClass('minimized');
            //$('#header').stop().animate({
            //    paddingTop:0,
            //    paddingBottom:0
            //}, 400);

            //$('#header + .container-fluid').stop().animate({
            //    paddingTop: 55
            //}, 400);
            //$('#header #logo').stop().animate({
            //    marginTop:5,
            //    marginLeft:27
            //}, 400);
            //$('#header #logo img').stop().animate({
            //    height:42
            //}, 400);
        }
        else {
            $('#header').removeClass('minimized');
            //$('#header').stop().animate({
            //    paddingTop:10,
            //    paddingBottom:10
            //}, 400);
            //
            //$('#header + .container-fluid').stop().animate({
            //    paddingTop: 75
            //}, 400);
            //$('#header #logo').stop().animate({
            //    marginTop:0,
            //    marginLeft:0
            //}, 400);
            //$('#header #logo img').stop().animate({
            //    height:52
            //}, 400);
        }
    });
});

var notLocked = true;
$.fn.animateHighlight = function (highlightColor, duration) {
    var highlightBg = highlightColor || "#FFFF9C";
    var animateMs = duration || 1500;
    var originalBg = this.css("backgroundColor");
    if (notLocked) {
        notLocked = false;
        this.stop().css("background-color", highlightBg)
            .animate({backgroundColor: originalBg}, animateMs);
        setTimeout(function () {
            notLocked = true;
        }, animateMs);
    }
};

if(window.Prototype) {
    delete Object.prototype.toJSON;
    delete Array.prototype.toJSON;
    delete Hash.prototype.toJSON;
    delete String.prototype.toJSON;
}