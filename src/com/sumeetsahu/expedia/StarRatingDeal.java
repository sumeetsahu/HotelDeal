package com.sumeetsahu.expedia;

import java.util.Comparator;

public class StarRatingDeal extends DealDecorator {

	public StarRatingDeal(Comparator<Hotel> comparator) {
		super(comparator);		
	}

	@Override
	public int compareDeal(Hotel o1, Hotel o2) {
		
		return -1 * o1.getStarRating().compareTo(o2.getStarRating());
	}

}
