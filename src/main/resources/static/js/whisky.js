
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

    //var nameSelectedWhisky = $('#nameWhiskey').val();
    //var quantitySelectedWhisky = $('#numberOrderWhisky').val();
    //$.ajax({   //тип запроса
    //    headers: {
    //        "Accept": "application/json",
    //        "Content-Type": "application/json"
    //    },
    //    type: "POST", //это типа method
    //    data: JSON.stringify(nameSelectedWhisky.id, quantitySelectedWhisky.id),
    //
    //    url: '/buySuccessfulWhisky',
    //    success: function (msg) {  //msg - показывает ответ с сервера
    //        window.location.href = "warehouseWhisky";
    //    },
    //    error:function(){
    //        alert("ERROR");
    //    }
    //})
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


function buyFromJS() {
    //var newWin = $('#go').open();
    //newWin.document.open();
    //newWin.document.writeln('Динамический контент.');
    //newWin.document.write('<div>Как-то так.</div>');
    //newWin.document.close();

sendBuyWhiskyInJava();
}

  function sendBuyWhiskyInJava(){
        $('#buySelectedWhiskyThroughJavaScript').submit(function(e){
            e.preventDefault();

            $.ajax({   //тип запроса
                type: "POST", //это типа method
                url: '/buySuccessfulWhisky',
                data: {
                    nameSelectedWhisky: $("#nameWhiskey").val(),
                quantitySelectedWhisky: $("#numberOrderWhisky").val()
        },
                success: function(data) {  //msg - показывает ответ с сервера
                    window.location.href = "warehouseWhisky";
                    console.log(data)
                }
        })
    })
}





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