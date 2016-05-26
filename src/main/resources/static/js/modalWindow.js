
$(function() {
    //# поиск по id
    dialog = $( "#registrationDialog" ).dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        dialogClass: 'no-close success-dialog',
        buttons: {
            Cancel: function() {
                dialog.dialog( "close" );
            }
        }
    });

    $( "#login_register" ).button().on( "click", function() {
        dialog.dialog( "open" );
    });

    });

