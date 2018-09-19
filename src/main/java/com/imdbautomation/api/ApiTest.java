package com.imdbautomation.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import io.restassured.response.Response;

public class ApiTest {
	
	/* In this class we have created few validations
	 * that we can use in our test cases
	 * 
	 */
	
		private Response response = null;
		
		public Response getAPICall(String baseUri, String basePath, String contentType, String accessToken, String resource, String resourceId, HashMap<String, ?> params ){
			BaseAPI.setBaseUri(baseUri); //Setup Base URI
			BaseAPI.setBasePath(basePath); //Setup Base Path
		      response = BaseAPI.getApiResponse(contentType, accessToken, resource, resourceId, params);
		      return response;
		}
		
		public Response postAPICall(String baseUri, String basePath, String contentType, String accessToken, String resource, HashMap<?, ?> bodyParams){
			BaseAPI.setBaseUri(baseUri); //Setup Base URI
			BaseAPI.setBasePath(basePath); //Setup Base Path
		      response = BaseAPI.postApiResponse(contentType, accessToken, resource, bodyParams);
		      return response;
		}
		
		public static int checkStatusIs200(Response res){
			return res.getStatusCode();
		}
		
		public static int getResponseTime(Response res){
			System.out.println(res.time());
			return (int) res.time();
		}
		public static String appendAndEncode(ArrayList<String> data){
			   int length=data.size();
			   String token=new String();
			   for(int i=0;i<length-1;i++){
				   token+=data.get(i)+":";
			   }
			   token+=data.get(length-1);
			   byte[] encodedBytes = Base64.encodeBase64(token.getBytes());
			   return new String(encodedBytes);
		 }
		
	}

