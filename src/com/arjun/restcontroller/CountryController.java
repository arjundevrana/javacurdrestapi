package com.arjun.restcontroller;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
import com.arjun.service.CountryService;



@Path("/countries")
public class CountryController {

    CountryService countryService = new CountryService();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public APIResponse getCountries() {

        APIResponse listOfCountries = countryService.getAllCountries();
        return listOfCountries;
    }

    @GET
    @Path("/xml")
    @Produces({ MediaType.APPLICATION_XML })

    public APIResponse getCountriesXML() {

        APIResponse listOfCountries = countryService.getAllCountries();
        return listOfCountries;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getCountryById(@PathParam("id") int id) {
        return countryService.getCountry(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse addCountry(Country country) {
        return countryService.addCountry(country);
    }

    @POST
    @Path("/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public APIResponse addCountryXML(JAXBElement<Country> country) {
        Country country2 = country.getValue();
        return countryService.addCountry(country2);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse updateCountry(Country country) {
        return countryService.updateCountry(country);

    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse deleteCountry(@PathParam("id") int id) {
        return countryService.deleteCountry(id);

    }


}
