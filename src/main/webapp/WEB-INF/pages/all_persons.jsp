<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

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
            background-color: darkgrey;
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
        .col-md-4 .custom-file-input {
            margin-left: 200px;
            padding: 15px;
            width: 1000px;
        }
        #uploadFile {
            margin-left: 30px;
            cursor: pointer;
        }

    </style>

</head>
<body>
<h3 align="center">CV in Database</h3>
<div class="input-group mb-3">
    <a class="btn btn-info" href="${pageContext.request.contextPath}/person?action=create" role="button">Create new CV</a>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <input id="uploadFile" class="btn btn-info" value="Input" name="data" type="file" accept=".json, .xml, .txt">
        <input class="button" type="submit" value="Add to Database"> <br>
    </form>
</div>
<c:if test="${message!=null}">
    <div class="alert alert-success" id = "message">${message}</div>
</c:if>
<c:if test="${errorMessage!=null}">
    <div class="alert alert-danger" id = "message">${errorMessage}</div>
</c:if>
<div style="display: none" id = "ajaxMessage"></div>
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
                <li><a href="/">All</a></li>
                <c:forEach var="specialization" items="${specializations}">
                    <li><a href="/?specId=${specialization.name}">${specialization.name}</a></li>
                </c:forEach>
            </ul>
        </th>
        <th scope="col" colspan="3" align="center" width="fit-content">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${persons}">
        <tr id="personRow${person.id}">
            <td ></td>
            <td>${person.surname}</td>
            <td>${person.name}</td>
            <td>${person.dateOfBirth}</td>
            <td>${person.specialization.name}</td>
            <td><a class="btn btn-info" href="${pageContext.request.contextPath}/person?personId=${person.id}" role="button">Full resume</a></td>
            <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/person?action=update&personId=${person.id}" role="button">Edit </a></td>
            <td>
                <button data-id="${person.id}" class="open-DeleteModal delete btn btn-danger" data-toggle="modal">Delete</button>
            </td>
        </tr>
    </c:forEach>
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content delete-content">
                <input id="userId" type="hidden">
                <div class="modal-header">
                    <h4 class="modal-title">Delete CV</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these CV?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id = "confirmButton">Delete</button>
                </div>
            </div>
        </div>
    </div>
    </tbody>
</table>
<script>
    $(".open-DeleteModal").click(function () {
        var id = $(this).data('id');
        // console.log(id);
        $("#userId").val(id);
        // console.log($("#userId").val());
        $('#deleteEmployeeModal').modal('show');
    });

    $("#confirmButton").click(function () {

        var id = $("#userId").val();
        // console.log($("#userId").val());

        $('#deleteEmployeeModal').modal('hide');
        $.ajax({
            type : "DELETE", // http method
            url : "/person?personId=" + id, // the endpoint
            contentType: 'application/json',

            success : function(json) {

                console.log(json);
                // method to show success message
                var message = $("#ajaxMessage");
                if(json.result == "true"){
                    $("#personRow" + id).remove();
                    message.addClass("alert alert-success");
                } else {
                    message.addClass("alert alert-danger");
                }
                message.html(json.message);
                message.show();
                $('#ajaxMessage').delay(5000).fadeOut();
            }
        });
    });
    $(function() {
        // setTimeout() function will be fired after page is loaded
        // it will wait for 5 sec. and then will fire
        // $("#successMessage").hide() function
        setTimeout(function() {
            $(".alert-success").fadeOut('fast');
        }, 5000);
    });

    $(function() {
        // setTimeout() function will be fired after page is loaded
        // it will wait for 5 sec. and then will fire
        // $("#successMessage").hide() function
        setTimeout(function() {
            $(".alert-danger").fadeOut('fast');
        }, 5000);
    });
</script>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>
</html>
