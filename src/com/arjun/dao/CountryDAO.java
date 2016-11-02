package com.arjun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.arjun.bean.Country;
/**
 * 
 * @author Arjun Singh
 *
 */
public class CountryDAO {
    private Connection connection;
/**
 * 
 * @param connection This is parameter constructor.It will take Connection type object as parameter.
 */
    public CountryDAO(final Connection connection) {
        this.connection = connection;
    }
/**
 * 
 * @param country It take country type object as parameter.
 * @return It will return INT value according to insertion country in data base. 
 */
    public int saveCountry(final Country country) {
        PreparedStatement ps = null;
        int statusFlag = 0;
        try {
            ps = connection.prepareStatement("INSERT INTO country_info(country_id,country_name,country_population) values(?,?,?)");
            ps.setInt(1, country.getId());
            ps.setString(2, country.getCountryName());
            ps.setLong(3, country.getPopulation());
            statusFlag = ps.executeUpdate();
        } catch (Exception e) {

            System.out.println("Error in new country insertation +" + e);

        }
        return statusFlag;

    }
/**
 * 
 * @param countyID This method take country id type INT as parameter.
 * @return It will return true or false boolean type if Country have already then will return true else it will return false.  
 */
    public boolean checkCountryEntry(final int countyID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean statusFlag = false;
        try {
            String query = "SELECT country_name from country_info WHERE country_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, countyID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statusFlag = true;
            }
        } catch (Exception e) {
            System.out.println(" Error in country check " + e);
        }
        return statusFlag;

    }
/**
 *  
 * @param countyID it will take countryID parameter as INT type.
 * @return It will return Country type object by country ID. It may be return null if country id dose not exist.
 */
    public Country getCountryByID( final int countyID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Country country = new Country();
        try {
            String query = "SELECT * from country_info WHERE country_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, countyID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.setId(resultSet.getInt("country_id"));
                country.setCountryName(resultSet.getNString("country_name"));
                country.setPopulation(resultSet.getLong("country_population"));
            }
        } catch (Exception e) {
            System.out.println(" Error in country check " + e);
        }
        return country;

    }
/**
 * 
 * @return This method use to get all country in database. It may be return null if no record fount in database.
 */
    public List<Country> getALLCountry() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countryList = new ArrayList<>();
        try {
            String query = "SELECT * from country_info ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt("country_id"));
                country.setCountryName(resultSet.getNString("country_name"));
                country.setPopulation(resultSet.getLong("country_population"));
                countryList.add(country);
            }
        } catch (Exception e) {
            System.out.println(" Error in country check " + e);
        }
        return countryList;

    }
/**
 * 
 * @param country It will take Country object as parameter.
 * @return It will return INT type value may be 0 or 1 according update success or not.
 */
    public int updateCountry(final Country country) {
        PreparedStatement ps = null;
        int statusFlag = 0;
        try {
            ps = connection.prepareStatement("UPDATE country_info SET country_id=?,country_name=?,country_population=? WHERE country_id=? ");
            ps.setInt(1, country.getId());
            ps.setString(2, country.getCountryName());
            ps.setLong(3, country.getPopulation());
            ps.setInt(4, country.getId());
            statusFlag = ps.executeUpdate();
        } catch (Exception e) {

            System.out.println("Error in new country updation  +" + e);

        }
        return statusFlag;

    }
/**
 *  
 * @param countryID This method will take countryID as parameter.
 * @return It will return country object if record has successfully delete.
 */
    public Country deleteCountry(final int countryID) {
        PreparedStatement ps = null;
        int statusFlag = 0;
        Country country = null ;
        
        try {
            country =  getCountryByID(countryID);
            ps = connection.prepareStatement("DELETE FROM  country_info  WHERE country_id=? ");
            ps.setInt(1, countryID);
            statusFlag = ps.executeUpdate();
            if(statusFlag <1){
                country = null;
            }
        } catch (Exception e) {
            System.out.println("Error in new country deletion +" + e);

        }
        return country;
    }
}
