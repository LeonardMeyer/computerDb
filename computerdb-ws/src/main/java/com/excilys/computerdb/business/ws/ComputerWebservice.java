package com.excilys.computerdb.business.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import com.excilys.computerdb.business.domain.ComputerDto;

@WebService
public interface ComputerWebservice {

	@WebMethod
	public List<ComputerDto> search(String name, int nbElem, String orderBy, int fromBound) 
			throws DataRetrievalFailureException;
	
	@WebMethod
	public long count() throws DataAccessException;
	
	@WebMethod
	public long countFiltered(String name) throws DataAccessException;

}
