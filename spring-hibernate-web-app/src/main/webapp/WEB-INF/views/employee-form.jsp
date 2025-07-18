<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employee Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { width: 400px; }
        label { display: block; margin-top: 15px; }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>${employee.id == null ? "Create New Employee" : "Edit Employee"}</h2>

<form action="${pageContext.request.contextPath}/employees/${employee.id == null ? 'save' : 'update'}" method="post">

    <input type="hidden" name="id" value="${employee.id}"/>

    <label for="name">Name:</label>
    <input type="text" name="name" id="name" value="${employee.name}" required/>

    <label for="salary">Salary:</label>
    <input type="number" name="salary" id="salary" value="${employee.salary}" step="0.01" required/>

    <input type="submit" value="Save"/>
</form>

</body>
</html>
