<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Main page</title>
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<script>
    function getCookie(name) {
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }
</script>
<div th:insert="/templ::main"></div>
<button onclick='location.href="./logout"'>Sign out</button>
<div th:if="${role}=='admin'">
<script>
    $.getJSON( "./api/orders/",function(data) {
        var html='<table id="tableord" border="1"><TR><TD>OrderID</TD><TD>NameProduct</TD><td>Username</td>'+
            '<TD>dateOrder</TD><TD>dateOrderEnd</TD><TD>unitCost</TD>'+
            '<TD>address</TD><TD>organizationName</TD><TD>Time to order</TD><TD>ExtraProduct</TD>';
        for(var i in data)
        {
            html+='<tr>';
            html+='<td>'+data[i]['orderId']+'</td><td>'+data[i]['nameProduct']['nameProduct']+'</td><td>'
                +data[i]['username']['loginuser']+'</td><td>'
                +data[i]['dateOrder'].slice(0,10)+'</td><td>'+data[i]['dateOrderEnd'].slice(0,10)+'</td><td>'+data[i]['unitCost']+'</td>'+
                '<td>'+data[i]['addressClient']+'</td><td>'+data[i]['organizationName']['organizationName']+'</td><td>'+
                data[i]['timeToOrder']+'</td><td>'+data[i]['extraProduct']+'</td></tr>';
        }
        html+='</table>';
        document.getElementById('table').innerHTML=html;
    });
</script>
</div>
<div th:if="${role}=='user'">
<script>
    $.ajax
    ({
        type: "GET",
        data:{base64:getCookie('BASE64')},
        url:"./api/orders/getByUser",
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
                        '</td><td>'+data[i]['dateOrderEnd']+'</td><td>'+data[i]['unitCost']+'</td>'+
                        '<td>'+data[i]['addressClient']+'</td><td>'+data[i]['organizationName']['organizationName']+'</td><td>'+
                        data[i]['timeToOrder']+'</td><td>'+data[i]['extraProduct']+'</td></tr>';
                }
            }
            html+='</table>';
            document.getElementById('table').innerHTML=html;
        }});
</script>
</div>
<script>
    function send() {
        $.ajax
        ({
            type: "GET",
            data:{dateOrder:$('#date1').val(),dateOrderEnd:$('#date2').val(),nameProduct:$('#name').val(),
                base64:getCookie('BASE64')},
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
                        html+='<td>'+data[i]['orderId']+'</td><td>'+data[i]['nameProduct']['nameProduct']+'</td><td>'
                            +data[i]['username']['loginuser']+'</td><td>'
                            +data[i]['dateOrder']+
                            '</td><td>'+data[i]['dateOrderEnd']+'</td><td>'+data[i]['unitCost']+'</td>'+
                            '<td>'+data[i]['addressClient']+'</td><td>'+data[i]['organizationName']['organizationName']
                            +'</td><td>'+data[i]['timeToOrder']+'</td><td>'+data[i]['extraProduct']+'</td></tr>';
                    }
                }
                html+='</table>';
                document.getElementById('table').innerHTML=html;
            }});
    }
</script>
<div>Search Order:
    Name Product: <input name="nameProduct" id='name' />
    <br><br>
    DateStart: <input type="date" name="dateOrder" id='date1'/>
    <br><br>
    DateEnd: <input type="date" name="dateOrderEnd" id='date2' />
<button onclick='send()'>Search</button>
</div>
<script>
    jQuery('#date1').on('change',function(){
        document.getElementById("date2").setAttribute("min", $('#date1').val());
    });
    jQuery('#date2').on('change',function(){
        document.getElementById("date1").setAttribute("max", $('#date2').val());
    });
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>