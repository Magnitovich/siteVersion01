
$(function() {

    $("#SignUpBtn").click(function(event) {
        //отмена привычных действий кнопки ссылки input(a)
        event.preventDefault();
        submitSignupShowError();
    });

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

function submitSignupShowError() {

    var email = document.getElementById("Email");
    var nickName = $("#NickName");
    var password = $("#signUpPassword");

    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: {NickName:nickName.val(), signUpPassword: password.val(), Email:email.value},
        url: '/registrationPage',
        success: function(msg){  //msg - показывает ответ с сервера

            window.location.href = "index?successful";
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var errorMessage = document.getElementById("emailError");
            var errorMessage = document.getElementById("nickError");
            //#errors это означ что мы обращаемся к нашему getElementById("errors")

            $("#emailError").show();
            $("#nickError").show();
        }
    });




}