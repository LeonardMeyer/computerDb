package com.excilys.computerdb.business.domain;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;



/**
 * @author Leonard
 *
 */
public class Computer implements Serializable {
	
	private int computerId;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	
	public Computer(int computerId, String name, LocalDate introduced,
			LocalDate discontinued, Company company) {
		super();
		this.computerId = computerId;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.setCompany(company);
	}

	public int getComputerId() {
		return computerId;
	}

	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
