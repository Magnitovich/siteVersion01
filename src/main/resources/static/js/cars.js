
function addNewCar() {

    window.location.href="addInfoAboutNewCar";
}

function editCars() {

    var checkboxChecked = $('input:checked') //определение активированных checkbox
    if(checkboxChecked.length>1)
    {alert("You selected more one car for the edit!")

    } else if (checkboxChecked.length==0) {
        alert("Nothing selected")

    } else {
        window.location.href="addInfoAboutNewCar?id="+checkboxChecked[0].id;
    }
}
function deleteCars() {

    var arrayCarsFromCheckbox = [];
    var i = 0;
    var checkboxChecked = $('input:checked')

    checkboxChecked.each(function(){
        var checkbox = $(this);

        arrayCarsFromCheckbox[i] = checkbox[0].id;
        i++;
    });
    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: JSON.stringify(arrayCarsFromCheckbox), //грубо говоря это Серриализация
        url: '/deleteCars/DELETE',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = window.location.href;
        }
    });



}