package com.sumeetsahu.expedia;


public class CityFilter extends ApiFilterDecorator {


	public CityFilter(String value, Filterable filter) {
		super(value, filter);
	}

	@Override
	public String getFilterQuery() {
		return "city="+getValue();
	}

}
