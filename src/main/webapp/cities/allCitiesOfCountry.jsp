<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 21.10.2023
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All cities by country</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>${cities[0].name_country}</h1>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>NAME CITY</th>
                <th>IS CAPITAL</th>
                <th>INFORMATION</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cities}" var="city">
                <tr>
                    <td>${city.id}</td>
                    <td>${city.name_city}</td>
                    <td>${city.capital}</td>
                    <td>${city.information}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a href="countries" class="btn btn-outline-secondary">BACK</a>
        </div>
    </div>
</body>
</html>
