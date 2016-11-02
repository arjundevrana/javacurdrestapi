package com.arjun.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
import com.arjun.dao.CountryDAO;
import com.arjun.util.ApplicationProperty;
import com.arjun.util.JDBCConnection;
import com.arjun.validator.RestAPIValidator;

public class CountryService {

    /**
     * @author ARJUN SINGH
     * @return This method return  APIResponse type including with list of all country.
     */
    public APIResponse getAllCountries() {
        APIResponse apiResponse = new APIResponse();
        Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME, ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
        CountryDAO countryDAO = new CountryDAO(connection);
        if (countryDAO.getALLCountry() == null || countryDAO.getALLCountry().isEmpty()) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.EXPGETCOUNTRY);
            apiResponse.setCountry(new ArrayList<Country>());
            return apiResponse;
        }
        apiResponse.setStatus(true);
        apiResponse.setMessage(ApplicationProperty.SUCCESSME);
        apiResponse.setCountry(countryDAO.getALLCountry());
        ;
        return apiResponse;
    }
    /**
     * 
     * @param id it take id as integer parameter 
     * @return This method will return APIResponse with country, get by country ID.
     */
    public APIResponse getCountry(final int id) {
        APIResponse apiResponse = new APIResponse();
        Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME, ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
        CountryDAO countryDAO = new CountryDAO(connection);
        Country country = null;
        country = countryDAO.getCountryByID(id);
        List< Country> countries = new ArrayList<>();
        countries.add(country);
        if(country == null){
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.EXPGETCOUNTRY);
            apiResponse.setCountry(countries);  
        }else{
            apiResponse.setStatus(true);
            apiResponse.setMessage(ApplicationProperty.SUCCESSME);
            apiResponse.setCountry(countries);
        }
        return apiResponse;
    }
    /**
     * 
     * @param country It take country type object as a parameter
     * @return Return country with successful status in case recored insertion is success.
     */
    public APIResponse addCountry( final Country country) {
        APIResponse apiResponse = new APIResponse();
        Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME, ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
        CountryDAO countryDAO = new CountryDAO(connection);
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        if (RestAPIValidator.checkCountryNull(country).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryNull(country).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryDuplicate(country, countryDAO).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryDuplicate(country, countryDAO).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryID(country.getId()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryID(country.getId()).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryName(country.getCountryName()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryName(country.getCountryName()).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryPopulation(country.getPopulation()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryPopulation(country.getPopulation()).getApiResponse();
            return apiResponse;
        }
        if (countryDAO.saveCountry(country) < 1) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.EXPADDCOUNTRY);
            apiResponse.setCountry(countries);
            return apiResponse;

        }
        apiResponse.setStatus(true);
        apiResponse.setMessage(ApplicationProperty.SUCADDCOUNTRY);
        apiResponse.setCountry(countries);
        return apiResponse;
    }
   /**
    * 
    * @param country It take Country type object as parameter
    * @return It will return APIResponse with country object if country is successfully update.
    */
    public APIResponse updateCountry( final Country country) {
        APIResponse apiResponse = new APIResponse();
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME, ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
        CountryDAO countryDAO = new CountryDAO(connection);
        if (RestAPIValidator.checkCountryNull(country).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryNull(country).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryID(country.getId()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryID(country.getId()).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryName(country.getCountryName()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryName(country.getCountryName()).getApiResponse();
            return apiResponse;
        }
        if (RestAPIValidator.checkCountryPopulation(country.getPopulation()).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryPopulation(country.getPopulation()).getApiResponse();
            return apiResponse;
        }
        if (countryDAO.updateCountry(country) > 0) {
            apiResponse.setStatus(true);
            apiResponse.setMessage(ApplicationProperty.SUCUPDATECOUNTRY);
            apiResponse.setCountry(countries);
        } else {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORPDATECOUNTRY);
            apiResponse.setCountry(countries);
        }
        return apiResponse;

    }
    /**
     * 
     * @param countryID It will take country id as parameter.
     * @return It will return country object after successfully deletion. 
     */
    public APIResponse deleteCountry(final int countryID) {
        APIResponse apiResponse = new APIResponse();
        Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME, ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
        CountryDAO countryDAO = new CountryDAO(connection);
        Country country = null;
        List<Country> countries = new ArrayList<>();
        if (RestAPIValidator.checkCountryID(countryID).isStatusFlag()) {
            apiResponse = RestAPIValidator.checkCountryID(countryID).getApiResponse();
            return apiResponse;
        }
        country = countryDAO.deleteCountry(countryID);
        if (country != null) {
            apiResponse.setStatus(true);
            apiResponse.setMessage(ApplicationProperty.SUCCDELETECOUNTRY);
            countries.add(country);
            apiResponse.setCountry(countries);
        } else {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORDELETECOUNTRY);
            apiResponse.setCountry(countries);
        }

        return apiResponse;
    }

}
