//$(document).ready(function() {

    var cartWhisky = [];

    var itemCart = function (photo, name, describe, quantity, price) {
        this.photo = photo;
        this.name = name;
        this.describe = describe;
        this.quantity = quantity;
        this.price = price;
    };

    function buyFromJS() {

        var photo = $("#photoId").val();
        var name = $("#nameWhiskey").val();
        var describe = $("#describeID").val();
        var quantity = Number($("#numberOrderWhisky").val());
        var quantityInDB = Number($("#quantityWhiskeyInDB").val());
        var price = $("#priceID").val();

        if (quantity <= quantityInDB) {
            loadCart();
            console.log(cartWhisky);
            var item = new itemCart(photo, name, describe, quantity, price);
            cartWhisky.push(item);
            saveCart();
            comeBack(); //возвращаемся на главную страницу без изменения кол-ва виски, т.к. оно в корзине.
            //sendBuyWhiskyInJava();
        }
        else if (quantity > quantityInDB && quantity < 0) {
            clearCart()
        }
    }

//clear
    function clearCart() {
        cartWhisky = [];
        saveCart();
    }

//SaveCart()
    function saveCart() {
        //JSON.parse – читает объекты из строки в формате JSON.
        //JSON.stringify – превращает объекты в строку в формате JSON, используется, когда нужно из JavaScript передать данные по сети.

        localStorage.setItem("shoppingCart", JSON.stringify(cartWhisky));
    }

//loadCart()
    function loadCart() {
        cartWhisky = JSON.parse(localStorage.getItem("shoppingCart"));
    }

    function comeBack() {
        window.location.href = "warehouseWhisky";
    }

    function cartOpen() {
        window.location.href = "allCart";
    }
$(document).ready(function() {
    loadCart();
console.log(cartWhisky);
    //var orderOnCartWhisky = [];
    var cartArray = listCart();
    //var output = "";
    //var cartArrayDIV = listCart();
    //var outputDIV = "";
console.log(cartArray.length);
    var table ='';
    var rows = cartArray.length;
    for(var r in cartArray){
        table +='<tr>';
            table +="<td>"+"<img src='"+cartArray[r].photo+"' width='35' height='80'>"+"</td>";
            table +="<td width='100'>"+cartArray[r].name+"</td>";
            table +='<td width="150">'+cartArray[r].describe+'</td>';
            table +='<td align="center" width="100">'+"<button class='minus' data-name='"+cartArray[r].name+"'>-</button>"
                +" "+cartArray[r].quantity
                +" "+"<button class='plus' data-name='"+cartArray[r].name+"' >+</button>"+'</td>';
            table +='<td width="80" align="center">'+cartArray[r].price+'</td>';
            table +='<td width="20">' +"<button class='deleteItem' data-name='"+cartArray[r].name+"'>X</button>"+'</td>';
        table +='</tr>';
    }
    document.write('<table align="center">'+table+'</table>')
    //    for (var i in cartArrayDIV) {
    //        $("#photoWhisky").html("<img src='"+cartArray[i].photo+"' width='35' height='80'>");
    //        $("#nameWhisky").html(cartArrayDIV[i].name);
    //        $("#describeWhisky").html(cartArrayDIV[i].describe);
    //        $("#quantityWhisky").html(cartArrayDIV[i].quantity);
    //        $("#priceWhisky").html(cartArrayDIV[i].price);
    //        console.log(cartWhisky[i].name+" "+cartArrayDIV[i].describe
    //        +" "+cartArrayDIV[i].quantity
    //        +" "+cartArrayDIV[i].price);
    //        i++;
    //    }
    //        for (var i in cartArray) {
    //        output +="<li>"
    //            +"<img src='"+cartArray[i].photo+"' width='35' height='80'>"+" "
    //            +cartArray[i].name
    //            +"  "+cartArray[i].quantity+" "
    //            +" "+cartArray[i].price
    //            //+" "+"<button class='minus' data-name='"+cartArray[i].name+"'>-</button>"
    //            //+" "+"<button class='plus' data-name='"+cartArray[i].name+"' >+</button>"
    //            //+" "
    //            //+"<button class='deleteItem' data-name='"+cartArray[i].name+"'>X</button>"
    //
    //            +"</li>"
    //    }
    //$("#showCart").html(output);


        //$.ajax({   //тип запроса
        //    headers: {
        //        "Accept": "application/json",
        //        "Content-Type": "application/json"
        //    },
        //    type: "POST", //это типа method
        //    data: JSON.stringify(orderOnCartWhisky), //грубо говоря это Серриализация
        //    url: '/allCart',
        //    success: function (msg) {  //msg - показывает ответ с сервера
        //        window.location.href = window.location.href;
        //    }
        //});
        //window.location.href = "cartStart";

    });

    function sendBuyWhiskyInJava() {
        $('#buySelectedWhiskyThroughJavaScript').submit(function (e) {
            e.preventDefault();

            $.ajax({   //тип запроса
                type: "POST", //это типа method
                url: '/buySuccessfulWhisky',
                data: {
                    nameSelectedWhisky: $("#nameWhiskey").val(),
                    quantitySelectedWhisky: $("#numberOrderWhisky").val()
                },
                success: function (data) {  //msg - показывает ответ с сервера
                    window.location.href = "warehouseWhisky";
                    console.log(data)
                }
            })
        })
    }
//***************************************************************************
function listCart(){

    var cartCopy = [];
    for(var i in cartWhisky) {
        var item = cartWhisky[i];
        var itemCopy = {};
        for (var p in item) {
            itemCopy[p] = item[p];
        }
        //itemCopy.total = (item.price * item.count).toFixed(2);
        cartCopy.push(itemCopy);
    }
    return cartCopy;
}
//});
