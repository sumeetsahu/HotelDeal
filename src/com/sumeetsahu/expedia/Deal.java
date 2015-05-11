package com.sumeetsahu.expedia;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Deal {

	private DealDecorator deal;
	
	public Deal(String sort, String dealorder) {
		
		deal = DealFactory.Create(sort.toLowerCase(),null);		
		
		
		/*
		 *  Use Case : List all hotel with highest discount on top,
		 *  	if discount is same then show least price hotel on top,
		 *  	if price is same then show best guest rating hotel on top,
		 *  	if guest rating is same then show best star rating on top
		 *  
		 *  Default order is defined at "resource/config.properties". User can put his preference on top of this list if he provide value for 'sort'
		 *  
		 */
		for (String dealItem : dealorder.split(",")) {
			if(dealItem!=sort)
				deal = DealFactory.Create(dealItem, deal);// Decorating each deal to generate Comparator; DealDecorator has implemented Comparator
		}
		
	}

	public List<Hotel> getHotelDeals(Hotel[] hotels) {

		
		List<Hotel> hotelList = Arrays.asList(hotels);		
		
		Collections.sort(hotelList, deal);// DealDecorator has implemented Comparator		
		
		return hotelList;
	}

}
