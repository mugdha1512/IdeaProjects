<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Employee List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { width: 80%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #f2f2f2; }
        a.button {
            padding: 6px 12px;
            text-decoration: none;
            background-color: #28a745;
            color: white;
            border-radius: 4px;
        }
        a.button:hover { background-color: #218838; }
        .delete { background-color: #dc3545; }
        .delete:hover { background-color: #c82333; }
    </style>
</head>
<body>

<h2>Employee List</h2>

<a href="${pageContext.request.contextPath}/employees/create" class="button">Add New Employee</a>

<br/><br/>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.salary}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employees/edit/${employee.id}" class="button">Edit</a>
                <a href="${pageContext.request.contextPath}/employees/delete/${employee.id}" class="button delete" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
