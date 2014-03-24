package com.excilys.computerdb.business.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.computerdb.business.annotations.ValidDate;


public class ComputerDto{
	private int computerId;
	@NotEmpty
	private String name;
	@ValidDate
	private String introduced;
	@ValidDate
	private String discontinued;
	private int companyId;
	private String companyName;

	
	public ComputerDto() {

	}
	
	public ComputerDto(int computerId, String name, String introduced,
			String discontinued, int companyId, String companyName) {
		super();
		this.computerId = computerId;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
		
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
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
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
