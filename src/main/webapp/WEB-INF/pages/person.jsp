<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Person info</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="assets/css/jquery-confirm.min.css">
</head>
<body>

<c:if test="${!empty person}">
    <h3 class="text-center">All persons</h3>

    <table class="table table-striped">
        <tr>
            <td width="150">Name</td>
            <td width="150">Surname</td>
            <td width="150">Date of birth</td>
            <td width="150">City</td>
            <td width="150">Address</td>
            <td width="150">Phone number</td>
            <td width="150">Email</td>
        </tr>
        <c:forEach var="person" items="${person}">
                <td>${person.name}</td>
                <td>${person.surname}</td>
                <td>${person.dateOfBirth}</td>
                <td>${person.contact.city}</td>
                <td>${person.contact.address}</td>
                <td>${person.contact.phoneNumber}</td>
                <td>${person.contact.email}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%--${persons}--%>

</body>
</html>
