package com.imdbautomation.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetReader {
	
	/* This class is used to get the data kept in google sheets
	 * we can use this for parameterization
	 */
	
	private static final String APPLICATION_NAME = "User_Email_Mapping";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String CREDENTIALS_FOLDER = System.getProperty("user.dir")+"/src/main/java/resources/"; // Directory to store user credentials.

	/**
	 * Global instance of the scopes required by this quickstart.
	 * If modifying these scopes, delete your previously saved credentials/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CLIENT_SECRET_DIR =System.getProperty("user.dir")+"/src/main/java/resources/client_secret.json";

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If there is no client_secret.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in =  new FileInputStream(CLIENT_SECRET_DIR);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
				.setAccessType("offline")
				.build();
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	public Map<Object, Map<String,List<Object>>>  returnAllList(String spreadsheetId, String range) throws GeneralSecurityException, IOException
	{     
		Map<Object, Map<String,List<Object>>> obj = new HashMap<Object, Map<String,List<Object>>>();
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range)
				.execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for(int i=0; i<values.size(); i++){
				for(int j=0; j<values.get(i).size(); j++ )
				{

				}
			}
		}

		return obj;

	}

	private Map<Object,Object> excelReadObj(String spreadsheetId,String range) throws GeneralSecurityException
	{
		Map<Object,Object> map = new LinkedHashMap<Object,Object>();

		try
		{
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME)
					.build();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
			//			OutputStream out = new FileOutputStream(System.getProperty("user.dir")+"/file");
			//			System.out.println("File saved at" + System.getProperty("user.dir"));
			//			service.spreadsheets().values().get(spreadsheetId, range).executeAndDownloadTo(out);
			List<List<Object>> values = response.getValues();
			if (values == null || values.size() == 0) 
			{
				System.out.println("No data found.");
			} 
			else
			{
				//System.out.println("No of columns in sheet"+ values.get(0).size());
				for(int i=0; i<values.size(); i++)
				{
					if(values.get(i).size()>2)
					{
						//System.out.println(values.size());
						//System.out.println("No of colums greater than 2, will return Map with key and list of objects");


						{   
							Object key = values.get(i).get(0);
							int sizeOfList = values.get(i).size();
							List <String> newlist = new ArrayList<String>();
							for(int j = 1; j<sizeOfList; j++)
							{
								Object x = values.get(i).get(j);
								newlist.add(x.toString());
								x = null;
							}
							map.put(key, newlist);
						}
					}
					else
					{
						try{
							if((values.get(i).size()>0)&&(values.get(i).size()<=2))
							{
								//System.out.println(values.get(i));

								for(int k=0;k<values.get(i).size(); k++){
									if(values.get(i).size()==2){
										map.put(values.get(i).get(0),(values.get(i).get(1)));    
									}
									else
										if(values.get(i).size()==1)
										{

											map.put(values.get(i).get(0)," ");}
								}
							}

						}catch(Exception e)
						{  System.out.println("Exception is returned"+e); }
					}
				}

			}
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
		return map;
	}

	public  Map<Object, Map<Object, List<Object>>> desiredMap( String spreadsheetId, String range) throws GeneralSecurityException{

		Map<Object, Map<Object, List<Object>>> mapObj = new HashMap<Object, Map<Object, List<Object>>>();
		Map<Object,Object> obj = excelReadObj( spreadsheetId, range);

		for(Map.Entry<Object, Object> oldMap:obj.entrySet())
		{	
			@SuppressWarnings("unchecked")
			List<Object> listObj = (List<Object>) oldMap.getValue();
			List<Object> newList = new ArrayList<Object>();
			Map<Object, List<Object>> interimMap = new HashMap<Object, List<Object>>();
			String toBeadd= (String) listObj.get(0);
			for(int i=1; i<listObj.size(); i++)
			{

				newList.add((String)listObj.get(i));
			}

			interimMap.put(toBeadd, newList);
			mapObj.put(oldMap.getKey(), interimMap);
		}
		return mapObj;
	}

	public Map<Object, Object> returnmap(String spreadsheetId, String range) throws GeneralSecurityException
	{
		Map<Object, Object> interimMap= excelReadObj(spreadsheetId,range);
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		for(Map.Entry<Object,Object> obj:interimMap.entrySet())
		{
			@SuppressWarnings("unchecked")
			List<String> xyz= (List<String>)obj.getValue();
			returnMap.put(obj.getKey(), xyz.get(0));

		}
		return returnMap;
	}

	public Map<Object, List<Object>> returnmap(Map<Object, Map<Object, List<Object>>> obj) 
	{
		Map<Object, List<Object>>  returnMap = new HashMap<Object, List<Object>>();
		for(Map.Entry<Object, Map<Object,List<Object>>> object:obj.entrySet() )
		{

			returnMap.putAll(object.getValue());

		}
		return returnMap;

	}

}    	


