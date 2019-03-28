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
            width: 100%;
            margin: auto;
            max-width: 1400px;
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
<c:choose>
    <c:when test="${person!=null}">
        <h3 align="center">Update CV</h3>
    </c:when>
    <c:otherwise>
        <h3 align="center">Create new CV</h3>
    </c:otherwise>
</c:choose>

<form method="post" action="/person">
    <c:if test="${person!=null}">
        <input type="number" hidden name="personId" value="${person.id}">
    </c:if>
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
                    <input  value="${person.surname}" type="text" name="surname" id="surname" class="form-control" placeholder="Surname user" aria-label="Surname user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${person.name}" type="text"  name="name" id="name" class="form-control" placeholder="Name user" aria-label="Name user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${person.dateOfBirth}" type="text" name = "dateOfBirth" class="form-control" placeholder="uuuu-MM-dd" aria-label="Date of birth user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Options</label>
                    </div>
                    <select name = "specializ" class="custom-select" id="inputGroupSelect01">
                        <option selected>Choose...</option>
                        <c:forEach var="specialization" items="${specializations}">
                            <option value="${specialization.name}" <c:if test="${person.specialization == specialization}">selected</c:if>>${specialization.name}</option>
                        </c:forEach>
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
                    <input value="${person.contact.city}" type="text" name ="city" class="form-control" placeholder="City user" aria-label="City user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${person.contact.address}" type="text" name ="address" class="form-control" placeholder="Address user" aria-label="Address user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${person.contact.phoneNumber}" type="text" name ="phoneNumber" class="form-control" placeholder="Phone number user" aria-label="Phone number user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${person.contact.email}" type="text" name ="email" class="form-control" placeholder="E-mail user" aria-label="E-mail user" aria-describedby="basic-addon1">
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
        <div class="btn btn-info btn-sm" id="addNewJobs">Add job</div>
        <tbody id="jobTable">
        <c:if test="${empty person.companies}">
            <tr id="newJob_1">
                <td>
                    <div class="input-group mb-3">
                        <input value="${company.companyName}" type="text" name = "companyName0" class="form-control" placeholder="Company user" aria-label="Company user" aria-describedby="basic-addon1">
                    </div>
                </td>
                <td>
                    <div class="input-group mb-3">
                        <input value="${company.position}" type="text" name ="position0" class="form-control" placeholder="Position user" aria-label="Position user" aria-describedby="basic-addon1">
                    </div>
                </td>
                <td>
                    <div class="input-group mb-3">
                        <input value="${company.workedFrom}" type="text" name ="workedFrom0" class="form-control" placeholder="uuuu-MM-dd" aria-label="From user" aria-describedby="basic-addon1">
                    </div>
                </td>
                <td>
                    <div class="input-group mb-3">
                        <input value="${company.workedTill}" type="text" name ="workedTill0" class="form-control" placeholder="uuuu-MM-dd" aria-label="To user" aria-describedby="basic-addon1">
                    </div>
                </td>
            </tr>
        </c:if>
        <c:forEach var="company" items="${person.companies}" varStatus="loop">
        <tr id="newJob_1">
            <td>
                <div class="input-group mb-3">
                    <input value="${company.companyName}" type="text" name = "companyName${loop.index}" class="form-control" placeholder="Company user" aria-label="Company user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${company.position}" type="text" name ="position${loop.index}" class="form-control" placeholder="Position user" aria-label="Position user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${company.workedFrom}" type="text" name ="workedFrom${loop.index}" class="form-control" placeholder="uuuu-MM-dd" aria-label="From user" aria-describedby="basic-addon1">
                </div>
            </td>
            <td>
                <div class="input-group mb-3">
                    <input value="${company.workedTill}" type="text" name ="workedTill${loop.index}" class="form-control" placeholder="uuuu-MM-dd" aria-label="To user" aria-describedby="basic-addon1">
                </div>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3 align="center">Professional skills</h3>
    <table class="table table-striped table-bordered table-hover">
        <div class="btn btn-info btn-sm" id="addNewSkill">Add skill</div>
        <tbody id="skillTable">
        <c:if test="${empty person.skills}">
            <tr>
                <td>
                    <div class="input-group mb-1">
                        <input value="${skill}"type="text" name="skill" class="form-control" placeholder="Skills user" aria-label="Skills user" aria-describedby="basic-addon1">
                    </div>
                </td>
            </tr>
        </c:if>
        <c:forEach var="skill" items="${person.skills}" varStatus="loop">
    <tr>
            <td>
                <div class="input-group mb-1">
                    <input value="${skill}"type="text" name="skill${loop.index}" class="form-control" placeholder="Skills user" aria-label="Skills user" aria-describedby="basic-addon1">
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
    <div>
        <input type="submit" name="submit" value="Submit" class="btn btn-info">
    </div>

