package com.example.dz_12_country;

import com.example.dz_12_country.entity.City;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "citiesServlet", value = "/cities")
public class CitiesServlet extends HttpServlet {
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
        getServletContext().getRequestDispatcher("/cities/formAllCitiesOfCountry.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String paramFromForm = request.getParameter("countryId");
        List<City> cities = new ArrayList<>();

        if (paramFromForm != null && !paramFromForm.isEmpty()){
            int countryId = Integer.parseInt(paramFromForm);
            cities = getCitiesByCountryId(countryId);
        }

        request.setAttribute("cities", cities);
        getServletContext().getRequestDispatcher("/cities/allCitiesOfCountry.jsp").forward(request,response);
    }

    private List<City> getCitiesByCountryId(int country_id){
        String query = "SELECT bc.id, c.name_country, bc.name_city, bc.is_capital, bc.information FROM bigcities bc " +
                "LEFT JOIN countries c ON c.id = bc.country_id " +
                "WHERE country_id=?;";
        List<City> cities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, country_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName_country(resultSet.getString("name_country"));
                city.setName_city(resultSet.getString("name_city"));
                city.set_capital(resultSet.getBoolean("is_capital"));
                city.setInformation(resultSet.getString("information"));

                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
