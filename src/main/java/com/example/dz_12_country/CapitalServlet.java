package com.example.dz_12_country;

import com.example.dz_12_country.entity.Capital;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "capitalServlet", value = "/capital")
public class CapitalServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/countries", "bestuser", "bestuser");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/capital/formCapitalOfCountry.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String paramFromForm = request.getParameter("countryId");

        if (paramFromForm != null && !paramFromForm.isEmpty()){
            int countryId = Integer.parseInt(paramFromForm);
            Capital capital = getCapitalByCountry(countryId);

            request.setAttribute("capital", capital);
            getServletContext().getRequestDispatcher("/capital/capitalOfCountry.jsp").forward(request, response);
        }
    }

    private Capital getCapitalByCountry(int countryId){
        String query = "SELECT bc.id, c.name_country, bc.name_city FROM bigcities bc " +
                "LEFT JOIN countries c ON c.id = bc.country_id " +
                "WHERE country_id = ? and is_capital = true;";
        Capital capital = new Capital();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, countryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                capital.setId(resultSet.getInt("id"));
                capital.setName_country(resultSet.getString("name_country"));
                capital.setName_capital(resultSet.getString("name_city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return capital;
    }
}
