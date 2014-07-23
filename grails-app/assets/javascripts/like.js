/**
 * Created by root on 7/5/14.
 */

var rateObject = {
    urlRate : likeUrl,
    rate : function(obj) {
        obj.click(function(e) {
            var thisObj = $(this);
            var thisType = thisObj.hasClass('rateUp') ? 'LIKE' : 'DISLIKE';
            var thisItem = thisObj.attr('data-item');
            jQuery.getJSON(rateObject.urlRate, { type : thisType, item : thisItem }, function(data) {
                if (!data.error) {
                    thisObj.parent('.rateWrapper').find('.rateUpN').html(data.likes);
                    thisObj.parent('.rateWrapper').find('.rateDownN').html(data.dislikes);
                    thisObj.parent('.rateWrapper').find('.active').removeClass('active')
                    if(data.type == 'LIKE')
                        thisObj.parent('.rateWrapper').find('.rateUp').addClass('active');
                    if(data.type == 'DISLIKE')
                        thisObj.parent('.rateWrapper').find('.rateDown').addClass('active');
                }
            });
            e.preventDefault();
        });
    }
};
$(function() {

    jQuery.ajaxSetup({ cache:false });
    rateObject.rate($('.rate'));

});