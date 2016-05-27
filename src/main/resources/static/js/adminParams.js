function adminRights() {

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
            window.location.href = window.location.href;
        }
    });
}
//
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