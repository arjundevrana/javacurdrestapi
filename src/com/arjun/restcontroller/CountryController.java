package com.arjun.restcontroller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
import com.arjun.service.CountryService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;




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

    @POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "E:/arjun/image/"
				+ fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
