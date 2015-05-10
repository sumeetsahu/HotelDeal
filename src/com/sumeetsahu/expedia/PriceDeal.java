package com.sumeetsahu.expedia;

import java.util.Comparator;

public class PriceDeal extends DealDecorator{

	public PriceDeal(Comparator<Hotel> comparator) {
		super(comparator);		
	}

	@Override
	public int compareDeal(Hotel o1, Hotel o2) {
		
		return o1.getPricePerNight().compareTo(o2.getPricePerNight());
	}

	

}
