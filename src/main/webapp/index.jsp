<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <script src="resources/js/script.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
<div class="block-upload-form">
    <div id="errorMessage"></div>

    <form id="uploadImageForm" onsubmit="uploadFile();return false" enctype="multipart/form-data" method="post"
          action="upload">
        <input type="hidden" name="command" value="upload_file">

        <p>
            <input type="file" name="img" accept="image/jpg,image/png,image/gif"/>
        </p>
        <p>
            <input type="submit" value="Отправить"/>
        </p>
    </form>
    <div>
        <form class="form-horizontal" role="form" method="post" action="display">
            <input type="hidden" name="command" value="display_file">
            <p>
                <button type="submit">Показать файлы</button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
