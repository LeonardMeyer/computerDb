package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.excilys.computerdb.business.domain.Company;

public interface CompanyService {

	/**
	 * Retrieve all <code>Company</code> names from the data store.
	 * 
	 * @return a <code>Collection</code> of <code>Company</code> names
	 */
	List<Company> findAll() throws DataAccessException;
}
