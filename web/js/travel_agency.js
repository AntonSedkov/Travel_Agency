/*
$('#loginModal').modal({
    keyboard: false,
    backdrop: "static"
})*/

(function ($) {
    "use strict";
    /*
        var nc_select = $('.nc_select');
        if (nc_select.length) {
            nc_select.niceSelect();
        }

        $('#datepicker_1').datepicker();
        $('#datepicker_2').datepicker();
        $('#datepicker_3').datepicker();*/

    document.addEventListener('keydown', (event) => {
        if (event.keyCode === 116) event.preventDefault();
    })

}(jQuery));