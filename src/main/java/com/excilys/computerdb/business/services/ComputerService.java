package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.business.dao.ComputerDao;
import com.excilys.computerdb.business.domain.Computer;

@Service
public class ComputerService implements ServiceProvider<Computer> {

	private ComputerDao computerDao;
	
	@Override
	public Computer find(long id) {
		Computer foundComputer = computerDao.find(id);
		return foundComputer;
	}

	@Override
	public List<Computer> findAll() {
		return computerDao.findAll();
	}

	@Override
	public boolean create(Computer obj) {
		boolean success = computerDao.create(obj);
		return success;
	}

	@Override
	public boolean delete(int id) {
		return computerDao.delete(id);
	}
	
	public List<Computer> findAllInRange(int firstBound, int secondBound) {
		return computerDao.findAllInRange(firstBound, secondBound);
	}
	
	public List<Computer> filterByName(String toSearch) {
		return computerDao.filterByName(toSearch);
	}
	
	public int count() {
		return computerDao.count();
	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}

	@Autowired
	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

}
