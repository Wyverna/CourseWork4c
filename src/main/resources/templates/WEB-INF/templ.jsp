<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="./style.css"/>
    <title>List</title>
</head>
<body>
<div th:fragment="main">
    <div id="header" class="container">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="./">Homepage</a></li>
			<li><a href="./organization">Organization</a></li>
			<li><a href="./order">Add order</a></li>
			<li><a href="./CategoryCompany">Category Company </a></li>
			<div th:if="${role}=='admin'">
			<li><a href="./product">Register Product</a></li>
			<li><a href="./extraproduct">Register Extra Product</a></li>
			<li><a href="./xml">XML Page</a></li>
			<li><a href="./report">Report</a></li>
			</div>
		</ul>
	</div>
</div>
<div id='table'></div>
<div id = 'error'><div th:out="${message}"></div>
</div>
</div>
	<div th:fragment="footer">
<div id="footer">
    <br><br><br><br><br><br><br><br>
</div>
</div>

</body>
</html>