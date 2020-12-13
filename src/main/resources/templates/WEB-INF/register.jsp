<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Registration</title>
    <script src="jquery.js"
            type="text/javascript"></script>
</head>
<body>
<div id="message"></div>
<form onsubmit="return send()" class="alignform" on>
    Login: <input id="login" name="loginuser" required />
    <br><br>
    Password: <input type="password" id="password" name="passworduser" required />
    <br><br>
    Email: <input type="email" id="email" name="emailuser" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
<script>
    function send() {
        $.ajax
        ({
            type: "POST",
            data: {
                loginuser: $('#login').val(),
                passworduser: $('#password').val(),
                emailuser: $('#email').val()
            },
            url: "./api/users/register",
            success: function (data) {
                document.getElementById('message').innerHTML = data;
            }
        });
        return false;
    }
</script>
<button onclick='location.href="./"'>Return</button>
</body>
</html>
