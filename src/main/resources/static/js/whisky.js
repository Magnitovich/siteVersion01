
function cartOpen() {
    window.location.href = "allCart";
}

function deleteSelectedElements() {
    var nameWhisky = []; //новый пустой массив
    var i = 0;
    var listCheckdYahcts = $('input:checked'); //переменная = всем нажатым checkbox (это массив)
    // JavaScript не имеет типов var может быть массивом, строкой, функцией....
    listCheckdYahcts.each(function() { // для каждого эл. массива вызовем ф-цию это наш for()
        var checkbox = $(this); // this глобальная переменная, котор меняет свое знач. в зависимости от того где используется.

        nameWhisky[i] = checkbox[0].id;  // эта строчка находит этот код th:attr="id=${viewAvailableWhisky.nameWhisky}
        i++; //гуляем по listCheckdYahcts.each(function()
    });
    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: JSON.stringify(nameWhisky), //грубо говоря это Серриализация
        url: '/deleteWhisky',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = window.location.href;
        }
    });
}

function editInfoWhisky() {

    var listCheckdWhisky = $('input:checked'); //переменная = всем нажатым checkbox (это массив)
    if (listCheckdWhisky.length > 1) {
        alert("WOW")
    }
    else if (listCheckdWhisky.length==0) {

        alert("Nothing check");

    } else {

      window.location.href = "addNewWhisky?id=" + listCheckdWhisky[0].id;        }

}
//function buyFromJS() {
    //
    // $.getJSON("buySuccessfulWhisky",
    //     {
    //         nameSelectedWhisky:$('#nameWhiskey').val(),
    //         quantitySelectedWhisky:$('#numberOrderWhisky').val()
    //     },
    //     function() {
    //         window.location.href = "warehouseWhisky";
    //         //window.location.href=$('#warehouseWhisky');
    //     }   );
    //}
//}
function modalityWindowNew(){

    dialog = $('#go').dialog({
        autoOpen: false,
        //height: 'auto',
        //width: 'auto',
        modal: true,
        dialogClass: 'no-close success-dialog',
        //target: $('#go'),
        //toggle: $('modal')
    });
    //openModality.open();
    $('#buttonBuyWhiskey').button().on( "click", function() {
        dialog.dialog("open");
    });
    sendBuyWhiskyInJava();
}
//var cartWhisky = [];
//var itemCart = function(photo, name, describe, quantity, price){
//    this.photo=photo;
//    this.name=name;
//    this.describe=describe;
//    this.quantity=quantity;
//    this.price=price;
//};
//
//function buyFromJS() {
//    //var newWin = $('#go').open();
//    //newWin.document.open();
//    //newWin.document.writeln('Динамический контент.');
//    //newWin.document.write('<div>Как-то так.</div>');
//    //newWin.document.close();
//
//
//    var photo = $("#photoId").val();
//    var name = $("#nameWhiskey").val();
//    var describe = $("#describeID").val();
//    var quantity= Number($("#numberOrderWhisky").val());
//    var quantityInDB= Number($("#quantityWhiskeyInDB").val());
//    var price = $("#priceID").val();
// //console.log("price: "+price);
// //console.log("quantity: "+quantity);
// //console.log("quantityDB: "+quantityInDB);
//
//    if (quantity<=quantityInDB) {
//        loadCart();
//        console.log(cartWhisky)
//    var item = new itemCart(photo, name, describe, quantity, price);
//    cartWhisky.push(item);
//    saveCart();
//    sendBuyWhiskyInJava();
//    }
//    else if (quantity>quantityInDB && quantity<0) {
//    clearCart()
//    }
//}
////clear
//function clearCart() {
//    cartWhisky = [];
//    saveCart();
//}
////SaveCart()
//function saveCart(){
//    //JSON.parse – читает объекты из строки в формате JSON.
//    //JSON.stringify – превращает объекты в строку в формате JSON, используется, когда нужно из JavaScript передать данные по сети.
//
//    localStorage.setItem("shoppingCart", JSON.stringify(cartWhisky));
//}
//
////loadCart()
//function loadCart(){
//    cartWhisky = JSON.parse(localStorage.getItem("shoppingCart"));
//}
//
//
//
//  function sendBuyWhiskyInJava(){
//        $('#buySelectedWhiskyThroughJavaScript').submit(function(e){
//            e.preventDefault();
//
//            $.ajax({   //тип запроса
//                type: "POST", //это типа method
//                url: '/buySuccessfulWhisky',
//                data: {
//                    nameSelectedWhisky: $("#nameWhiskey").val(),
//                quantitySelectedWhisky: $("#numberOrderWhisky").val()
//        },
//                success: function(data) {  //msg - показывает ответ с сервера
//                    window.location.href = "warehouseWhisky";
//                    console.log(data)
//                }
//        })
//    })
//}
//




function imageClick() {

    var imageId = $("#photoId");
    var hiddenId = $("#idNameWhisky");
    alert(hiddenId.getPropertyValue);

    window.location.href = "buySelectedWhisky?id=" + hiddenId[0].id;

}

function addNewWhisky() {

    window.location.href="addNewWhisky";

}

function selectedYachtForBuy() {

    var nameYacht = $("#nameBuyWhiskey");

    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: JSON.stringify(nameYacht), //грубо говоря это Серриализация
        url: '/buySelectedWhisky',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href ="buySelectedWhisky";
        }
    });

}