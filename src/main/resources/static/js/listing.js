$(function () {

    $('.remover').on('click', function () {
        const remover = $(this);

        $.ajax({
            url: '/loan/requests/' + remover.val(),
            type: 'DELETE',

            success: function(data) {
                $('#par' + remover.val()).remove();

                if ($('.request-par').length === 0) {
                    $('#empty-bd-par').removeAttr('hidden');
                }
            }
        });
    });

    $('.schedule').on('click', function () {
        window.location.href='/loan/schedule/' + $(this).val();
    });

    $('.edit').on('click', function () {
        window.location.href='/loan/requests/' + $(this).val();
    })
});