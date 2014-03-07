package com.excilys.computerdb.business.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


import com.excilys.computerdb.business.domain.Company;


public interface CompanyRepository {

	/**
	 * Repository class for <code>Company</code> domain objects All method names are
	 * compliant with Spring Data naming conventions so this interface can
	 * easily be extended for Spring Data See here:
	 * http://static.springsource.org
	 * /spring-data/jpa/docs/current/reference/html
	 * /jpa.repositories.html#jpa.query-methods.query-creation
	 * 
	 * @author Léonard Meyer
	 */

	/**
	 * Retrieve all <code>Company</code> names from the data store.
	 * 
	 * @return a <code>Collection</code> of <code>Company</code> names
	 */
	List<Company> findAll() throws DataAccessException;

}
