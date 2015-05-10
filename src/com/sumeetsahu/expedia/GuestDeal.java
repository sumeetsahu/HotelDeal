package com.sumeetsahu.expedia;

import java.util.Comparator;

public class GuestDeal extends DealDecorator{
	
	public GuestDeal(Comparator<Hotel> comparator) {
		super(comparator);		
	}

	@Override
	public int compareDeal(Hotel o1, Hotel o2) {
		
		return -1 * o1.getGuestRating().compareTo(o2.getGuestRating());
		
	}

	
}
