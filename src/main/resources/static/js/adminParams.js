function adminRights() {

//var checkboxChecked = $('input:checked') //определение активированных checkbox
//if(checkboxChecked.length>1)
//{alert("You selected more one car for the edit!")
//
//} else if (checkboxChecked.length==0) {
//    alert("Nothing selected")
//
//} else {
//    window.location.href="okYouDoIt?id="+checkboxChecked[0].id;
//}


    var selectedCheckBoxForUpdate = [];
    var i = 0;

    var arrayCheckboxClicked = $('input:checked')

    arrayCheckboxClicked.each(function(){
        var hasNextt = $(this);

        selectedCheckBoxForUpdate[i] = hasNextt[0].id;
        i++;
    });

    //cкрываем все ранее показанные ошибки
    var allInputHide = $('span[id*="_someText"]');
    allInputHide.hide();

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

            //эта ошибка 417 ее и проверяем

            if(xhr.responseJSON.status == 417) {

                var arrayErrors = xhr.responseJSON.message.split(",");

                arrayErrors.forEach(function(userName) {
                    //userName = ${user.name}
                    var nick = $("#"+userName+"_someText");
                    nick.show();

                })

            }

        }
    });
}
