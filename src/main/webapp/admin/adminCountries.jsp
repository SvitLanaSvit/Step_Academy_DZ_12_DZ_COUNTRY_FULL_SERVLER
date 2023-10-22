<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin countries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <h1>COUNTRIES</h1>
        <div>
            <form action="admin" method="post">
                <input type="hidden" name="param" value="insertCountry" />
                <input class="btn btn-outline-success" type="submit" value="New country">
            </form>
        </div>
        <table class="table table-striped table-hover">
            <thead class="sticky-sm-top">
            <tr>
                <th>ID</th>
                <th>NAME COUNTRY</th>
                <th>POPULATION</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${countries}" var="country">
                <tr>
                    <td>${country.id}</td>
                    <td>${country.name_country}</td>
                    <td>${country.population}</td>
                    <td>
                        <div style="display: flex; flex-wrap: wrap;">
                            <form id="deleteForm${country.id}" action="admin" method="post" style="margin-right: 10px">
                                <input type="hidden" name="param" value="deleteCountry" />
                                <input type="hidden" name="idDelete" value="${country.id}" />
                                <button class="btn btn-outline-danger" type="button" onclick="confirmDelete(${country.id})">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                    </svg>
                                </button>
                            </form>
                            <form action="admin" method="post">
                                <input type="hidden" name="param" value="updateCountry" />
                                <input type="hidden" name="idUpdate" value="${country.id}" />
                                <button class="btn btn-outline-warning" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <form action="admin" method="post">
                <input type="hidden" name="param" value="back" />
                <input class="btn btn-outline-secondary" type="submit" value="BACK"/>
            </form>
        </div>
    </div>
</body>
</html>
<script>
    function confirmDelete(id){
        if (confirm("Are you sure you want to delete this country by id = " + id + "?")) {
            // If the user confirms, submit the form to delete the country.
            const form = document.getElementById("deleteForm" + id);
            form.submit();
        }
    }
</script>
