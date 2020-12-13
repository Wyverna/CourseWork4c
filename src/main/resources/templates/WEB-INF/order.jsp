<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Order</title>
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
<div id="message"></div>
<form onsubmit="return postOrder()">
    Product: <select name="nameProduct" id="options" required>
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
	<input type="date" id="date3" name="dateOrder" hidden/>
    Date order end: <input type="date" id="date2" name="dateOrderEnd" required/>
    <br><br>
    UnitCost: <input type="number" name="unitCost" id="CostOrder" required readonly/>
    <br><br>
    Address: <input type="text" name="addressClient" id="address"required />
    <br><br>
    OrganizationName: <select type="text" name="organizationName" id="Organization" required>
    <option selected disabled hidden style='display: none' value=''></option>
</select>
    <br><br>
    Time to order: <select type="text" id="time" name="timeToOrder">
    <option>Morning</option>
    <option>Day</option>
    <option>Evening</option>
</select>
    <br><br>
    Extra Product: <select type="text" name="nameExtraProduct" id="Extra" class="ExtraChange" >
    <option selected disabled hidden style='display: none' value=''></option>
</select>
    <br><br>
    <input type="submit" value="Add Order" />
</form>
<script>
    $.getJSON( "./api/products/",function(data) {
      var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
      if(Array.isArray(data))
      {
      for(var i in data)
      {
          html2+='<option>'+data[i]['nameProduct']+'</option>';
      }
      }
      document.getElementById('options').innerHTML=html2;
  });
</script>
<script>
    jQuery('#options').on('change',function(){
            $.ajax
        ({
        type: "GET",
        url: "./api/products/getByProduct",
        data:{nameProduct:$('#options :selected').val()},
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                 if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['organizationName']['organizationName']+'</option>';
                }
                }
                document.getElementById('Organization').innerHTML=html2;
                }});
            });
    jQuery('#Extra').on('change',function(){
         $.ajax
        ({
        type: "GET",
        url: "./api/products/getCost",
        data:{nameProduct:$('#options :selected').val(),organizationName:$('#Organization :selected').val()},
        success:function(data) {
                    document.getElementById("CostOrder").value = data['costProduct'];
                }});
        $.ajax
        ({
        type: "GET",
        url:"./api/extraproducts/getCost",
        data:{nameProduct:$('#options :selected').val(),
            organizationName:$('#Organization :selected').val(),
            nameExtraProduct:$('#Extra :selected').val()},
            success:function(data) {
                    document.getElementById("CostOrder").value=(+(document.getElementById("CostOrder").value))+data['unitCost'];
                }});
    });
    jQuery('#Organization').on('change',function(){
        $.ajax
        ({
        type: "GET",
        url: "./api/extraProducts/getByOrganizationAndProduct",
        data:{organizationName:$('#Organization :selected').val(),nameProduct:$('#options').val()},
        success:function(data) {
                    var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                    if(Array.isArray(data))
                    {
                    for(var i in data)
                    {
                    html2+='<option>'+data[i]['nameExtraProduct']+'</option>';
                    }
                    }
                    document.getElementById('Extra').innerHTML=html2;
        }});
        $.ajax
        ({
        type: "GET",
        url: "./api/products/getCost",
        data:{nameProduct:$('#options :selected').val(),
            organizationName:$('#Organization :selected').val()},
            success:function(data) {
                    document.getElementById("CostOrder").value=data['costProduct'];
                }});
    });
</script>
<script>
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
 if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("date3").value=today;
document.getElementById("date2").setAttribute("min", today);
function postOrder() {
    $.ajax
    ({
        type: "POST",
        data: {
            nameExtraProduct: $('#Extra').val(),
            nameProduct: $('#options').val(),
            dateOrder: $('#date3').val(),
            dateOrderEnd: $('#date2').val(),
            unitCost: $('#CostOrder').val(),
            addressClient: $('#address').val(),
            organizationName: $('#Organization').val(),
            timeToOrder: $('#time').val()

        },
        url: "./api/orders/postOrder",
        success: function (data) {
            document.getElementById('message').innerHTML = data;
        }
    });
    return false;
}
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>
