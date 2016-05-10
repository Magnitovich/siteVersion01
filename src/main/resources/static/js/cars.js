
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
