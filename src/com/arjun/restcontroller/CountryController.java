package com.arjun.restcontroller;
/**
 * 
 * @author ARJUN SINGH 
 *
 */

import java.io.BufferedOutputStream;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBElement;

import org.apache.commons.io.IOUtils;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
import com.arjun.bean.ImageInfo;
import com.arjun.bean.ImageInfoResponce;
import com.arjun.service.CountryService;
import com.arjun.service.ImageInfoService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;




@Path("/countries")
public class CountryController {
	private static final boolean IS_CHUNKED = true;
    CountryService countryService = new CountryService();
	ImageInfoService imageInfoservice =  new ImageInfoService();

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
	public ImageInfoResponce uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
    	ImageInfo imageInfo = new ImageInfo();
    	imageInfo.setIamgeId(2);
    	byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
    	imageInfo.setFileInput(bytes);
    	ImageInfoResponce imageInfoResponce=	imageInfoservice.saveImage(imageInfo);
    
				return imageInfoResponce;

		/*String uploadedFileLocation = "E:/arjun/image/"
				+ fileDetail.getFileName();
*/
		// save it
		//writeToFile(uploadedInputStream, uploadedFileLocation);
		/*byte[] inputFile;
	
		try {
			inputFile =  IOUtils.toByteArray(uploadedInputStream);
			byte[] base64EncodedData = Base64.encodeBase64(inputFile, IS_CHUNKED);
			byte[] base64DcodedData=Base64.decodeBase64(base64EncodedData);
			writeByteArraysToFile(uploadedFileLocation, base64EncodedData);
			writeByteArraysToFile("E:/arjun/image/test/"+fileDetail.getFileName(), base64DcodedData);
			System.out.println(base64EncodedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convert base64 Decode
		
		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();*/

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
	/**
     * This method writes byte array content into a file.
     *
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeByteArraysToFile(String fileName, byte[] content) throws IOException {
 
        File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
 
    }
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile() {
        File file = new File("E:/arjun/image/LoginForm.zip");
        ResponseBuilder response = Response.ok((Object) file);
        String headerContent ="attachment; filename="+file.getName(); 
        response.header("Content-Disposition", headerContent);
        return response.build();

    }
    @GET
    @Path("file/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public ImageInfoResponce getUserImage(@PathParam("id") int id) {
        return imageInfoservice.getImageInfo(id);
    }
    
    @GET
    @Path("video")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response video() {
        File file = new File("C:/Users/NIC/Videos/android.mp4");
        ResponseBuilder response = Response.ok((Object) file);
        String headerContent ="attachment; filename="+file.getName(); 
        response.header("Content-Disposition", headerContent);
        return response.build();
    }
}
