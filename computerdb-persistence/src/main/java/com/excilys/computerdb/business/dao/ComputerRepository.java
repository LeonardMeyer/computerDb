package com.excilys.computerdb.business.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.excilys.computerdb.business.domain.Computer;

@Profile(value="jpa")
public interface ComputerRepository extends JpaRepository<Computer, Integer>{
	/**
	 * Repository class for <code>Computer</code> domain objects All method names are
	 * compliant with Spring Data naming conventions so this interface can
	 * easily be extended for Spring Data See here:
	 * http://static.springsource.org
	 * /spring-data/jpa/docs/current/reference/html
	 * /jpa.repositories.html#jpa.query-methods.query-creation
	 * 
	 * @author Léonard Meyer
	 */
	
	/**
	 * Retrieve <code>Computer</code>s from the data store given a specific string.
	 * 
	 * @return a <code>Collection</code> of <code>Computer</code>s
	 */
	Page<Computer> findByNameContaining(String name, Pageable pageable) throws DataRetrievalFailureException;
	
	/**
	 * Count the number of filtered <code>Computer</code>s of the data store.
	 *         
	 * @return a <code>int</code>
	 */

	long countByNameContaining(String name) throws DataAccessException;
}
