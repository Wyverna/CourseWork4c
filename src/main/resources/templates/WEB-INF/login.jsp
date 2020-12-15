<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <script src="jquery.js"
            type="text/javascript"></script>
</head>
<body>
<div id="message"></div>
<form onsubmit="return send()" class="alignform">
    Login: <input name="login" id="login" required />
    <br><br>
    Password: <input type="password" id="password" name="password" required />
    <br><br>
    <input type="submit" value="Authorize" />
  </form>
<script>
    function send() {
        $.ajax
        ({
            type: "POST",
            data: {
                login: $('#login').val(),
                password: $('#password').val()
            },
            url: "./api/users/login",
            success: function (data) {
                document.cookie = "BASE64="+data;
                window.location.replace('/');
                window.location.reload(true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                document.getElementById('message').innerHTML = jqXHR.responseJSON.message;
            }
            });
        return false;
        }
</script>
  <button onclick='location.href="/register"'>Registration</button>
  </body>
</html>
