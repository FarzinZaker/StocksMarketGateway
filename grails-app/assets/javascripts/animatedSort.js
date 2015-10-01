/**
 * Created by farzin on 9/30/2015.
 */
$.fn.animatedSort = function () {

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

    items.css('position', 'absolute').width(container.width() - 20);
    var sortedList = [];
    $.each(items, function () {
        sortedList[sortedList.length] = $(this);
    });
    sortedList = animatedSort_insertionSort(sortedList).reverse();
    var currentTop = 0;
    var indexer = 0;
    $.each(sortedList, function () {
        if (indexer++ < 10) {
            this.stop().animate({
                top: currentTop,
                opacity: 1
            }, 700);
            currentTop += this.height() + 21;
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
        if (indexer++ < 10)
            totalHeight += this.height() + 13;

        if (indexer < 10) {
            this.removeClass('noBorder');
        }
        else {
            this.addClass('noBorder');
        }
    });
    container.stop().animate({
        height: totalHeight + 80
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