</form>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
    window.addEventListener("load", init, false);
    var skill_index = 2;
    var job_index = 2;
    function init () {
        console.log('init');console.log('init');
        addNewSkill.addEventListener("click", addSkill, false);
        addNewJobs.addEventListener("click", addJob, false);
        // closeRowSkill.addEventListener("click", closeSkill, false);

    }

    function closeSkill(elementIdToRemove) {
        console.log('closeSkill() init');
        document.getElementById(elementIdToRemove).remove();
    }

    function addSkill () {

        console.log("add new skill table row");

        var trNode = document.createElement('TR');
        trNode.id = "rowSkill" + skill_index;
        var divNode = document.createElement("div");
        divNode.className = "input-group mb-1";
        var tdNode = document.createElement('TD');
        var tdNode1 = document.createElement('TD');
        var input = document.createElement("input");
        var closeButton = document.createElement("button");
        closeButton.className = "close";
        closeButton.type = "button";
        closeButton.setAttribute("onclick", "document.getElementById('rowSkill" + skill_index + "' ).remove()");

        var closeText = document.createTextNode("x");
        closeButton.appendChild(closeText);

        input.type = "text";
        input.className = "form-control";
        input.name = "skill" + skill_index;
        input.placeholder = "Skills user";
        input.style.width = "800px";

        skill_index++;
        divNode.appendChild(input);
        tdNode.appendChild(divNode);
        tdNode1.appendChild(closeButton);
        trNode.appendChild(tdNode);
        trNode.appendChild(tdNode1);
        document.getElementById("skillTable").appendChild(trNode);
    }

    // <button type="button" id="closeRowSkill" class="close" aria-label="Close" >
    //     <span>&times;</span>
    // </button>




    function addJob () {

        console.log("add new job table row");

        var trNode = document.createElement('TR');

        var tdNode = document.createElement('TD');
        var divNode = document.createElement("div");
        divNode.className = "input-group mb-3";
        var input = document.createElement("input");
        input.type = "text";
        input.className = "form-control";
        input.name = "companyName" + job_index;
        input.placeholder = "Company user";

        var tdNode2 = document.createElement('TD');
        var divNode2 = document.createElement("div");
        divNode2.className = "input-group mb-3";
        var input2 = document.createElement("input");
        input2.type = "text";
        input2.className = "form-control";
        input2.name = "position" + job_index;
        input2.placeholder = "Position user";

        var tdNode3 = document.createElement('TD');
        var divNode3 = document.createElement("div");
        divNode3.className = "input-group mb-3";
        var input3 = document.createElement("input");
        input3.type = "text";
        input3.className = "form-control";
        input3.name = "workedFrom" + job_index;
        input3.placeholder = "From user";

        var tdNode4 = document.createElement('TD');
        var divNode4 = document.createElement("div");
        divNode4.className = "input-group mb-3";
        var input4 = document.createElement("input");
        input4.type = "text";
        input4.className = "form-control";
        input4.name = "workedTill" + job_index;
        input4.placeholder = "To user";

        job_index++;

        divNode4.appendChild(input4);
        tdNode4.appendChild(divNode4);
        divNode3.appendChild(input3);
        tdNode3.appendChild(divNode3);
        divNode2.appendChild(input2);
        tdNode2.appendChild(divNode2);
        divNode.appendChild(input);
        tdNode.appendChild(divNode);
        trNode.appendChild(tdNode);
        trNode.appendChild(tdNode2);
        trNode.appendChild(tdNode3);
        trNode.appendChild(tdNode4);
        document.getElementById("jobTable").appendChild(trNode);
    }
</script>
</body>
</html>
