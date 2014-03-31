package com.excilys.computerdb.business.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import com.excilys.computerdb.business.domain.ComputerDto;
import com.excilys.computerdb.business.services.ComputerService;

@WebService
public class ComputerWebserviceImpl {
	
	@Autowired
	private ComputerService compService;

	@WebMethod
	public List<ComputerDto> search(String name, int nbElem, String orderBy, int fromBound) 
			throws DataRetrievalFailureException {
		return compService.search(name, nbElem, orderBy, fromBound);
	}
	
	@WebMethod
	public long count() throws DataAccessException {
		return compService.count();
	}
	
	@WebMethod
	public long countFiltered(String name) throws DataAccessException{
		return compService.countFiltered(name);
	}
}
