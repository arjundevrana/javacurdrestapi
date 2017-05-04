package com.arjun.client;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.arjun.bean.APIResponse;
import com.arjun.bean.Country;
public class ApiClient {
	public static void main(String[] args) {
		Country country =  new Country();
		country.setId(1);
	    country.setCountryName("INDIA");
	    country.setPopulation(1000012);
	    System.out.println(addCountry(country));
		System.out.println(findAllCountry().toString());

	}
   static APIResponse findAllCountry(){
	   @SuppressWarnings("deprecation")
	DefaultHttpClient httpClient = new DefaultHttpClient();
	   APIResponse apiResponse = null;
	    try
	    {
	        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
	        //Choice depends on type of method you will be invoking.
	    	
	        HttpGet getRequest = new HttpGet("http://localhost:8080/JAXRRestCurdApi/rest/countries/xml/");
	         
	        //Set the API media type in http accept header
	        getRequest.addHeader("accept", "application/xml");
	          
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String apiOutput = EntityUtils.toString(httpEntity);
	         
	        //Lets see what we got from API
	        System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>
	         
	        //In realtime programming, you will need to convert this http response to some java object to re-use it.
	        //Lets see how to jaxb unmarshal the api response content
	        JAXBContext jaxbContext = JAXBContext.newInstance(APIResponse.class);
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        apiResponse = (APIResponse) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	         
	      
	    }catch( Exception e){
	    	e.printStackTrace();
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	return apiResponse;
	   
   }
   
     static APIResponse addCountry( Country country){
    	 DefaultHttpClient httpClient = new DefaultHttpClient();
    	 APIResponse apiResponse = null;
    	    
    	    try
    	    {
    	    
    	    StringWriter writer = new StringWriter();
    	    JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
    	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    	    jaxbMarshaller.marshal(country, writer);
    	     
    	    
    	        //Define a postRequest request
    	        HttpPost postRequest = new HttpPost("http://localhost:8080/JAXRRestCurdApi/rest/countries/xml");
    	         
    	        //Set the API media type in http content-type header
    	        postRequest.addHeader("content-type", "application/xml");
    	         
    	        //Set the request post body
    	        StringEntity userEntity = new StringEntity(writer.getBuffer().toString());
    	        postRequest.setEntity(userEntity);
    	          
    	        //Send the request; It will immediately return the response in HttpResponse object if any
    	        HttpResponse response = httpClient.execute(postRequest);
    	         
    	        //verify the valid error code first
    	        int statusCode = response.getStatusLine().getStatusCode();
    	        if (statusCode != 200) 
    	        {
    	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
    	        }
    	        //Now pull back the response object
    	        HttpEntity httpEntity = response.getEntity();
    	        String apiOutput = EntityUtils.toString(httpEntity);
    	         
    	        //Lets see what we got from API
    	        System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>
    	         
    	        //In realtime programming, you will need to convert this http response to some java object to re-use it.
    	        //Lets see how to jaxb unmarshal the api response content
    	        JAXBContext jaxbContextResponce = JAXBContext.newInstance(APIResponse.class);
    	        Unmarshaller jaxbUnmarshaller = jaxbContextResponce.createUnmarshaller();
    	        apiResponse = (APIResponse) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
    	    } catch( Exception exception){
    	    	exception.printStackTrace();
    	    }
    	    finally
    	    {
    	        //Important: Close the connect
    	        httpClient.getConnectionManager().shutdown();
    	    }
		return apiResponse;
    	 
     }
}
