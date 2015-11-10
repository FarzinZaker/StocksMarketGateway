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

$.fn.fadeInWithDelay = function () {
    var delay = 0;
    return this.each(function () {
        $(this).delay(delay).animate({opacity: 1}, 200);
        delay += 100;
    });
};

function isElementInViewport (el) {

    //special bonus for those using jQuery
    if (typeof jQuery === "function" && el instanceof jQuery) {
        el = el[0];
    }

    var rect = el.getBoundingClientRect();

    return (
        rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && /*or $(window).height() */
        rect.right <= (window.innerWidth || document.documentElement.clientWidth) /*or $(window).width() */
    );
}

function onVisibilityChange(el, callback) {
    if (isElementInViewport(el))
        callback();
}

function compareSearchResults(a,b) {
    if (a.score < b.score)
        return 1;
    if (a.score > b.score)
        return -1;
    return 0;
}

function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
        s4() + '-' + s4() + s4() + s4();
}