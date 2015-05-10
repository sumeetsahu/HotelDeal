package com.sumeetsahu.expedia;

public abstract class ApiFilterDecorator implements Filterable {

	private Filterable filter;
	private String value;
	
	public ApiFilterDecorator(String value, Filterable filter) {
		this.filter = filter;
		this.setValue(value);
	}
	
	abstract public String getFilterQuery();
	
	final public String getQuery(){
		String query = "";
		
		if(filter!=null)
			query+=filter.getQuery();
		
		if(!query.isEmpty())
			query+="&";
		
		query+=getFilterQuery();
		
		return query;
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}
	

}
