function checkPasswordMatch() {
    var password = $("#txtuserLoginpassword").val();
    var confirmPassword = $("#txtuserLoginpasswordConfirm").val();

    if (password != confirmPassword)
        $("#divCheckPasswordMatch").html("<div class='error-password'>Passwords do not match!</div>");
    else
        $("#divCheckPasswordMatch").html("<div class='okey-password'>Passwords match.</div>");
}

$(document).ready(function () {
   $("#txtuserLoginpasswordConfirm").keyup(checkPasswordMatch);
});


function checkform(f) {
	  var errMSG = ""; 
	  // цикл ниже перебирает все элементы в объекте f, 
	  // переданном в качестве параметра
	  // функции, в данном случае - наша форма.            
	  for (var i = 0; i<f.elements.length; i++) 
	    // если текущий элемент имеет атрибут required
	    // т.е. обязательный для заполнения
	    if (null!=f.elements[i].getAttribute("required")) 
	       // проверяем, заполнен ли он в форме
	        if (isEmpty(f.elements[i].value)) // пустой
	            errMSG += "  " + f.elements[i].name + "\\n"; // формируем сообщение
	                                                       // об ошибке, перечисляя 
	                                                       // незаполненные поля
	        // если сообщение об ошибке не пусто,
	        // выводим его, и возвращаем false     
	        if ("" != errMSG) {
	            alert("Не заполнены обязательные поля:\\n" + errMSG);
	            return false;
	        }
	}


function isEmpty(str) {
	   for (var i = 0; i < str.length; i++)
	      if (" " != str.charAt(i))
	          return false;
	      return true;
	}
