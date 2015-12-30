/**
 * Created by Farzin on 12/27/2015.
 */
function showTwitterCommentEditor(sender, container, loading, url) {
    $('.replyButtonContainer').show();
    sender.parent().fadeOut(200, function () {
        loading.fadeIn(200, function () {
            $('.replyEditor').hide();
            if (container.html()) {
                loading.fadeOut(200, function () {
                    container.show().slideDown();
                });
            }
            else {
                $.ajax({
                    type: "POST",
                    url: url
                }).done(function (response) {
                    loading.fadeOut(200, function () {
                        container.hide().html(response).show().slideDown();
                    });
                });
            }
        })
    });
}

function cancelTwitterComment(formId) {
    $('#' + formId).parent().parent().parent().slideUp().parent().find('.replyButtonContainer').slideDown();
    ;
}