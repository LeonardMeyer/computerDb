package com.excilys.computerdb.business.ws;

import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.excilys.computerdb.business.services.ComputerService;


@Path("/Computers")
public class ComputerWebserviceImpl implements ComputerWebservice{
	
	@Autowired
	private ComputerService compService;
	
	@Override
	public long count() throws DataAccessException {
		return compService.count();
	}
	@Override
	public long countFiltered(String name) throws DataAccessException{
		return compService.countFiltered(name);
	}
}
