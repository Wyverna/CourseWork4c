<html>
<head>
    <title>Export and import XML</title>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>
<div th:insert="/templ::main"></div>
Export XML:
<button onclick='location.href="./api/xml/ExportXML"'>ExportXML</button>
<br><br>
Import XML:
<form action="./api/xml/ImportXML" method="POST" enctype="multipart/form-data">
    <input type="file" name="file" size="60" required/>
    <br>
    <input type="submit" value="Import" />
</form>
<div th:insert="/templ::footer"></div>
</body>
</html>