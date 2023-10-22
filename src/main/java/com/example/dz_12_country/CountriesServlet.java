package com.example.dz_12_country;

import com.example.dz_12_country.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "countriesServlet", value = "/countries")
public class CountriesServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/countries", "bestuser", "bestuser");
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Country> countries = getAllCountries();
        request.setAttribute("countriesSize", countries.size());
        getServletContext().getRequestDispatcher("/countries.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String paramFromForm = request.getParameter("param");

        if("connect".equals(paramFromForm)){
            connect();
            String message = "Connect was success";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/countries.jsp").forward(request, response);
        }else if("insert".equals(paramFromForm)){
            insertToDB();
            String message = "Inserting was success";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/countries.jsp").forward(request, response);
        }else if("allCountries".equals(paramFromForm)){
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/allCountries.jsp").forward(request, response);
        }else if("allCitiesOfCountry".equals(paramFromForm)){
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/cities/formAllCitiesOfCountry.jsp").forward(request,response);
        }else if("allCapital".equals(paramFromForm)){
            List<Capital> capitals = getAllCapital();
            request.setAttribute("capitals", capitals);
            getServletContext().getRequestDispatcher("/allCapital.jsp").forward(request,response);
        }else if("capitalOfCountry".equals(paramFromForm)){
            List<Country> countries = getAllCountries();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/capital/formCapitalOfCountry.jsp").forward(request,response);
        }else if("countriesMostCities".equals(paramFromForm)){
            List<CountryMostCity> countryMostCities = getCountriesMostCities();
            request.setAttribute("countries", countryMostCities);
            getServletContext().getRequestDispatcher("/countriesMostCities.jsp").forward(request, response);
        }else if("countriesMostPopulation".equals(paramFromForm)){
            List<Country> countries = getCountriesMostPopulation();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/allCountries.jsp").forward(request,response);
        }else if("countriesLessPopulation".equals(paramFromForm)){
            List<Country> countries = getCountriesLessPopulation();
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/allCountries.jsp").forward(request,response);
        } else if("sameCitiesCountries".equals(paramFromForm)){
            List<SameCity> sameCities = getSameCitiesOfAnotherCountries();
            request.setAttribute("cities", sameCities);
            getServletContext().getRequestDispatcher("/sameCities.jsp").forward(request,response);
        }else if("uniqueSameCitiesCountries".equals(paramFromForm)){
            List<UniqueSameCity> sameCities = getUniqueSameCityOfAnotherCountry();
            request.setAttribute("cities", sameCities);
            getServletContext().getRequestDispatcher("/uniqueSameCities.jsp").forward(request,response);
        }else if("countriesNumberRangeCities".equals(paramFromForm)){
            getServletContext().getRequestDispatcher("/formRangeCities.jsp").forward(request,response);
        } else if("rangeCities".equals(paramFromForm)){
            String min = request.getParameter("minCount");
            String max = request.getParameter("maxCount");

            int minInt = Integer.parseInt(min);
            int maxInt = Integer.parseInt(max);
            if(minInt > maxInt){
                int temp = minInt;
                minInt = maxInt;
                maxInt = temp;
            }

            List<CountryCountCities> countries = getCountriesByRangeOfCities(minInt, maxInt);
            request.setAttribute("countries", countries);
            getServletContext().getRequestDispatcher("/countriesByRangeCities.jsp").forward(request, response);

        }else if("admin".equals(paramFromForm)){
            getServletContext().getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }
    }

    private void connect(){
        String queryCountries = "CREATE TABLE IF NOT exists Countries" +
                "(id int auto_increment primary key not null, " +
                "name_country nvarchar(250) not null, " +
                "population int not null);";

        String queryCities = "CREATE TABLE IF NOT exists BigCities(" +
                "id int auto_increment primary key not null, " +
                "country_id int not null, " +
                "name_city nvarchar(250) not null, " +
                "is_capital bit not null, " +
                "information text not null, " +
                "FOREIGN KEY (country_id) references countries(id));";

        try {
            statement.executeUpdate(queryCountries);
            statement.executeUpdate(queryCities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertToDB(){
        String queryCountries = "INSERT INTO Countries (name_country, population)" +
                "VALUES" +
                "('United States', 331002651), " +
                "('China', 1439323776), " +
                "('India', 1380004385), " +
                "('Brazil', 212559417), " +
                "('Mexico', 128932753), " +
                "('Indonesia', 273523615), " +
                "('Pakistan', 220892340), " +
                "('Nigeria', 206139587), " +
                "('Bangladesh', 164689383), " +
                "('Japan', 126476461), " +
                "('Egypt', 102334404), " +
                "('Germany', 83783942), " +
                "('Turkey', 84339067), " +
                "('France', 65273511), " +
                "('United Kingdom', 67886011), " +
                "('Italy', 60461826), " +
                "('South Africa', 59308690), " +
                "('South Korea', 51269185), " +
                "('Argentina', 45195777);";

        String queryCities = "INSERT INTO BigCities (country_id, name_city, is_capital, information)\n" +
                "VALUES (1, 'New York City', 1, 'The largest city in the United States.'), " +
                "(2, 'Shanghai', 0, 'One of China is major economic and cultural centers.'), " +
                "(3, 'Mumbai', 0, 'India is most populous city and a financial hub.'), " +
                "(4, 'Sao Paulo', 0, 'The largest city in Brazil and a major business center.'), " +
                "(5, 'Mexico City', 1, 'The capital and largest city of Mexico.'), " +
                "(6, 'Jakarta', 1, 'The capital and largest city of Indonesia.'), " +
                "(7, 'Karachi', 1, 'The largest city in Pakistan.'), " +
                "(8, 'Lagos', 0, 'Nigeria is largest city and economic hub.'), " +
                "(9, 'Dhaka', 1, 'The capital and largest city of Bangladesh.'), " +
                "(10, 'Tokyo', 1, 'The capital and largest city of Japan.'), " +
                "(11, 'Cairo', 1, 'The capital and largest city of Egypt.'), " +
                "(12, 'Berlin', 1, 'The capital and largest city of Germany.'), " +
                "(13, 'Istanbul', 1, 'A major city in Turkey, connecting Europe and Asia.'), " +
                "(14, 'Paris', 1, 'The capital and largest city of France.'), " +
                "(15, 'London', 1, 'The capital and largest city of the United Kingdom.'), " +
                "(16, 'Rome', 1, 'The capital and largest city of Italy.'), " +
                "(17, 'Johannesburg', 0, 'A major city in South Africa.'), " +
                "(18, 'Seoul', 1, 'The capital and largest city of South Korea.'), " +
                "(19, 'Buenos Aires', 1, 'The capital and largest city of Argentina.')," +
                "(1, 'Los Angeles', 0, 'A major city in the United States known for its entertainment industry.')," +
                "(1, 'Chicago', 0, 'A major city in the United States with a rich cultural heritage.'), " +
                "(2, 'Beijing', 1, 'The capital and one of the largest cities in China.'), " +
                "(2, 'Shenzhen', 0, 'A major Chinese city known for its rapid economic growth.'), " +
                "(3, 'Delhi', 0, 'A major city in India known for its historical significance.'), " +
                "(3, 'Kolkata', 0, 'A significant cultural and economic center in India.'), " +
                "(4, 'Rio de Janeiro', 0, 'A famous city in Brazil known for its beautiful beaches.')," +
                "(4, 'Brasília', 1, 'The capital city of Brazil and a planned urban center.'), " +
                "(5, 'Guadalajara', 0, 'A significant city in Mexico with a rich cultural scene.'), " +
                "(5, 'Monterrey', 0, 'A major industrial city in Mexico.'), " +
                "(6, 'Surabaya', 0, 'A significant city in Indonesia with a busy port.'), " +
                "(6, 'Bandung', 0, 'A major city in Indonesia known for its universities and tech industry.'), " +
                "(7, 'Lahore', 0, 'A cultural and historical city in Pakistan.'), " +
                "(7, 'Faisalabad', 0, 'A major industrial city in Pakistan.'), " +
                "(8, 'Kano', 0, 'A major city in Nigeria with a rich history.'), " +
                "(8, 'Ibadan', 0, 'A significant city in Nigeria known for its universities.'), " +
                "(9, 'Chittagong', 0, 'A major port city in Bangladesh.'), " +
                "(9, 'Khulna', 0, 'A significant city in Bangladesh known for its industry.');";

        try {
            statement.executeUpdate(queryCountries);
            statement.executeUpdate(queryCities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    private List<Capital> getAllCapital(){
        String query = "SELECT id, name_city FROM bigcities WHERE is_capital = true;";
        List<Capital> capitals = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Capital capital = new Capital();
                capital.setId(resultSet.getInt("id"));
                capital.setName_capital(resultSet.getString("name_city"));

                capitals.add(capital);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return capitals;
    }
    private List<CountryMostCity> getCountriesMostCities(){
        String query = "SELECT c.name_country, count(name_city) as count FROM countries c " +
                "LEFT JOIN bigcities bc ON bc.country_id = c.id " +
                "group by c.name_country " +
                "order by count DESC " +
                "limit 3;";
        List<CountryMostCity> countryMostCities = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                CountryMostCity countryMostCity = new CountryMostCity();
                countryMostCity.setName_country(resultSet.getString("name_country"));
                countryMostCity.setCount_cities(resultSet.getInt("count"));

                countryMostCities.add(countryMostCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryMostCities;
    }
    private List<Country> getCountriesMostPopulation(){
        String query = "SELECT * FROM countries " +
                "order by population desc " +
                "limit 3;";
        return getCountries(query);
    }
    private List<Country> getCountriesLessPopulation(){
        String query = "SELECT * FROM countries " +
                "order by population " +
                "limit 3;";
        return getCountries(query);
    }
    private List<Country> getCountries(String query){
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName_country(resultSet.getString("name_country"));
                country.setPopulation(resultSet.getInt("population"));

                countries.add(country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }
    private List<SameCity> getSameCitiesOfAnotherCountries(){
        String query = "SELECT name_city, COUNT(DISTINCT country_id) AS num_countries FROM BigCities " +
                "GROUP BY name_city " +
                "HAVING COUNT(DISTINCT country_id) > 1;";
        List<SameCity> sameCities = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                SameCity sameCity = new SameCity();
                sameCity.setName_city(resultSet.getString("name_city"));
                sameCity.setCount_countries(resultSet.getInt("num_countries"));

                sameCities.add(sameCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  sameCities;
    }
    private List<UniqueSameCity> getUniqueSameCityOfAnotherCountry(){
        String query = "SELECT name_city " +
                "FROM BigCities " +
                "GROUP BY name_city " +
                "HAVING COUNT(DISTINCT country_id) > 1;";
        List<UniqueSameCity> sameCities = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                UniqueSameCity sameCity = new UniqueSameCity();
                sameCity.setName_city(resultSet.getString("name_city"));

                sameCities.add(sameCity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  sameCities;
    }
    private List<CountryCountCities> getCountriesByRangeOfCities(int min, int max){
        String query = "SELECT c.id AS country_id, c.name_country AS country_name, COUNT(bc.id) AS city_count " +
                "FROM Countries c " +
                "LEFT JOIN BigCities bc ON c.id = bc.country_id " +
                "GROUP BY c.id, c.name_country " +
                "HAVING city_count >= ? AND city_count <= ?";
        List<CountryCountCities> countryCountCitiesList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CountryCountCities countryCountCities = new CountryCountCities();
                countryCountCities.setCountry_id(resultSet.getInt("country_id"));
                countryCountCities.setCountry_name(resultSet.getString("country_name"));
                countryCountCities.setCity_count(resultSet.getInt("city_count"));

                countryCountCitiesList.add(countryCountCities);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryCountCitiesList;
    }
}
