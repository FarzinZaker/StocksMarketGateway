/**
 * Created by Farzin on 3/1/2016.
 */
function editArticle(id, editButton) {
    window.location.href = "/article/edit/" + id;
}

function deleteArticle(id, deleteButton) {
    articleIdForDelete = id;
    articleDeleteButton = deleteButton;
    articleDeleteRetryCount = 0;
    confirm('آیا از حذف این مقاله اطمینان دارید؟', doArticleDelete, cancelArticleDelete);
}

var articleIdForDelete = 0;
var articleDeleteButton = null;
var articleDeleteRetryCount = 0;
function doArticleDelete() {
    if(articleIdForDelete > 0 ) {
        $.ajax({
            type: "POST",
            url: "/article/delete/" + articleIdForDelete
        }).done(function (response) {
            if(response == "1") {
                var li = $(articleDeleteButton);
                while (li.prop("tagName").toLowerCase() != 'li') {
                    li = li.parent();
                }
                li.slideUp(400, function () {
                    $(this).remove()
                });
                cancelArticleDelete();
            }
            else if(articleDeleteRetryCount++ < 3){
                doArticleDelete();
            }
            else {
                cancelArticleDelete();
            }
        });
    }
}

function cancelArticleDelete(){
    articleIdForDelete = 0;
    articleDeleteButton = null;
    articleDeleteRetryCount = 0;
}

function editTalk(id, editButton) {
    cancelTalkInlineEdit();
    cancelCommentInlineEdit();
    $(editButton).stop().slideUp();
    var container = $(editButton).parent().parent().parent();
    var bodyContainer = container.find('.description').first().show();
    var editorContainer = container.find('.descriptionEditor').first().hide();
    var loading = container.find('.loading').first().hide();

    bodyContainer.slideUp(200, function () {
        loading.show().slideDown(200, function () {
            $.ajax({
                type: "POST",
                url: "/twitter/inlineEditor/" + id + "?type=Talk"
            }).done(function (response) {
                loading.slideUp(200, function () {
                    editorContainer.html(response).show().slideDown();
                });
            });
        })
    });
}

function deleteTalk(id, deleteButton) {
    talkIdForDelete = id;
    talkDeleteButton = deleteButton;
    talkDeleteRetryCount = 0;
    confirm('آیا از حذف این مطلب اطمینان دارید؟', doTalkDelete, cancelTalkDelete);
}

var talkIdForDelete = null;
var talkDeleteButton = null;
var talkDeleteRetryCount = 0;
function doTalkDelete() {
    if(talkIdForDelete) {
        $.ajax({
            type: "POST",
            url: "/talk/delete/" + talkIdForDelete
        }).done(function (response) {
            if(response == "1") {
                var li = $(talkDeleteButton);
                while (li.prop("tagName").toLowerCase() != 'li') {
                    li = li.parent();
                }
                li.slideUp(400, function () {
                    $(this).remove()
                });
                cancelTalkDelete();
            }
            else if(talkDeleteRetryCount++ < 3){
                doTalkDelete();
            }
            else {
                cancelTalkDelete();
            }
        });
    }
}

function cancelTalkDelete(){
    talkIdForDelete = null;
    talkDeleteButton = null;
    talkDeleteRetryCount = 0;
}


function editComment(id, editButton) {

    cancelTalkInlineEdit();
    cancelCommentInlineEdit();
    $(editButton).stop().slideUp();
    var container = $(editButton).parent().parent().parent().parent();
    var imageContainer = container.find('.image').first().hide();
    var bodyContainer = container.find('.description .description').first().show();
    var editorContainer = container.find('.description .descriptionEditor').first().hide();
    var loading = container.find('.loading').first().hide();

    bodyContainer.slideUp(200, function () {
        loading.show().slideDown(200, function () {
            $.ajax({
                type: "POST",
                url: "/twitter/inlineEditor/" + id + "?type=Comment"
            }).done(function (response) {
                loading.slideUp(200, function () {
                    editorContainer.html(response).show().slideDown();
                });
            });
        })
    });
}

function deleteComment(id, deleteButton) {
    commentIdForDelete = id;
    commentDeleteButton = deleteButton;
    commentDeleteRetryCount = 0;
    confirm('آیا از حذف این نظر اطمینان دارید؟', doCommentDelete, cancelCommentDelete);
}

var commentIdForDelete = null;
var commentDeleteButton = null;
var commentDeleteRetryCount = 0;
function doCommentDelete() {
    if(commentIdForDelete) {
        $.ajax({
            type: "POST",
            url: "/comment/delete/" + commentIdForDelete
        }).done(function (response) {
            if(response == "1") {
                var li = $(commentDeleteButton);
                while (li.prop("tagName").toLowerCase() != 'li') {
                    li = li.parent();
                }
                li.slideUp(400, function () {
                    $(this).remove()
                });
                cancelCommentDelete();
            }
            else if(commentDeleteRetryCount++ < 3){
                doCommentDelete();
            }
            else {
                cancelCommentDelete();
            }
        });
    }
}

function cancelCommentDelete(){
    commentIdForDelete = null;
    commentDeleteButton = null;
    commentDeleteRetryCount = 0;
}

function cancelTalkInlineEdit() {
    $('.replyList .image').slideDown();
    $('.description .author .k-button').slideDown();
    $('.description .description').slideDown();
    $('.description .descriptionEditor').slideUp();
    $('.description .loading').slideUp();
}

function cancelCommentInlineEdit() {
    $('.replyList .image').slideDown();
    $('.description .author .k-button').slideDown();
    $('.description .description').slideDown();
    $('.description .descriptionEditor').slideUp();
    $('.description .loading').slideUp();
}