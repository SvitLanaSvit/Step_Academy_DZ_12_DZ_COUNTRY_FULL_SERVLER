<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 20.10.2023
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="allCountries" />
                        <input type="submit" class="nav-link" value="ALL COUNTRIES">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="allCitiesOfCountry" />
                        <input type="submit" class="nav-link" value="ALL CITIES OF COUNTRY">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="allCapital" />
                        <input type="submit" class="nav-link" value="ALL CAPITAL">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="capitalOfCountry" />
                        <input type="submit" class="nav-link" value="CAPITAL OF COUNTRY">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="admin" />
                        <input type="submit" class="nav-link" value="ADMIN">
                    </form>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto" id="connect">
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="connect" />
                        <input type="submit" class="nav-link" value="Connect to DB">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="countries" method="post">
                        <input type="hidden" name="param" value="insert" />
                        <input type="submit" class="nav-link" value="Insert data into DB" <c:if test="${countriesSize > 0}">disabled</c:if>>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container" style="margin-top: 20px">
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success">${message}</div>
            <script>
                setTimeout(function(){
                    document.querySelector(".alert-success").style.display = "none"
                }, 3000);
            </script>
        </c:if>
        <div class="alert alert-primary" >
            <form action="countries" method="post">
                <input type="hidden" name="param" value="countriesMostCities" />
                <input type="submit" class="btn btn-outline-primary"
                       value="Show the three countries with the most cities">
            </form>
            <form action="countries" method="post">
                <input type="hidden" name="param" value="countriesMostPopulation" />
                <input type="submit" class="btn btn-outline-primary"
                       value="The three countries with the largest number of inhabitants">
            </form>
            <form action="countries" method="post">
                <input type="hidden" name="param" value="countriesLessPopulation" />
                <input type="submit" class="btn btn-outline-primary"
                       value="The three countries with the smallest number of inhabitants">
            </form>
        </div>
        <div class="alert alert-warning" >
            <form action="countries" method="post">
                <input type="hidden" name="param" value="sameCitiesCountries" />
                <input type="submit" class="btn btn-outline-warning"
                       value="Show the number of cities with the same name in different countries">
            </form>
            <form action="countries" method="post">
                <input type="hidden" name="param" value="uniqueSameCitiesCountries" />
                <input type="submit" class="btn btn-outline-warning"
                       value="Show unique city names from different countries">
            </form>
            <form action="countries" method="post">
                <input type="hidden" name="param" value="countriesNumberRangeCities" />
                <input type="submit" class="btn btn-outline-warning"
                       value="Show countries with the number of cities in the specified range">
            </form>
        </div>
    </div>
</body>
</html>