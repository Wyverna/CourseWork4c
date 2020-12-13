<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Extra Product</title>
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
<script>
    function extraProductTable() {
        $.getJSON("./api/extraProducts/", function (data) {
            var html = '<table id="tableprod" border="1"><TR><TD>Name Extra Product</TD><TD>Organization</TD>' +
                '<TD>Cost</TD><TD>Name Product</TD></TR>';
            if (Array.isArray(data)) {
                for (var i in data) {
                    html += '<tr>';
                    html += '<td>' + data[i]['nameExtraProduct'] + '</td><td>' + data[i]['organizationName']['organizationName'] + '</td><td>'
                        + data[i]['unitCost'] +'</td><td>' + data[i]['nameProduct']['nameProduct'] + '</td></tr>';
                }
            }
            html += '</table>';
            document.getElementById('table').innerHTML = html;
        });
        $.getJSON("./api/extraProducts/", function (data) {
            var html2 = '<option selected disabled hidden style="display: none" value=""></option>';
            if (Array.isArray(data)) {
                for (var i in data) {
                    html2 += '<option>' + data[i]['nameExtraProduct'] + '</option>';
                }
            }
            document.getElementById('NameExtraProduct').innerHTML = html2;
            document.getElementById('NameExtraProduct2').innerHTML = html2;
        });
    }
    extraProductTable()
</script>
<div id="message"></div>
Create extra product:<br/>
<form onsubmit="return postExtraProduct()">
    Name extra product: <input id="extraProduct" name="nameExtraProduct" required />
    <br><br>
    Organization name: <select id="Organization" name="organizationName" required >
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    Cost extra product: <input type="number" name="unitCost" id="unitCost" required />
    <br><br>
    Name product:
    <select id="Product" name="nameProduct" required >
        <option selected disabled hidden style='display: none' value=''></option>
    </select><br>
    <input type="submit" value="Create" />
</form>
<script>
    $.getJSON( "./api/organizations/",function(data) {
      var html2='<option selected disabled hidden style="display: none" value=""></option>';
      if(Array.isArray(data))
      {
      for(var i in data)
      {
          html2+='<option>'+data[i]['organizationName']+'</option>';
      }
      }
      document.getElementById('Organization').innerHTML=html2;
  });
    jQuery('#Organization').on('change',function(){
          $.ajax
        ({
        type: "GET",
        url:"./api/products/getByOrganization",
        data:{organizationName:$('#Organization :selected').val()},
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['nameProduct']+'</option>';
                }
                }
                document.getElementById('Product').innerHTML=html2;
                }});
    });
</script>
Delete extra product:
<form onsubmit="return deleteExtraProduct()">
    Name Extra Product:<select id="NameExtraProduct" name="nameExtraProduct" required >
        <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    Organization name: <select id="OrganizationExtra" name="organizationName" required >
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
      <br><br>
    Name Product: <select id="NProd3" name="nameProduct" required>
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    <input type="submit" value="Delete" />
</form>
Update cost extra product
<form onsubmit="return updateExtraProduct()">
    Name Extra Product:<select id="NameExtraProduct2" name="nameExtraProduct" required >
        <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    Organization name: <select id="OrganizationExtra2" name="organizationName" required>
    <option selected disabled hidden style='display: none' value=''></option>
</select>
  <br><br>
    Name Product: <select id="NProd2" name="nameProduct" required>
    <option selected disabled hidden style='display: none' value=''></option>
</select>
    <br><br>
    Cost: <input type="number" id="unitCost2" name="costProduct" required/>
    <br><br>
    <input type="submit" value="Update" />
</form>
<script>
        jQuery('#NameExtraProduct').on('change',function(){
         $.ajax
        ({
        type: "GET",
        url: "./api/extraProducts/getOrganizationsByExtraProduct",
        data:{nameExtraProduct:$('#NameExtraProduct :selected').val()},
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['organizationName']['organizationName']+'</option>';
                }
                }
                document.getElementById('OrganizationExtra').innerHTML=html2;
                }});
    });
</script>
<script>
    jQuery('#NameExtraProduct2').on('change',function(){
        $.ajax
        ({
        type: "GET",
        url: "./api/extraProducts/getOrganizationsByExtraProduct",
        data:{nameExtraProduct:$('#NameExtraProduct2 :selected').val()},
        success:function(data) {
                var html2 ="<option selected disabled hidden style='display: none' value=''></option>";
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['organizationName']['organizationName']+'</option>';
                }
                }
                document.getElementById('OrganizationExtra2').innerHTML=html2;
                }});
    });
     jQuery('#OrganizationExtra2').on('change',function(){
          $.ajax
        ({
        type: "GET",
        url:"./api/products/getByOrganization",
        data:{organizationName:$('#OrganizationExtra2 :selected').val()},
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['nameProduct']+'</option>';
                }
                }
                document.getElementById('NProd2').innerHTML=html2;
                }});
    });
         jQuery('#OrganizationExtra').on('change',function(){
          $.ajax
        ({
        type: "GET",
        url:"./api/products/getByOrganization",
        data:{organizationName:$('#OrganizationExtra :selected').val()},
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>';
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['nameProduct']+'</option>';
                }
                }
                document.getElementById('NProd3').innerHTML=html2;
                }});
    });
    function postExtraProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameExtraProduct: $('#extraProduct').val(),
                nameProduct: $('#Product').val(),
                unitCost: $('#unitCost').val(),
                organizationName: $('#Organization').val()

            },
            url: "./api/extraProducts/postExtraProduct",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                extraProductTable();
            }
        });
        return false;
    }
    function deleteExtraProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameExtraProduct:$('#NameExtraProduct').val(),
                nameProduct: $('#NProd3').val(),
                organizationName: $('#OrganizationExtra').val()
            },
            url: "./api/extraProducts/deleteExtraProduct",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                extraProductTable();
            }
        });
        return false;
    }
    function updateExtraProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameExtraProduct: $('#NameExtraProduct2').val(),
                nameProduct: $('#NProd2').val(),
                costProduct: $('#unitCost2').val(),
                organizationName: $('#OrganizationExtra2').val()

            },
            url: "./api/extraProducts/updateCost",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                extraProductTable();
            }
        });
        return false;
    }
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>
