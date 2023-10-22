<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 22.10.2023
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<style>
    .input_button_blue, .input_button_yellow{
        border: none;
        background-color: transparent;
    }

    .input_button_blue:hover{
        color: #013682;
        cursor: pointer;
    }

    .input_button_yellow:hover{
        color: #ffc107;
        cursor: pointer;
    }

</style>
<body>
    <div class="container" style="margin-top: 20px">
        <h1>ADMIN</h1>
        <div class="alert alert-primary" >
            <form action="admin" method="post">
                <input type="hidden" name="param" value="countriesAdmin" />
                <input class="input_button_blue" type="submit" value="Countries"/>
            </form>
        </div>
        <div class="alert alert-warning">
            <form action="admin" method="post">
                <input type="hidden" name="param" value="citiesAdmin" />
                <input class="input_button_yellow" type="submit" value="Cities"/>
            </form>
        </div>
        <div>
            <a href="countries" class="btn btn-outline-secondary">BACK</a>
        </div>
    </div>
</body>
</html>
