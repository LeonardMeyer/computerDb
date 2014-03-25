package com.excilys.computerdb.business.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import com.excilys.computerdb.business.domain.Computer;

public interface ComputerRepository {
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
     * Retrieve a <code>Computer</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Computer</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Computer findById(int id) throws DataRetrievalFailureException;

	
	/**
	 * Retrieve <code>Computer</code>s from the data store given a specific string.
	 * 
	 * @return a <code>Collection</code> of <code>Computer</code>s
	 */
	List<Computer> search(String name, SearchOrder orderBy, int fromBound, int maxResult) throws DataRetrievalFailureException;

	/**
	 * Save a <code>Company</code> to the data store, either inserting or updating
	 * it.
	 * 
	 * @param computer
	 *            the <code>Computer</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Computer computer) throws DataAccessException;
	
	/**
	 * Delete a <code>Computer</code> from the data store
	 * it.
	 * 
	 * @param computer
	 *            the <code>Computer</code> to save
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Count the number of <code>Computer</code>s of the data store.
	 *         
	 * @return a <code>int</code>
	 */
	long count() throws DataAccessException;	
	
	/**
	 * Count the number of filtered <code>Computer</code>s of the data store.
	 *         
	 * @return a <code>int</code>
	 */
	long countFiltered(String name) throws DataAccessException;
}
