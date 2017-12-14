$(function () {

    let isSumCorrect = false,
        isDurationCorrect = false;

    if ($('#post').length > 0) {
        $('#date').attr('min', new Date().toISOString().substring(0, 10));
    }

    $('#sum').on('keyup', function () {

        isSumCorrect = $('#sum').val() > 0;
        validateInput(isSumCorrect, isDurationCorrect);
    });

    $('#duration').on('keyup', function () {

        const duration = $('#duration').val();

        isDurationCorrect = duration > 5 && duration <= 900 && !duration.includes('.');
        validateInput(isSumCorrect, isDurationCorrect);
    });

    $('#sum').trigger('keyup');
    $('#duration').trigger('keyup');

    function validateInput(isSumCorrect, isDurationCorrect) {

        if (isSumCorrect && isDurationCorrect) {
            $('#submit').removeAttr('disabled');
        } else {
            $('#submit').prop('disabled', 'disabled');
        }
    }
});