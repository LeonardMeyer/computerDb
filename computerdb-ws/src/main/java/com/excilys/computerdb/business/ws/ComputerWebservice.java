package com.excilys.computerdb.business.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.dao.DataAccessException;


public interface ComputerWebservice {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public long count() throws DataAccessException;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("find/{name}")
	public long countFiltered(@PathParam("name") String name) throws DataAccessException;

}
