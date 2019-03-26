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

    <title>CV</title>

    <style>
        body {
            width: 100%;
            margin: auto;
            max-width: 1400px;
        }
        h3 {
            display: block;
            background-color: yellow;
            padding-top: 50px;
            padding-bottom: 50px;
            height: fit-content;
        }
        table {
            counter-reset: rowNumber;
            width: fit-content;
        }

        table tr td:first-child::before {
            counter-increment: rowNumber;
            content: counter(rowNumber);
        }
        .inner {
            width: 700px;
        }
        span {
            margin-left: 40px;
            margin-right: 40px;
        }
    </style>

</head>
<body>
<h3 align="center">CV in Database</h3>
<div class="input-group mb-3">
    <a class="btn btn-info" href="${pageContext.request.contextPath}/new_person_cv" role="button">Create new CV</a>
    <%--<span></span>--%>
    <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroupFileAddon01">Upload file CV</span>
    </div>
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
    </div>
</div>
<c:if test="${message!=null}">
    <div class="alert alert-danger" id = "message">${message}</div>
</c:if>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">№</th>
        <th scope="col">Surname</th>
        <th scope="col">Name</th>
        <th scope="col">Date of birth</th>
        <th scope="col">Specialization
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Filter by</button>
                <ul class="dropdown-menu">
                    <li><a href="/persons">All</a></li>
                    <c:forEach var="specialization" items="${specializations}">
                        <li><a href="/persons?specId=${specialization.name}">${specialization.name}</a></li>
                    </c:forEach>
                </ul>
        </th>
        <th scope="col" colspan="3" align="center" width="fit-content">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${persons}">
    <tr>
        <td ></td>
        <td>${person.surname}</td>
        <td>${person.name}</td>
        <td>${person.dateOfBirth}</td>
        <td>${person.specialization.name}</td>
        <td><a class="btn btn-info" href="${pageContext.request.contextPath}/person_info?personId=${person.id}" role="button">Full resume</a></td>
        <td><a class="btn btn-primary" href="/new_person_cv?personId=${person.id}" role="button">Edit </a></td>
        <td>
            <form method="post" action="/deletePerson">
                <input type="number" hidden name="personId" value="${person.id}">
                <input type="submit" name="submit" value="Delete" class="btn btn-danger">
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>

    <script>
        $(function() {
            bs_input_file();
        });
        $(document).ready(function(){
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $(".dropdown-menu li").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>

</table>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
