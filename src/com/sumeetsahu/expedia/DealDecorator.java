package com.sumeetsahu.expedia;

import java.util.Comparator;

/**
 * @author ssahu
 *
 *	Abstract Class for Decorator Pattern
 *
 *	All Deal has to implement this abstract class
 *	
 */
public abstract class DealDecorator implements Comparator<Hotel> {

	private Comparator<Hotel> comparator;
	
	public DealDecorator(Comparator<Hotel> comparator){
		this.comparator = comparator;
	}
	
	abstract public int compareDeal(Hotel o1, Hotel o2);
	
	final public int compare(Hotel o1, Hotel o2) {
		
		int dealValue = 0;
		if(comparator!=null)
			dealValue=comparator.compare(o1,o2);
		if(dealValue==0)
			return compareDeal(o1,o2);
		
		return dealValue;
		
			
	}
	
}
