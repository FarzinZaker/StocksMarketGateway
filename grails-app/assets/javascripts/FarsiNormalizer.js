/**
 * Created by farzin on 27/05/2015.
 */
function normalizeFarsi(input) {
    return normalizeInternal(input, input.length);
}

function normalizeInternal(input, len) {
    var s = input.toString().split('');
    for (var i = 0; i < len; i++) {
        switch (input.charCodeAt(i)) {
            case 1740:
                s[i] = '\u064A';
                break;
            case 1746:
                s[i] = '\u064A';
                break;

            case 1705:
                s[i] = '\u0643';
                break;

            case 1728:
                s[i] = '\u0647';
                break;
            case 1729:
                s[i] = '\u0647';
                break;

            case 1620:
                len = del(s, i, len);
                i--;
                break;
        }
    }
    return s.join('');
}


function del(s, pos, len) {
    if (pos < len)
        s = s.splice(pos, 1);
    return len - 1;
}