package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.business.domain.Computer;

@Service
public class ComputerServiceImpl implements ComputerService{

	@Override
	public Computer findById(int id) throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> findByName(String name)
			throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Computer computer) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Computer computer) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}



}
