package com.sumeetsahu.expedia;

import java.util.Comparator;

public class DiscountDeal extends DealDecorator {


	public DiscountDeal(Comparator<Hotel> comparator) {
		super(comparator);
	}

	@Override
	public int compareDeal(Hotel o1, Hotel o2) {
		
		return -1 * o1.getPercentSavings().compareTo(o2.getPercentSavings());
	}

}

