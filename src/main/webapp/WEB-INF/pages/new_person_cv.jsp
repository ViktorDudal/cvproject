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

    <style>
        body {
            width: 1400px;
            margin: auto;
        }
        h3 {
            display: block;
            background-color: darkgray;
            padding-top: 25px;
            padding-bottom: 25px;
            height: fit-content;
        }
    </style>

    <title>CV</title>

</head>
<body>
<h3 align="center">Create new CV</h3>
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
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Surname user" aria-label="Surname user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Name user" aria-label="Name user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Date of birth user" aria-label="Date of birth user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Options</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01">
                    <option selected>Choose...</option>
                    <option value="1">Java</option>
                    <option value="2">Python</option>
                    <option value="3">DevOps</option>
                    <option value="4">WebUI</option>
                    <option value="5">Ruby</option>
                </select>
            </div>
        </td>
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
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="City user" aria-label="City user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Address user" aria-label="Address user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Phone number user" aria-label="Phone number user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="E-mail user" aria-label="E-mail user" aria-describedby="basic-addon1">
            </div>
        </td>
    </tr>
    </tbody>
</table>
<h3 align="center">Jobs</h3>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Company</th>
        <th scope="col">Position</th>
        <th scope="col">From</th>
        <th scope="col">To</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Company user" aria-label="Company user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Position user" aria-label="Position user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="From user" aria-label="From user" aria-describedby="basic-addon1">
            </div>
        </td>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="To user" aria-label="To user" aria-describedby="basic-addon1">
            </div>
        </td>
    </tr>
    </tbody>
</table>
<h3 align="center">Professional skills</h3>
<table class="table table-striped table-bordered table-hover">
    <tbody>
    <tr>
        <td>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Skills user" aria-label="Skills user" aria-describedby="basic-addon1">
            </div>
        </td>
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
