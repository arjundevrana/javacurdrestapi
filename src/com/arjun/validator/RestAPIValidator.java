package com.arjun.validator;

import java.util.ArrayList;
import java.util.List;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
import com.arjun.bean.Validator;
import com.arjun.dao.CountryDAO;
import com.arjun.util.ApplicationProperty;

public class RestAPIValidator {
    static APIResponse apiResponse = new APIResponse();
    static List<Country> countries = new ArrayList<>();
    static Validator validator = new Validator();
    static boolean statusFlag = true;




    public static Validator checkCountryDuplicate(Country country, CountryDAO countryDAO) {
        if (countryDAO.checkCountryEntry(country.getId())) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORCOUNTRYALLREADYEXIST);
            apiResponse.setCountry(countries);
            validator.setApiResponse(apiResponse);
            validator.setStatusFlag(true);
            return validator;
        }
        validator.setApiResponse(apiResponse);
        validator.setStatusFlag(false);
        return validator;
    }

    public static Validator checkCountryNull(Country country) {
        if (country == null) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORCOUNTRYRECORD);
            apiResponse.setCountry(countries);
            validator.setApiResponse(apiResponse);
            validator.setStatusFlag(true);
            return validator;
        }
        validator.setApiResponse(apiResponse);
        validator.setStatusFlag(false);
        return validator;
    }

    public static Validator checkCountryID(int countryID) {
        if (countryID < 0) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORCOUNTRYID);
            apiResponse.setCountry(countries);
            validator.setApiResponse(apiResponse);
            validator.setStatusFlag(true);
            return validator;
        }
        validator.setApiResponse(apiResponse);
        validator.setStatusFlag(false);
        return validator;

    }

    public static Validator checkCountryName(String countryName) {
        if (countryName == null || countryName.isEmpty()) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORCOUNTRYNAME);
            apiResponse.setCountry(countries);
            validator.setApiResponse(apiResponse);
            validator.setStatusFlag(true);
            return validator;
        }
        validator.setApiResponse(apiResponse);
        validator.setStatusFlag(false);
        return validator;
    }

    public static Validator checkCountryPopulation(long countryPopulation) {

        if (countryPopulation < 1) {
            apiResponse.setStatus(false);
            apiResponse.setMessage(ApplicationProperty.ERRORCOUNTRYPOPULATION);
            apiResponse.setCountry(countries);
            validator.setApiResponse(apiResponse);
            validator.setStatusFlag(true);
            return validator;
        }
        validator.setApiResponse(apiResponse);
        validator.setStatusFlag(false);
        return validator;
    }


}
