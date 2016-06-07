
$(function() {
    //# поиск по id
    dialog = $( "#registrationDialog" ).dialog({
        autoOpen: false,
        height: 550,
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

    $("form").submit(function(e){
        e.preventDefault();
    });

    });

function AdminsRight() {

    window.location.href="administrationNotSleeps";
}

function showSignUp() {
    $("#signup").show();
    $("#loginForm").hide();

    $("li_signup").addClass("active");
    $("li_login").removeClass("active");

}

function showLogin() {
    $("#signup").hide();
    $("#loginForm").show();

    $("#li_login").addClass("active");
    $("#li_signup").removeClass("active");

}