<html>
<head>
    <title>Report</title>
    <meta charset="UTF-8" />
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
    <script>
           function send() {
         $.ajax
        ({
        type: "GET",
        url:"./api/orders/getSumByDate",
        data:{dateOrder:$('#date1').val(),dateOrderEnd:$('#date2').val()},
        success:function(data) {
        var sum=0;
        var html2='';
        if(Array.isArray(data))
        {
        for(var i in data)
        {
          sum+=data[i]['unitCost'];
        }
        }
        html2+='С '+$('#date1').val()+' по '+$('#date2').val()+' получена сумма '+sum+'<br/>';
        document.getElementById('sum').innerHTML=html2;}});
		$.ajax
        ({
            type: "GET",
            data:{dateOrder:$('#date1').val(),dateOrderEnd:$('#date2').val(),nameProduct:''},
            url:"./api/orders/getByDate",
            success:function(data) {
        var html='<table id="tableord" border="1"><TR><TD>OrderID</TD><TD>NameProduct</TD><td>Username</td>'+
        '<TD>dateOrder</TD><TD>dateOrderEnd</TD><TD>unitCost</TD>'+
        '<TD>address</TD><TD>organizationName</TD><TD>Time to order</TD><TD>ExtraProduct</TD>';
        if(Array.isArray(data))
        {
        for(var i in data)
        {
          html+='<tr>';
          html+='<td>'+data[i]['orderId']+'</td><td>'+data[i]['nameProduct']['nameProduct']+
              '</td><td>'+data[i]['username']['loginuser']+'</td><td>'
              +data[i]['dateOrder']+
              '</td><td>'+data[i]['dateOrderend']+'</td><td>'+data[i]['unitCost']+'</td>'+
              '<td>'+data[i]['addressClient']+'</td><td>'+data[i]['organizationName']+'</td><td>'+
                  data[i]['timeToOrder']+'</td><td>'+data[i]['extraProduct']+'</td></tr>';
        }
        }
        html+='</table>';
        document.getElementById('table').innerHTML=html;
        }});
        }
    </script>
    <div id='sum'></div>
    <div>Report:</div>
    <div>Date start report: <input type="date" id="date1" name="dateOrder" /></div>
    <br><br>
    <div>Date end report: <input type="date" id="date2" name="dateOrderEnd" /></div>
    <br><br>
    <button onclick='send()'>Search</button>
<div th:insert="/templ::footer"></div>
</body>
</html>
