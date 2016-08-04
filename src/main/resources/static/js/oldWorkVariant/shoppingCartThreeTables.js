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
            addItemToCart(photo, name, describe, quantity, price);
            comeBack(); //возвращаемся на главную страницу без изменения кол-ва виски, т.к. оно в корзине.
            //sendBuyWhiskyInJava();
        }
        else if (quantity > quantityInDB && quantity < 0) {
            clearCart()
        }
    }
//AddItemToCart(photo, name, describe, quantity, price)
function addItemToCart(photo, name, describe, quantity, price) {

    var item = new itemCart(photo, name, describe, quantity, price);
    cartWhisky.push(item);
    saveCart();
}
//clear
    function clearCart() {
        cartWhisky = [];
        saveCart();
    }



    function comeBack() {
        window.location.href = "warehouseWhisky";
    }

    function cartOpen() {
        window.location.href = "allCart";
    }
$(document).ready(function() {
    loadCart();
    //var cartArray = listCart();
    var cartArrayDIV = listCart();

    for (var i in cartArrayDIV) {
        $("#tblWhisky tbody").append('<tr><td><img src="'+cartArrayDIV[i].photo+'" width=35 height=80></td>\
            <td>'+cartArrayDIV[i].name+'</td>\
            <td>'+cartArrayDIV[i].describe+'</td>\
            <td>'+cartArrayDIV[i].quantity+'</td>\
            <td>'+cartArrayDIV[i].price+'</td>\
            <td>'+cartArrayDIV[i].total+'</td></tr>');
    }
    //var table ='';
    //var rows = cartArray.length;
    //for(var r in cartArray){
    //    table +='<tr>';
    //        table +="<td>"+"<img src='"+cartArray[r].photo+"' width='35' height='80'>"+"</td>";
    //        table +="<td width='100'>"+cartArray[r].name+"</td>";
    //        table +='<td width="150">'+cartArray[r].describe+'</td>';
    //        table +='<td align="center" width="100">'
    //            +"<button class='minus' id='minusID' data-name='"+cartArray[r].name+"'>-</button>"
    //            +" "+cartArray[r].quantity
    //            +" "+"<button class='plus' data-name='"+cartArray[r].name+"' >+</button>"+'</td>';
    //        table +='<td width="80" align="center">'+cartArray[r].price+'</td>';
    //        table +='<td width="20">' +"<button class='deleteItem' data-name='"+cartArray[r].name+"'>X</button>"+'</td>';
    //    table +='</tr>';
    //}
    //document.write('<table align="center">'+table+'</table>');
    ////function tryNew(){
    //$(".minus").click(function(event){
    ////$('#minusID').on("click", ".minus", function (e){
    //    var name = $(this).attr("data-name");
    //    console.log(name);
    //    removeOneItemsFromCart(name);
    //    see();
    //});
    //function see(){ loadCart();
    //    var cartArray = listCart();
    //    var table ='';
    //    var rows = cartArray.length;
    //    for(var r in cartArray){
    //        table +='<tr>';
    //        table +="<td>"+"<img src='"+cartArray[r].photo+"' width='35' height='80'>"+"</td>";
    //        table +="<td width='100'>"+cartArray[r].name+"</td>";
    //        table +='<td width="150">'+cartArray[r].describe+'</td>';
    //        table +='<td align="center" width="100">'
    //            +"<button class='minus' id='minusID' data-name='"+cartArray[r].name+"'>-</button>"
    //            +" "+cartArray[r].quantity
    //            +" "+"<button class='plus' data-name='"+cartArray[r].name+"' >+</button>"+'</td>';
    //        table +='<td width="80" align="center">'+cartArray[r].price+'</td>';
    //        table +='<td width="20">' +"<button class='deleteItem' data-name='"+cartArray[r].name+"'>X</button>"+'</td>';
    //        table +='</tr>';
    //    }
    //document.write('<table align="center">'+table+'</table>');
    //}

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
//RemoveItemsFromCart(name) //Remove only one
function removeOneItemsFromCart(name){
    for(var i in cartWhisky) {
        cartWhisky[i].name === name //"3" == 3 true, "3"===3 false - because "3" is a string
        cartWhisky[i].quantity--;
        console.log(cartWhisky[i].quantity);
        if (cartWhisky[i].quantity ===0) {
            //Метод splice() изменяет содержимое массива, удаляя существующие элементы и/или добавляя новые.
            //in this case we delete Apple from cart
            cartWhisky.splice(i,1);
        }
        break;
    }
    saveCart();
}

//RemoveAllItemsFromCart()  //Remove all
function removeAllItemsFromCart(name) {
    for(var i in cartWhisky) {
        if(cartWhisky[i].name === name) {
            cartWhisky.splice(i, 1);
            break;
        }
    }
    saveCart();
}

//clearCart()
function clearCart() {
    cartWhisky = [];
    saveCart();
}

//countCart() -->return total count Summary count all item in cart
function countCart(){
    var totalCount = 0;
    for(var i in cartWhisky) {
        totalCount += cartWhisky[i].count;
    }
    return totalCount;
}

//totalCart() --> return total cost Summary value price all item in cart
function totalPriceInCart() {

    var totalPrice = 0;
    for(var i in cartWhisky) {
        totalPrice = cartWhisky[i].price * cartWhisky[i].count + totalPrice;
    }
    //toFixed() - кол-во знаков после запятой
    return totalPrice.toFixed(2);
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

});