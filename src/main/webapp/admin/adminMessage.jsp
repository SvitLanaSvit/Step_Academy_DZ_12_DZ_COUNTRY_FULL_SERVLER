<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success">${message}</div>
            <script>
                setTimeout(function(){
                    document.querySelector(".alert-success").style.display = "none"
                }, 3000);
            </script>
        </c:if>
        <div>
            <form action="admin" method="post">
                <input type="hidden" name="param" value="back" />
                <input class="btn btn-outline-secondary" type="submit" value="BACK"/>
            </form>
        </div>
    </div>
</body>
</html>
