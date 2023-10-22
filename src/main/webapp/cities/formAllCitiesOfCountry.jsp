<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 21.10.2023
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All cities of country</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form action="cities" method="post">
            <div class="mb-3 w-25">
                <label for="countryId">Select a Country:</label>
                <select class="form-control" id="countryId" name="countryId" style="margin-top: 20px">
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.id}">${country.name_country}</option>
                    </c:forEach>
                </select>
                <input type="submit" class="btn btn-success" value="Show all cities by country" style="margin-top: 20px">
            </div>
        </form>
        <div>
            <a href="countries" class="btn btn-outline-secondary">BACK</a>
        </div>
    </div>
</body>
</html>
