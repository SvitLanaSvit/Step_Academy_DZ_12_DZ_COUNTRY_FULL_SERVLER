<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 21.10.2023
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<html>
<head>
    <title>All capital</title>
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <h1>ALL CAPITALS FROM DB</h1>
        <table class="table table-striped table-hover">
            <thead class="sticky-sm-top">
            <tr>
                <th>ID</th>
                <th>NAME CAPITAL</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${capitals}" var="capital">
                <tr>
                    <td>${capital.id}</td>
                    <td>${capital.name_capital}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="countries" class="btn btn-outline-secondary">BACK</a>
    </div>
</body>
</html>
