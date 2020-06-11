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
    <c:forEach var="fileName" items="${requestScope.fileNames}">
        <img style="width:70px;height: 70px" alt="${fileName}"
             src="${pageContext.request.contextPath}/display?command=get_file&fileName=${fileName}">
    </c:forEach>



</div>
<div>
    <a href="${pageContext.request.contextPath}/index.jsp"> <button type="submit">Назад </button></a>
</div>
</body>
</html>
