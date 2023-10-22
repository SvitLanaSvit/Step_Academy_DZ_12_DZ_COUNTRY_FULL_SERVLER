package com.example.dz_12_country;

import com.example.dz_12_country.entity.City;
import com.example.dz_12_country.entity.CityDB;
import com.example.dz_12_country.entity.Country;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/countries", "bestuser", "bestuser");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String paramFromForm = request.getParameter("param");

        if("countriesAdmin".equals(paramFromForm)){
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/admin/adminCountries.jsp").forward(request, response);
        }else if("insertCountry".equals(paramFromForm)){
            getServletContext().getRequestDispatcher("/admin/insertCountry.jsp").forward(request, response);
        }else if("saveCountry".equals(paramFromForm)){
            Country country = new Country();
            String id = request.getParameter("id");
            String nameCountry = request.getParameter("nameCountry");
            String population = request.getParameter("population");

            if(id != null && !id.isEmpty()){
                country.setId(Integer.parseInt(id));
            }

            country.setName_country(nameCountry);
            country.setPopulation(Integer.parseInt(population));
            saveCountry(country);

            String message = "The operation was successful.";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/admin/adminMessage.jsp").forward(request, response);
        }else if("deleteCountry".equals(paramFromForm)){
            String idDelete = request.getParameter("idDelete");
            deleteCountry(Integer.parseInt(idDelete));
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }else if("updateCountry".equals(paramFromForm)){
            String idUpdate = request.getParameter("idUpdate");
            Country country = getCountry(Integer.parseInt(idUpdate));
            request.setAttribute("country", country);
            getServletContext().getRequestDispatcher("/admin/updateCountry.jsp").forward(request, response);
        }else if("citiesAdmin".equals(paramFromForm)){
            List<City> cities = getAllCities();
            request.setAttribute("cities", cities);
            getServletContext().getRequestDispatcher("/admin/adminCities.jsp").forward(request, response);
        }else if("insertCities".equals(paramFromForm)){
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/admin/insertCity.jsp").forward(request, response);
        }else if("saveCity".equals(paramFromForm)) {
            CityDB city = new CityDB();
            String id = request.getParameter("id");
            String countryId = request.getParameter("countryId");
            String nameCity = request.getParameter("nameCity");
            String capital = request.getParameter("capital");
            String information = request.getParameter("information");

            if(id != null && !id.isEmpty()){
                city.setId(Integer.parseInt(id));
            }

            city.setCountry_id(Integer.parseInt(countryId));
            city.setName_city(nameCity);
            if("on".equals(capital)){
                city.set_capital(true);
            }else{
                city.set_capital(false);
            }
            city.setInformation(information);

            saveCity(city);

            String message = "The operation was successful.";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/admin/adminMessage.jsp").forward(request, response);
        }else if("deleteCity".equals(paramFromForm)){
            String idDelete = request.getParameter("idDelete");
            deleteCity(Integer.parseInt(idDelete));
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }else if("updateCity".equals(paramFromForm)){
            String idUpdate = request.getParameter("idUpdate");
            CityDB city = getCity(Integer.parseInt(idUpdate));
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            request.setAttribute("city", city);
            getServletContext().getRequestDispatcher("/admin/updateCity.jsp").forward(request, response);
        }
        else if("back".equals(paramFromForm)){
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }
    }

    private List<Country> getAllCountries(){
        String query = "SELECT * FROM COUNTRIES ORDER BY population DESC";
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Country country = new Country();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name_country");
                int population = resultSet.getInt("population");

                country.setId(id);
                country.setName_country(name);
                country.setPopulation(population);

                countries.add(country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }
    private void saveCountry(Country country){
        if(country.getId() == 0){
            String query = "INSERT INTO countries(name_country, population)VALUES(?, ?);";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, country.getName_country());
                preparedStatement.setInt(2, country.getPopulation());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            String query = "UPDATE countries SET name_country = ?, population = ? WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, country.getName_country());
                preparedStatement.setInt(2, country.getPopulation());
                preparedStatement.setInt(3, country.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void deleteCountry(int id){
        String query = "DELETE FROM countries WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Country getCountry(int id){
        String query = "SELECT * FROM countries WHERE id = ?";
        Country country = new Country();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                country.setId(id);
                country.setName_country(resultSet.getString("name_country"));
                country.setPopulation(resultSet.getInt("population"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return country;
    }
    private List<City> getAllCities(){
        String query = "SELECT bc.id, bc.name_city, bc.is_capital, bc.information, c.name_country FROM bigcities bc " +
                "LEFT JOIN countries c ON c.id = bc.country_id;";
        List<City> cities = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                City city = new City();

                city.setId(resultSet.getInt("id"));
                city.setName_city(resultSet.getString("name_city"));
                city.set_capital(resultSet.getBoolean("is_capital"));
                city.setInformation(resultSet.getString("information"));
                city.setName_country(resultSet.getString("name_country"));

                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
    private void saveCity(CityDB cityDB){
        if(cityDB.getId() == 0){
            String query = "INSERT INTO bigcities(country_id, name_city, is_capital, information)" +
                    "VALUES(?, ?, ?, ?);";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, cityDB.getCountry_id());
                preparedStatement.setString(2, cityDB.getName_city());
                preparedStatement.setBoolean(3, cityDB.getCapital());
                preparedStatement.setString(4, cityDB.getInformation());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            String query =
                    "UPDATE bigcities SET country_id = ?, name_city = ?, is_capital = ?, information = ? WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, cityDB.getCountry_id());
                preparedStatement.setString(2, cityDB.getName_city());
                preparedStatement.setBoolean(3, cityDB.getCapital());
                preparedStatement.setString(4, cityDB.getInformation());
                preparedStatement.setInt(5, cityDB.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void deleteCity(int id){
        String query = "DELETE FROM bigcities WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private CityDB getCity(int id){
        String query = "SELECT * FROM bigcities WHERE id = ?";
        CityDB cityDB = new CityDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cityDB.setId(id);
                cityDB.setCountry_id(resultSet.getInt("country_id"));
                cityDB.setName_city(resultSet.getString("name_city"));
                cityDB.set_capital(resultSet.getBoolean("is_capital"));
                cityDB.setInformation(resultSet.getString("information"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  cityDB;
    }
}
