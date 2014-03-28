/**
 * 
 */
package com.excilys.computerdb.business.domain;

import java.io.Serializable;







import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leonard
 *
 */
@Entity
@Table(name="company")
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8904031966606029875L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int companyId;
	@Column
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
