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

            var lastHashTagNode = $(ed.selection.getNode());
            var link = $('#tagSearchResultsTab').find('.k-content.k-state-active a.k-state-active');
            lastHashTagNode.text('#' + link.attr('data-tag'));
            lastHashTagNode.attr('href', link.attr('data-link'));
            $('#tagSearchResults').hide();

            var currentPosition = getCursorPosition(ed);
            var content = ed.getContent();
            while (content[++currentPosition] != '>');
            setCursorPosition(ed, currentPosition + 1);
            var character = e.key;
            if (e.key == ' ')
                character = '&nbsp;';
            if (e.key == 'Enter')
                character = ' ';
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

function insertHashTagLink(ed) {
    var id = guid();

    var cursorPosition = getCursorPosition(ed);
    var cursorPositionHistory = cursorPosition;
    var content = ed.getContent();
    var phrase = '';
    while (cursorPosition < content.length
    && content[cursorPosition] != ' '
    && content[cursorPosition] != '.'
    && content[cursorPosition] != ','
    && content[cursorPosition] != ';'
    && content[cursorPosition] != '،'
    && content[cursorPosition] != '؛'
    && content[cursorPosition] != '>'
    && content[cursorPosition] != '<')
        phrase += content[cursorPosition++];
    ed.execCommand('mceInsertContent', false, id);
    replaceHashTag('all', ed, '#' + id + phrase, '<a href="#">#' + phrase + '</a>');
    setCursorPosition(ed, getCursorPosition(ed) - 4);
    //content = ed.getContent();
    //while (content[++cursorPositionHistory] != '#');
    //setCursorPosition(ed, getCursorPosition(ed) - 4);
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
    var node = $(tinyMCE.activeEditor.selection.getNode());
    node.text('#' + label);
    node.attr('href', link);
    $('#tagSearchResults').hide();
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
    var cursorPosition = getCurrentCursorPositionOfEditor(ed);
    $('#tagSearchResults').css('top', cursorPosition.top + 50).css('left', cursorPosition.left - 400).show();
}

function getCurrentCursorPositionOfEditor(editor) {
    var tinymcePosition = $(editor.getContainer()).position();
    var toolbarPosition = $(editor.getContainer()).find(".mce-toolbar").first();
    var nodePosition = $(editor.selection.getNode()).position();
    var textareaTop = 0;
    var textareaLeft = 0;
    if (editor.selection.getRng().getClientRects().length > 0) {
        textareaTop = editor.selection.getRng().getClientRects()[0].top + editor.selection.getRng().getClientRects()[0].height;
        textareaLeft = editor.selection.getRng().getClientRects()[0].left;
    } else {
        textareaTop = parseInt($(editor.selection.getNode()).css("font-size")) * 1.3 + nodePosition.top;
        textareaLeft = nodePosition.left;
    }
    var position = $(editor.getContainer()).offset();
    return {
        top: tinymcePosition.top + toolbarPosition.innerHeight() + textareaTop,
        left: tinymcePosition.left + textareaLeft// + position.left
    }
}

function replaceHashTag(a, ed, find, repl) {
    var se = ed.selection;
    var r = se.getRng();
    var f;
    var m = this.lastMode;
    var fl = 0;
    var w = ed.getWin();
    var wm = ed.windowManager;
    var fo = 0;
    var err = 0;

    // Get input
    var s = find;
    var b = 0; // direction of the search (forward=0, backward = 1)
    var ca = 0; // casesensitiviy of the search (not casesensitive = 0, casesensitive = 1)
    var rs = repl; // possibility to use a string to replace found text

    if (tinymce.isIE) {
        r = ed.getDoc().selection.createRange();
    }

    if (s == '')
        return;

    function fix() {
        // Correct Firefox graphics glitches
        // TODO: Verify if this is actually needed any more, maybe it was for very old FF versions?
        r = se.getRng().cloneRange();
        ed.getDoc().execCommand('SelectAll', false, null);
        se.setRng(r);
    }

    function replace() {
        ed.selection.setContent(rs); // Needs to be duplicated due to selection bug in IE
    }

    // IE flags
    if (ca)
        fl = fl | 4;

    switch (a) {
        case 'all':
            // Move caret to beginning of text
            ed.execCommand('SelectAll');
            ed.selection.collapse(true);

            if (tinymce.isIE) {
                ed.focus();
                r = ed.getDoc().selection.createRange();

                while (r.findText(s, b ? 1 : 1, fl)) {
                    r.scrollIntoView();
                    r.select();
                    replace();
                    fo = 1;

                    if (b) {
                        r.moveEnd("character", -(rs.length)); // Otherwise will loop forever
                    }
                }
            } else {
                while (w.find(s, ca, b ? 0 : 0, false, false, false, false)) {
                    replace();
                    fo = 1;
                }
            }

            return;

        case 'current':
            if (!ed.selection.isCollapsed())
                replace();

            break;
    }

    se.collapse(b);
    r = se.getRng();

    // Whats the point
    if (!s)
        return;

    if (tinymce.isIE) {
        ed.focus();
        r = ed.getDoc().selection.createRange();

        if (r.findText(s, b ? -1 : 1, fl)) {
            r.scrollIntoView();
            r.select();
        } else {
            err = 1;
        }
    } else {
        if (!w.find(s, ca, b, false, false, false, false)) {
            err = 1;
        }
        else
            fix();
    }

    // Focus input field if search yieled no error
    if (!err && !tinymce.isIE) {
        f[m + '_panel_searchstring'].focus();
    }
}

function getCursorPosition(editor) {
    //set a bookmark so we can return to the current position after we reset the content later
    var bm = editor.selection.getBookmark(0);

    //select the bookmark element
    var selector = "[data-mce-type=bookmark]";
    var bmElements = editor.dom.select(selector);

    //put the cursor in front of that element
    editor.selection.select(bmElements[0]);
    editor.selection.collapse();

    //add in my special span to get the index...
    //we won't be able to use the bookmark element for this because each browser will put id and class attributes in different orders.
    var elementID = "######cursor######";
    var positionString = '<span id="' + elementID + '"></span>';
    editor.selection.setContent(positionString);

    //get the content with the special span but without the bookmark meta tag
    var content = editor.getContent({format: "html"});
    //find the index of the span we placed earlier
    var index = content.indexOf(positionString);

    //remove my special span from the content
    editor.dom.remove(elementID, false);

    //move back to the bookmark
    editor.selection.moveToBookmark(bm);

    return index;
}

function setCursorPosition(editor, index) {
    //get the content in the editor before we add the bookmark...
    //use the format: html to strip out any existing meta tags
    var content = editor.getContent({format: "html"});

    //split the content at the given index
    var part1 = content.substr(0, index);
    var part2 = content.substr(index);

    //create a bookmark... bookmark is an object with the id of the bookmark
    var bookmark = editor.selection.getBookmark(0);

    //this is a meta span tag that looks like the one the bookmark added... just make sure the ID is the same
    var positionString = '<span id="' + bookmark.id + '_start" data-mce-type="bookmark" data-mce-style="overflow:hidden;line-height:0px"></span>';
    //cram the position string inbetween the two parts of the content we got earlier
    var contentWithString = part1 + positionString + part2;

    //replace the content of the editor with the content with the special span
    //use format: raw so that the bookmark meta tag will remain in the content
    editor.setContent(contentWithString, ({format: "raw"}));

    //move the cursor back to the bookmark
    //this will also strip out the bookmark metatag from the html
    editor.selection.moveToBookmark(bookmark);

    //return the bookmark just because
    return bookmark;
}