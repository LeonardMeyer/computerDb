package com.excilys.computerdb.business.domain;

import org.joda.time.LocalDate;

public class ComputerDto {
	private int computerId;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int companyId;
	private String companyName;
	
	public ComputerDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ComputerDto(int computerId, String name, LocalDate introduced,
			LocalDate discontinued, int companyId, String companyName) {
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
