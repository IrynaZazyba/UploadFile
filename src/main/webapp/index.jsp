<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <script src="resources/js/script.js"></script>
<%--    <link rel="stylesheet" type="text/css" href="style.css">--%>
</head>
<body>
<form id="uploadImageForm" onsubmit="uploadFile();return false" enctype="multipart/form-data" method="post" action="upload">
    <input type="hidden" name="command" value="upload_file">

    <p>
        <input type="file" name="img"/>
    </p>
    <p>
        <input type="submit" value="Отправить"/>
    </p>
</form>

</body>
</html>
<%--accept="image/jpg,image/png,image/gif"--%>