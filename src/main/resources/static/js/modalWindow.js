
$(function() {

    $("#SignUpBtn").click(function(event) {
        //отмена привычных действий кнопки ссылки input(a)
        event.preventDefault();
        if ($("#signupSubmitFrm").valid()) {
            submitSignupShowError();
        }
    });

    //# поиск по id
    dialog = $( "#registrationDialog" ).dialog({
        autoOpen: false,
        height: 'auto',
        width: 'auto',
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



function AdminsRight() {

    window.location.href="administrationNotSleeps";
}

function showSignUp() {
    $("#signup").show();
    $("#loginForm").hide();

    $("#li_signup").addClass("active");
    $("#li_login").removeClass("active");

}

function showLogin() {
    $("#signup").hide();
    $("#loginForm").show();

    $("#li_login").addClass("active");
    $("#li_signup").removeClass("active");

}

function submitSignupShowError() {

    var email = document.getElementById("Email");
    var nickName = $("#NickName");
    var password = $("#signUpPassword");

    $("#emailError").hide();
    $("#nickError").hide();

    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        url: '/registrationPage?' + $("#signupSubmitFrm").serialize(),
        success: function(msg){  //msg - показывает ответ с сервера

            window.location.href = "index?successful";
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var errorMessage = document.getElementById("emailError");
            var errorMessage = document.getElementById("nickError");
            //#errors это означ что мы обращаемся к нашему getElementById("errors")
            if (xhr.responseJSON.message === 'Email exists') {
                $("#emailError").show();
                $("#nickError").hide();
            }
            if (xhr.responseJSON.message === 'NickName exists') {
                $("#emailError").hide();
                $("#nickError").show();
            }

            console.log(xhr.responseJSON.message);
        }
    });
}