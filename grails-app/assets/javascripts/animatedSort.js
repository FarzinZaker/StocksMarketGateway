/**
 * Created by farzin on 9/30/2015.
 */
$.fn.animatedSort = function (config) {

    if (!config)
        config = {
            margin: 21,
            padding: 10,
            addedHeight: 0,
            count: 5
        };
    if (!config.margin && config.margin != 0)
        config.margin = 21;
    if (!config.padding && config.padding != 0)
        config.padding = 10;
    if (!config.addedHeight && config.addedHeight != 0)
        config.addedHeight = 0;
    if (!config.count && config.count != 0)
        config.count = 5;
    var container = this;

    if (!container.is(":visible"))
        return;

    var items = this.children();
    //var totalHeight = 0;
    //$.each(items, function () {
    //    totalHeight += $(this).height() + 13;
    //});
    //if (true)
    //    container.css('height', 100);
    //container.stop().animate({
    //    height: totalHeight + 45
    //}, 1000);

    items.css('position', 'absolute').width(container.width() - (2 * config.padding));
    var sortedList = [];
    $.each(items, function () {
        sortedList[sortedList.length] = $(this);
    });
    sortedList = animatedSort_insertionSort(sortedList).reverse();
    var currentTop = 0;
    var indexer = 0;
    $.each(sortedList, function () {
        if (indexer++ < config.count) {
            this.stop().animate({
                top: currentTop,
                opacity: 1
            }, 700);
            currentTop += this.height() + config.margin;
            if(indexer % 2 != 0){
                this.addClass('even').removeClass('odd');
            }
            else{
                this.addClass('odd').removeClass('even');
            }
        }
        else {
            this.remove();
        }
    });

    var totalHeight = 0;
    indexer = 0;
    $.each(sortedList, function () {
        if (indexer++ < config.count)
            totalHeight += this.height() + config.margin;

        if (indexer < config.count && indexer < sortedList.length) {
            this.removeClass('noBorder');
        }
        else {
            this.addClass('noBorder');
        }
    });
    container.stop().animate({
        height: totalHeight + config.addedHeight
    }, 1000);

    return this;
};

function animatedSort_insertionSort(unsortedList) {
    var len = unsortedList.length;

    for (var i = 0; i < len; i++) {
        var tmp = unsortedList[i]; //Copy of the current element.
        /*Check through the sorted part and compare with the
         number in tmp. If large, shift the number*/
        for (var j = i - 1; j >= 0 && (animatedSort_greaterThan(unsortedList[j], tmp)); j--) {
            //Shift the number
            unsortedList[j + 1] = unsortedList[j];
        }
        //Insert the copied number at the correct position
        //in sorted part.
        unsortedList[j + 1] = tmp;
    }
    return unsortedList;
}

function animatedSort_greaterThan(item1, item2) {
    return parseInt(item1.attr('data-time')) > parseInt(item2.attr('data-time'))
}