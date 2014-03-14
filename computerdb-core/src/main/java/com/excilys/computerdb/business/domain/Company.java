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
@Component
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8904031966606029875L;
	private int companyId;
	private String name;

	public Company() {

	}
	
	public Company(int companyId, String name) {
		super();
		this.companyId = companyId;
		this.name = name;
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


}
