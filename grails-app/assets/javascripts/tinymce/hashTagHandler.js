/**
 * Created by Farzin on 11/8/2015.
 */


function onKeyUpForHashTag(ed, e) {

    if (e.key == 'Escape') {
        $('#tagSearchResults').hide();
    }
    else if (isInHashTag(ed)) {
        var phrase = getHashTagSearchPhrase(ed);
        if (e.key == ' ') {
            if (phrase != null) {

            }
        }
        else {
            doHashTagSearch(ed, phrase);
        }
    } else {
        $('#tagSearchResults').hide();
    }
}

function onKeyDownForHashTag(ed, e) {

    if (isInHashTag(ed)) {
        if (e.key == '#') {
            e.preventDefault();
            e.stopPropagation();
        }
        else if ((e.key == ' '
            || e.key == 'Enter'
            || e.key == '.'
            || e.key == ','
            || e.key == ';'
            || e.key == '،'
            || e.key == '؛')) {

            e.preventDefault();
            e.stopPropagation();

            var container = $('#tagSearchResults');
            if (container.is(':visible')) {
                var lastHashTagNode = $(ed.selection.getNode());
                var link = $('#tagSearchResultsTab').find('.k-content.k-state-active a.k-state-active');
                lastHashTagNode.text('#' + link.attr('data-tag'));
                lastHashTagNode.attr('href', link.attr('data-link'));
                container.hide();
            }

            var currentPosition = getCursorPosition(ed);
            var content = ed.getContent();
            while (content[++currentPosition] != '>');
            setCursorPosition(ed, currentPosition + 1);
            var character = e.key;
            if (e.key == ' ')
                character = '&nbsp;';
            if (e.key == 'Enter')
                character = '&nbsp;';
            ed.execCommand('mceInsertContent', false, character);
        }
        else if (e.key == 'ArrowDown') {

            e.preventDefault();
            e.stopPropagation();

            var resultsTab = $('#tagSearchResultsTab').find('.k-content.k-state-active');
            var link = resultsTab.find('a.k-state-active').next('#tagSearchResultsTab .k-content.k-state-active a');
            if (link.length) {
                resultsTab.find('a').removeClass('k-state-active');
                link.addClass('k-state-active');
                resultsTab.scrollTop(resultsTab.scrollTop() + link.position().top - resultsTab.height() / 2 + link.height() / 2);
            }
        }
        else if (e.key == 'ArrowUp') {

            e.preventDefault();
            e.stopPropagation();

            var resultsTab = $('#tagSearchResultsTab').find('.k-content.k-state-active');
            var link = resultsTab.find('a.k-state-active').prev('#tagSearchResultsTab .k-content.k-state-active a');
            if (link.length) {
                resultsTab.find('a').removeClass('k-state-active');
                link.addClass('k-state-active');
                resultsTab.scrollTop(resultsTab.scrollTop() + link.position().top - resultsTab.height() / 2 + link.height() / 2);
            }

        }
        else if (e.key == 'Tab') {

            e.preventDefault();
            e.stopPropagation();

            var tabStrip = $("#tagSearchResultsTab").data("kendoTabStrip");
            var tabsCount = $('#tagSearchResults').find('.k-tabstrip-items li').length;
            var currentIndex = tabStrip.select().index();
            if (e.shiftKey) {
                currentIndex = (currentIndex - 1 + tabsCount) % tabsCount;
            }
            else {
                currentIndex = (currentIndex + 1) % tabsCount;
            }
            tabStrip.select(currentIndex);
            return false;
        }
        return false;
    }
    else if (e.key == '#') {
        e.preventDefault();
        e.stopPropagation();

        ed.execCommand('mceInsertContent', false, '<a href="#" class="hashTag">#</a>');
        setCursorPosition(ed, getCursorPosition(ed) - 4);

        return false;
    }
}

function onClickForHashTag(ed, e) {
    if (isInHashTag(ed)) {
        var phrase = getHashTagSearchPhrase(ed);
        doHashTagSearch(ed, phrase);
    }
}

function onBlurForHashTag(ed, e) {
    //$('#tagSearchResults').hide();
}

function doHashTagSearch(ed, phrase) {
    if (phrase != null) {
        showHashTagSearchBox(ed);
        showTagSearchResults(ed, phrase);
    }
    else {
        $('#tagSearchResults').hide();
    }
}

function insertHashTag(label, link) {
    var container = $('#tagSearchResults');
    if (!container.is(":visible"))
        return;
    var node = $(tinyMCE.activeEditor.selection.getNode());
    node.text('#' + label);
    node.attr('href', link);
    container.hide();
}

function isInHashTag(ed) {
    return $(ed.selection.getNode()).text().startsWith('#');
}

function getHashTagSearchPhrase(ed) {
    var cursorPosition = getCursorPosition(ed);
    var content = ed.getContent();
    var hashTagPosition = content.substr(0, cursorPosition).lastIndexOf('#');
    if (hashTagPosition == -1)
        return null;
    var phrase = content.substring(hashTagPosition + 1, cursorPosition);
    if (phrase.indexOf(' ') != -1
        || phrase.indexOf('.') != -1
        || phrase.indexOf(',') != -1
        || phrase.indexOf(';') != -1
        || phrase.indexOf('،') != -1
        || phrase.indexOf('؛') != -1
        || phrase.indexOf('<') != -1
        || phrase.indexOf('>') != -1)
        return null;
    return phrase;
}

function showHashTagSearchBox(ed) {
    $('#authorSearchResults').hide();
    var cursorPosition = getCurrentCursorPositionOfEditor(ed);
    $('#tagSearchResults').css('top', cursorPosition.top + 50).css('left', cursorPosition.left - 400).show();
}