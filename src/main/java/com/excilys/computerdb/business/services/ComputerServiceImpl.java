package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.domain.Computer;

@Service
public class ComputerServiceImpl implements ComputerService{

	private ComputerRepository computerRepo;
	
	@Override
	@Transactional(readOnly=true)
	public Computer findById(int id) throws DataRetrievalFailureException {
		return computerRepo.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Computer> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		return computerRepo.findByRange(fromBound, maxResult);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Computer> findByName(String name)
			throws DataRetrievalFailureException {
		return computerRepo.findByName(name);
	}

	@Override
	@Transactional
	public void save(Computer computer) throws DataAccessException {
		computerRepo.save(computer);
	}

	@Override
	@Transactional
	public void delete(int id) throws DataAccessException {
		computerRepo.delete(id);
	}

	@Override
	@Transactional
	public int count() throws DataAccessException {
		return computerRepo.count();
	}

	public ComputerRepository getComputerRepo() {
		return computerRepo;
	}

	@Autowired
	public void setComputerRepo(ComputerRepository computerRepo) {
		this.computerRepo = computerRepo;
	}



}
