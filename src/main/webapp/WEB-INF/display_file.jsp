<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <script src="resources/js/script.js"></script>
    <%--    <link rel="stylesheet" type="text/css" href="style.css">--%>
</head>
<body>
<div>
    <img  style="width:70px;height: 70px" alt="alt" src="${requestScope.url}">
</div>
</body>
</html>
