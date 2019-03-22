<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Person CV</title>

</head>
<body>
<h3 align="center">Resume</h3>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Surname</th>
        <th scope="col">Name</th>
        <th scope="col">Date of birth</th>
        <th scope="col">Specialization</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${person_info.surname}</td>
            <td>${person_info.name}</td>
            <td>${person_info.dateOfBirth}</td>
            <td>${person_info.specialization}</td>
        </tr>
    </tbody>
</table>
<h3 align="center">Contacts</h3>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">City</th>
        <th scope="col">Address</th>
        <th scope="col">Phone number</th>
        <th scope="col">e-mail</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${person_info.contact.city}</td>
        <td>${person_info.contact.address}</td>
        <td>${person_info.contact.phoneNumber}</td>
        <td>${person_info.contact.email}</td>
    </tr>
    </tbody>
</table>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>