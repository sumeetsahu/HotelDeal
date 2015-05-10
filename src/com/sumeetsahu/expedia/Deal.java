package com.sumeetsahu.expedia;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Deal {

	private DealDecorator deal;
	
	public Deal(String sort, String dealorder) {
		
		deal = DealFactory.Create(sort,null);		
		
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
