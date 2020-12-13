<html xmlns:th="http://www.thymeleaf.org">
<head></head>
<body>
<div th:if="${role}!=null">
  <div th:insert="/main"/>
</div>
<div th:if="${role}==null">
  <div th:insert="/login"/>
</div>
</body>
</html>