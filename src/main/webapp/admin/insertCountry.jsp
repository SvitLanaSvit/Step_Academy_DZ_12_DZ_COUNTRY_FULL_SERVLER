<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert country</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <form action="admin" method="post">
            <input type="hidden" name="param" value="saveCountry">
            <div class="mb-3 w-25">
                <label for="nameCountry" class="form-label">Name country:</label>
                <input type="text" class="form-control" id="nameCountry" name="nameCountry" required>
            </div>
            <div class="mb-3 w-25">
                <label for="population" class="form-label">Population:</label>
                <input type="number" class="form-control" id="population" name="population" required>
            </div>
            <div class="btn-group">
                <input type="submit" class="btn btn-outline-success" value="SAVE"/>
<%--                <button class="btn btn-outline-secondary" onclick="">BACK</button>--%>
            </div>
        </form>
    </div>
</body>
</html>
<%--<script>--%>
<%--    function goBack(){--%>
<%--        window.history.back();--%>
<%--    }--%>
<%--</script>--%>
