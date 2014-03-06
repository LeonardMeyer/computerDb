/**
 * 
 */
package com.excilys.computerdb.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Leonard
 *
 */
public class Company implements Serializable {
	
	private int companyId;
	private String name;
	private List<Integer> computersId;
	
	public Company(int companyId, String name, List<Integer> computersId) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.computersId = computersId;
	}
	
	public Company(int companyId, String name) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.computersId = new ArrayList<>();
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getComputersId() {
		return computersId;
	}

	public void setComputersId(List<Integer> computersId) {
		this.computersId = computersId;
	}

}
