<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>RegisterProduct</title>
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
<div id="message"></div>
<form onsubmit="return postProduct()">
    Name product: <input id="product" name="nameProduct" required />
    <br><br>
    Cost product: <input type="number" id="costProduct" name="costProduct" required />
    <br><br>
    Category product: <select name="categoryProduct" id="Category" required />
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    Organization name: <select id="Organization" name="organizationName" required>
    <option selected disabled hidden style='display: none' value=''></option>
</select>
    <br><br>
    <input type="submit" value="Register" />
</form>
<form onsubmit="return deleteProduct()">
    Product name: <select name="nameProduct" id='product1' required></select>
    <br><br>
    Organization name: <select id="Organization1" name="organizationName" required />
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    <input type="submit" value="Delete" />
</form>
<form onsubmit="return updateProduct()">
    Product name: <select name="nameProduct" id='product2' required></select>
    <br><br>
	Cost: <input type="number" id="costProduct1" name="costProduct" required/>
    <br><br>
    Organization name: <select id="Organization2" name="organizationName" required/>
    <option selected disabled hidden style='display: none' value=''></option>
    </select>
    <br><br>
    <input type="submit" value="Update" />
</form>
<script>
    function productTable() {
        $.getJSON("./api/products/", function (data) {
            var html = '<table id="tableprod" border="1"><TR><TD>Name Product</TD><TD>Cost</TD>' +
                '<TD>Category</TD><TD>Organization</TD></TR>';
            var html2 = '<option selected disabled hidden style="display: none" value=""></option>';
            if (Array.isArray(data)) {
                for (var i in data) {
                    html += '<tr>';
                    html += '<td>' + data[i]['nameProduct'] + '</td><td>' + data[i]['costProduct'] + '</td><td>' +
                        data[i]['categoryProduct']['categoryProductsName'] +
                        '</td><td>' + data[i]['organizationName']['organizationName'] + '</td></tr>';
                    html2 += '<option>' + data[i]['nameProduct'] + '</option>';
                }
            }
            html += '</table>';
            document.getElementById('table').innerHTML = html;
            document.getElementById('product1').innerHTML = html2;
            document.getElementById('product2').innerHTML = html2;
        });
    }
    productTable();
</script>
<script>
    $.getJSON( "./api/categoryProducts/",function(data) {
      var html='<option selected disabled hidden style="display: none" value=""></option>';
      if(Array.isArray(data))
      {
      for(var i in data)
      {
          html+='<option>'+data[i]['categoryProductsName']+'</option>';
      }
      }
      document.getElementById('Category').innerHTML=html;
  });
</script>
<script>
    jQuery('#Category').on('change',function(){
        $.ajax
        ({
        type: "GET",
        data:{categoryProduct:$('#Category :selected').val()},
        url:"./api/categoryCompanies/getOrganizations",
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
       jQuery('#product1').on('change',function(){
        $.ajax
        ({
        type: "GET",
        data:{nameProduct:$('#product1 :selected').val()},
        url:"./api/products/getByProduct",
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>'
                if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['organizationName']['organizationName']+'</option>';
                }
                }
                document.getElementById('Organization1').innerHTML=html2;
                }});
                
    });
       jQuery('#product2').on('change',function(){
                  $.ajax
        ({
        type: "GET",
        data: {nameProduct:$('#product2 :selected').val()},
        url:"./api/products/getByProduct",
        success:function(data) {
                var html2 ='<option selected disabled hidden style="display: none" value=""></option>'
                 if(Array.isArray(data))
                {
                for(var i in data)
                {
                    html2+='<option>'+data[i]['organizationName']['organizationName']+'</option>';
                }
                }
                document.getElementById('Organization2').innerHTML=html2;
                }});
    });
</script>
<script>
    function postProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameProduct: $('#product').val(),
                costProduct: $('#costProduct').val(),
                categoryProduct: $('#Category').val(),
                organizationName: $('#Organization').val()

            },
            url: "./api/products/postProduct",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                productTable();
            }
        });
        return false;
    }

    function deleteProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameProduct: $('#product1').val(),
                organizationName: $('#Organization1').val()

            },
            url: "./api/products/deleteProduct",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                productTable();
            }
        });
        return false;
    }
    function updateProduct() {
        $.ajax
        ({
            type: "POST",
            data: {
                nameProduct: $('#product2').val(),
                costProduct: $('#costProduct1').val(),
                organizationName: $('#Organization2').val()

            },
            url: "./api/products/updateCost",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                productTable();
            }
        });
        return false;
    }
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>
