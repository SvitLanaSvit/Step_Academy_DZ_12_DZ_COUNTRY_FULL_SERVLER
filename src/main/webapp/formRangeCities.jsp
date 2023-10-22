<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form range cities</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <form action="countries" method="post">
            <input type="hidden" name="param" value="rangeCities">
            <div class="mb-3 w-25">
                <label for="minCount" class="form-label">Min count of cities:</label>
                <input type="number" class="form-control" name="minCount" id="minCount" value="1" min="1" required>
            </div>
            <div class="mb-3 w-25">
                <label for="maxCount" class="form-label">Max count of cities:</label>
                <input type="number" class="form-control" name="maxCount" id="maxCount" value="1" min="1" required>
            </div>
            <button type="submit" class="btn btn-outline-primary">Submit</button>
        </form>
    </div>
</body>
</html>
