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
<script>
    function categoryCompaniesTable() {
        $.getJSON("./api/categoryCompanies/", function (data) {
            var html = '<table id="tablecat" border="1"><TR><TD>Organization Name</TD><TD>Name Category</TD></TR>';
            if (Array.isArray(data)) {
                for (var i in data) {
                    html += '<tr>';
                    html += '<td>' + data[i]['organizationName']['organizationName'] + '</td><td>'
                        + data[i]['categoryProduct']['categoryProductsName'] + '</td></tr>';
                }
            }
            html += '</table>';
            document.getElementById('table').innerHTML = html;
        });
    }
    categoryCompaniesTable()
</script>
<div th:if="${role}=='admin'">
<div id="message"></div>
<form onsubmit="return send()">
    OrganizationName: <select id='Organization' name="organizationName" required >
    </select>
    <br><br>
    Category: <select id="Category" name="categoryProduct" required></select>
    <br><br>
    <input type="submit" value="Register" />
</form>
</div>
<script>
    $.getJSON( "./api/organizations/",function(data) {
      var html2 ="<option selected disabled hidden style='display: none' value=''></option>";
      if(Array.isArray(data))
      {
      for(var i in data)
      {
          html2+='<option>'+data[i]['organizationName']+'</option>';
      }
      }
      document.getElementById('Organization').innerHTML=html2;
  });
    $.getJSON( "./api/categoryProducts/",function(data) {
      var html2 ="<option selected disabled hidden style='display: none' value=''></option>";
      if(Array.isArray(data))
      {
      for(var i in data)
      {
          html2+='<option>'+data[i]['categoryProductsName']+'</option>';
      }
      }
      document.getElementById('Category').innerHTML=html2;
  });
    function send() {
        $.ajax
        ({
            type: "POST",
            data: {
                organizationName: $('#Organization').val(),
                categoryProduct: $('#Category').val(),
            },
            url: "./api/categoryCompanies/postCategory",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                categoryCompaniesTable();
            }
        });
        return false;
    }
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>