/**
 * Created by farzin on 08/12/2014.
 */

var oldKendoFormat = kendo.format;
var oldKendoFormatDate = kendo.formatDate;
kendo.format = function (fmt) {
    switch (fmt) {
        case 'yyyy/MM/dd':
            return formatPersianDate.apply(this, Array.prototype.slice.call(arguments, 0));
        default:
            return oldKendoFormat.apply(this, Array.prototype.slice.call(arguments, 0));
    }
};

kendo.formatDate = function (value, fmt, culture) {
    return oldKendoFormatDate.apply(this, Array.prototype.slice.call(arguments, 0));
};

function formatPersianDate(format, value) {
    if (value == '')
        value = new Date();
    if (typeof(value) == 'string')
        value = new Date(value);
    var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
        return (persianDate[0] + "/" + persianDate[1] + "/" + persianDate[2]);
}

function getJalaliDate(value) {
    if (value == '')
        value = new Date();
    if (typeof(value) == 'string')
        value = new Date(value);
    var persianDate = new JalaliDate().gregorianToJalali(value.getFullYear(), (value.getMonth() == 12 ? value.getMonth() : value.getMonth() + 1), value.getDate());
    return (persianDate[0] + "/" + (persianDate[1] < 10 ? '0' : '') + persianDate[1] + "/" + persianDate[2]);
}