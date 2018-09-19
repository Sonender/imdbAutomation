package com.imdbautomation.api;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseAPI {
	   /* This is the primary class of API automation
	    * here we have used the RestAssured to create functions
	    * for different types of requests
	    * 
	    */
			
		public static String path;
		static String accessTokenHeader = "access_token";
		
		public static void setBaseUri(String baseURI){
			RestAssured.baseURI = baseURI;
		} 
		
		public static void setBasePath(String basePath){
			RestAssured.basePath = basePath;
		}
		
		 public static JsonPath getJsonPath (Response res) {
		        String json = res.asString();
		        return new JsonPath(json);
		 }
		
	
	public static Response getApiResponse(String contentType, String accessToken, String resourceName, String resourceId, HashMap<String, ?> queryParams) {
	        Response response =
	            given().
	            	params(queryParams).
	            	header(accessTokenHeader, accessToken).
	                contentType(contentType).
	            when().
	                get(resourceName + resourceId).
	            then().
	            extract().response();
	        
	        return response;
	    }
		
		public static Response postApiResponse(String contentType, String accessToken, String resourceName, HashMap<?, ?> bodyParams){
			Response response = 
				given().
					header(accessTokenHeader, accessToken).
					contentType(contentType).
					body(bodyParams).
				when().
					post(resourceName).
				then().
				extract().response();
			
			return response;
			
		}
	}


