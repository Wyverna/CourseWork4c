<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>List</title>
</head>
<body>
<span style="color: red" th:utext="${message}"></span>
<form th:action="@{./api/users/login}" method="Post" class="alignform">
    Login: <input name="login" required />
    <br><br>
    Password: <input type="password" name="password" required />
    <br><br>
    <input type="submit" value="Authorize" />
  </form>
  <button onclick='location.href="/register"'>Registration</button>
  </body>
</html>
