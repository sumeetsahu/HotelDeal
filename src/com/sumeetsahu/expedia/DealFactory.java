package com.sumeetsahu.expedia;


/**
 * @author ssahu
 * 
 * DealFactory is a Factory Method to generate DealDecorator
 *
 */
public class DealFactory {

	public static DealDecorator Create(String name, DealDecorator deal) {

		
		switch (name) {
		case "discount":
			return new DiscountDeal(deal);
		case "price": 
			return new PriceDeal(deal);
		case "guestrating": 
			return new GuestRatingDeal(deal);
		case "starrating": 
			return new StarRatingDeal(deal);
		default:
			break;
		}
		
		return null;
	}

}
