package com.sumeetsahu.expedia;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonSyntaxException;

@Controller
public class DealController {
	
	@Value("${dealapi}")
	private String dealapi;
	
	@Value("${dealorder}")
	private String dealorder;
		
	/**
	 * expediaDealView : Generate view for deals based upon the preference provided by Config. 'sort' deal will get preference
	 * 
	 * @param city : Filter deals based upon city. By default, deals from all city will be populated if not defined
	 * @param sort : Preference for sorting the deal
	 * @param start : From index of the sorted deal
	 * @param end : To index of the sorted deal
	 * @return : List of Hotels sorted as per the preference provided by Config and 'sort'
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	@Cacheable(value = "dealviewcache")
	@RequestMapping(value = "/deal", method=RequestMethod.GET)
	public ModelAndView expediaDealView(
			@RequestParam(value="city", defaultValue="", required=false) String city, 
			@RequestParam(value="sort", defaultValue="discount", required=false) String sort, 
			@RequestParam(value="start", defaultValue="0", required=false) int start, 
			@RequestParam(value="end", defaultValue="-1", required=false) int end) 
					throws JsonSyntaxException, Exception{

		List<Hotel> hotels = dealAPI(city, sort, start, end);
		
		ModelAndView modelandview = new ModelAndView("Deal");
		modelandview.addObject("welcomeMessage",hotels.get(0).getName());
		return modelandview;
		
	}
	
	
	
	/**
	 * 
	 * dealAPI : returns ordered list of Hotels as per the preference provided by Config. 'sort' deal will get preference
	 * 
	 * @param city : Filter deals based upon city. By default, deals from all city will be populated if not defined
	 * @param sort : Preference for sorting the deal
	 * @param start : From index of the sorted deal
	 * @param end : To index of the sorted deal
	 * @return : List of Hotels sorted as per the preference provided by Config and 'sort'
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	@Cacheable(value = "dealcache")
	@RequestMapping(value = "/API/deal", method=RequestMethod.GET)
	public @ResponseBody List<Hotel> dealAPI(
			@RequestParam(value="city", defaultValue="", required=false) String city, 
			@RequestParam(value="sort", defaultValue="discount", required=false) String sort, 
			@RequestParam(value="start", defaultValue="0", required=false) int start, 
			@RequestParam(value="end", defaultValue="-1", required=false) int end) 
					throws JsonSyntaxException, Exception{
		
		
		Filterable filter = new CityFilter(city, null);//TODO We have used only one filter; Filters are designed as decorator so we can add more filters if required in future 
		
		Hotel[] hotelsArray = API.getHotels(dealapi,filter);
		
		Deal deal = new Deal(sort, dealorder);
		List<Hotel> hotels = deal.getHotelDeals(hotelsArray);
		
		if(end!=-1)
			return hotels.subList(start, end);
		return hotels;
		
	}
	
	
	/**
	 * 
	 * getCities : returns list of cities available in API.
	 * 
	 * @return List of cities available with the API
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	@Cacheable(value = "citiescache")
	@RequestMapping(value = "/API/cities")
	public @ResponseBody List<String> getCities() 
			throws JsonSyntaxException, Exception{
		
		HashSet<String> cities = new HashSet<String>();

		Hotel[] hotels = API.getHotels(dealapi);
		
		for (Hotel hotel : hotels) {
			if(!cities.contains(hotel.getCity()))
				cities.add(hotel.getCity());
		}
		
		List<String> citiesList = new ArrayList<String>(cities);
		Collections.sort(citiesList);
		
		return citiesList;
		
	}
	
}
