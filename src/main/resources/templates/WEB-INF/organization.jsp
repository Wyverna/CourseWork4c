<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Organization</title>
    <script src="jquery.js"
            type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
<div th:if="${role}=='admin'">
<div id="message"></div>
<form onsubmit="return send()" >
    OrganizationName: <input id="org" name="organizationName" required />
    <br><br>
    AddressOrganization: <input id="addr" name="addressOrganization" required />
    <br><br>
    Telephone: <input id="tel" type="tel" name="telephone" required />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</div>
<script type="text/javascript">
    function organizationTable() {
        $.getJSON("./api/organizations/", function (data) {
            var html = '<table id="tableorg" border="1"><TR><TD>Organization Name</TD><TD>Address Organization</TD>' +
                '<TD>Telephone</TD></TR>'
            if (Array.isArray(data)) {
                for (var i in data) {
                    html += '<tr>';
                    html += '<td>' + data[i]['organizationName'] + '</td><td>' + data[i]['addressOrganization'] + '</td><td>' + data[i]['telephone'] +
                        '</td></tr>'
                }
            }
            html += '</table>';
            document.getElementById('table').innerHTML = html;
        });
    }
    organizationTable();
    function send() {
        $.ajax
        ({
            type: "POST",
            data: {
                organizationName: $('#org').val(),
                addressOrganization: $('#addr').val(),
                telephone: $('#tel').val()
            },
            url: "./api/organizations/postOrganization",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
                organizationTable();
            }
        });

        return false;
    }
</script>
<div th:insert="/templ::footer"></div>
</body>
</html>