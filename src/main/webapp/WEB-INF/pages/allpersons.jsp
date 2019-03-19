
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All persons</title>
</head>
<body>
<c:if test="${!empty all}">
    ${all}
</c:if>
</body>
</html>
