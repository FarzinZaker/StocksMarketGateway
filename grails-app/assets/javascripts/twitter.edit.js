/**
 * Created by Farzin on 3/1/2016.
 */
function editArticle(id, editButton) {
    window.location.href = "/article/edit/" + id;
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