function adminRights() {

    $('span[id^="username_"]').hide();

    var selectedCheckBoxForUpdate = [];
    var i = 0;

    var arrayCheckboxClicked = $('input:checked')

    arrayCheckboxClicked.each(function(){
        var hasNextt = $(this);

        selectedCheckBoxForUpdate[i] = hasNextt[0].id;
        i++;
    });
    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: JSON.stringify(selectedCheckBoxForUpdate), //грубо говоря это Серриализация
        url: '/okYouDoIt',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = "index";
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var errorMessage = document.getElementById("errors");
            //#errors это означ что мы обращаемся к нашему getElementById("errors")
            $("#errors").text("ERRORRRRRRR!!!!");
            $("#errors").show();
            if (xhr.responseJSON.status === 417) {
                var userNameArray = xhr.responseJSON.message.split(",");
                userNameArray.forEach(function(userName) {
                    $("#username_" + userName).show();
                });
            }
        }
    });
}
