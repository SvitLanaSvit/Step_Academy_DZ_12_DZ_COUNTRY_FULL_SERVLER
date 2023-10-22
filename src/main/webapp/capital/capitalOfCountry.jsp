<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 21.10.2023
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Capital of country</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
  <div class="container">
    <h1>${capital.name_country}</h1>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>CAPITAL</th>
      </tr>
      </thead>
      <tbody>
        <tr>
          <td>${capital.id}</td>
          <td>${capital.name_capital}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <a href="countries" class="btn btn-outline-secondary">BACK</a>
    </div>
  </div>
</body>
</html>
