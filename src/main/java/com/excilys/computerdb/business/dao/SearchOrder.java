package com.excilys.computerdb.business.dao;

public enum SearchOrder {
	NAME_ASC("ORDER BY computer.name ASC"),
	NAME_DESC("ORDER BY computer.name DESC"),
	INTRO_ASC("ORDER BY computer.introduced ASC"),
	INTRO_DESC("ORDER BY computer.introduced DESC"),
	DISC_ASC("ORDER BY computer.discontinued ASC"),
	DISC_DESC("ORDER BY computer.discontinued DESC"),
	COMPANY_ASC("ORDER BY company.name ASC"),
	COMPANY_DESC("ORDER BY company.name DESC");

    private final String strategy;
    SearchOrder(String order) { this.strategy = order; }
    public String getValue() { return strategy; }
    
    @Override
    public String toString() {
    	return strategy;
    }
}
