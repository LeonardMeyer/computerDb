package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.ComputerDto;

public interface ComputerService{

	/**
	 * Retrieve a <code>Computer</code> from the data store by id.
	 * 
	 * @param id
	 *            the id to search for
	 * @return the <code>Computer</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	ComputerDto findById(int id) throws DataRetrievalFailureException;

	/**
	 * Retrieve all <code>Computer</code> from the data store.
	 * 
	 * @param fromBound
	 *            Index dé départ de la récupération
	 * @param maxResult
	 *            Le nombre de lignes à retourner depuis fromBound
	 * 
	 * @return a <code>Collection</code> of <code>Computer</code>
	 */
	List<ComputerDto> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException;

	/**
	 * Retrieve <code>Computer</code>s from the data store given a specific
	 * string.
	 * 
	 * @return a <code>Collection</code> of <code>Computer</code>s
	 */
	List<ComputerDto> search(String name, SearchOrder orderBy, int fromBound, int maxResult) throws DataRetrievalFailureException;

	/**
	 * Save a <code>Company</code> to the data store, either inserting or
	 * updating it.
	 * 
	 * @param computer
	 *            the <code>Computer</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(ComputerDto computer) throws DataAccessException;

	/**
	 * Delete a <code>Computer</code> from the data store it.
	 * 
	 * @param id
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