package com.excilys.computerdb.business.domain;

import java.io.Serializable;

import org.joda.time.DateTime;



/**
 * @author Leonard
 *
 */
public class Computer implements Serializable {
	
	private int computerId;
	private String name;
	private DateTime introduced;
	private DateTime discontinued;
	private int companyId;
	private String companyName;
	
	public Computer(int computerId, String name, DateTime introduced,
			DateTime discontinued, int companyId, String companyName) {
		super();
		this.computerId = 0;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.setCompanyName(companyName);
	}
	
	public Computer(String name, DateTime introduced,
			DateTime discontinued) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
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

	public DateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}

	public DateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
