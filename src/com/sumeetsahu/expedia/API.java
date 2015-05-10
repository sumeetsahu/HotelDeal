package com.sumeetsahu.expedia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class API {
	
	public static Hotel[] getHotels(String apiurl) throws JsonSyntaxException, Exception{
		Filterable nofilter = new NoFilter();
		return getHotels(apiurl,nofilter);
	}
	
	public static Hotel[] getHotels(String apiurl, Filterable filter) throws JsonSyntaxException, Exception {		
		Gson gson = new GsonBuilder().create();
		Hotel[] hotels=(Hotel[])gson.fromJson(GetDeal(apiurl, filter.getQuery()), Hotel[].class);
		return hotels;
	}
	
	private static String GetDeal(String url, String query) throws Exception {		
		
		if(query!=null && ! query.trim().isEmpty())
			url+="?"+query;
		
		return getJsonData(url);
		
			
	}

	private final static String USER_AGENT = "Mozilla/5.0";
	
	// HTTP GET request
	private static String getJsonData(String url) throws Exception { 
		
		URL obj = new URL(url);
			
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();//TODO We can reuse HttpURLConnection to improve system performance
	 
			
		// optional default is GET			
		con.setRequestMethod("GET");	 
			
		//add request header			
		con.setRequestProperty("User-Agent", USER_AGENT);	 
			
		BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
		
		
			
		String inputLine;			
		StringBuffer response = new StringBuffer();	 			
		
		while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
		in.close();		
		
		return response.toString();
	 
		}	

	
}
