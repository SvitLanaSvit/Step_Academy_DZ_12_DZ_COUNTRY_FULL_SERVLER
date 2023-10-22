<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert city</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <form action="admin" method="post">
            <input type="hidden" name="param" value="saveCity">
            <div class="mb-3 w-25">
                <label for="countryId">Select a Country:</label>
                <select class="form-control" id="countryId" name="countryId" required>
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.id}">${country.name_country}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3 w-25">
                <label for="nameCity" class="form-label">Name city:</label>
                <input type="text" class="form-control" id="nameCity" name="nameCity" required>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="capital" name="capital">
                <label class="form-check-label" for="capital">Is city a capital?</label>
            </div>
            <div class="mb-3 w-25">
                <label for="information" class="form-label">Information:</label>
<%--                <input type="text" class="form-control" id="information" name="information" required>--%>
                <textarea class="form-control" id="information" name="information" rows="4" required></textarea>
            </div>
            <div class="btn-group">
                <input type="submit" class="btn btn-outline-success" value="SAVE"/>
                <%--                <button class="btn btn-outline-secondary" onclick="">BACK</button>--%>
            </div>
        </form>
    </div>
</body>
</html>
