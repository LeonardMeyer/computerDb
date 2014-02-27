package com.excilys.computerdb.business.services;

import java.util.List;

import com.excilys.computerdb.business.dao.DaoFactory;
import com.excilys.computerdb.business.domain.Computer;

public class ComputerService implements ServiceProvider<Computer> {

	private static volatile ComputerService instance = null;

	private ComputerService() {
	}

	public static ComputerService getInstance() {
		if (instance == null) {
			synchronized (ComputerService.class) {
				// Double check
				if (instance == null) {
					instance = new ComputerService();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Computer find(long id) {
		Computer foundComputer = DaoFactory.getComputerDao().find(id);
		return foundComputer;
	}

	@Override
	public List<Computer> findAll() {
		return DaoFactory.getComputerDao().findAll();
	}

	@Override
	public boolean create(Computer obj) {
		boolean success = DaoFactory.getComputerDao().create(obj);
		return success;
	}

	@Override
	public boolean delete(int id) {
		return DaoFactory.getComputerDao().delete(id);
	}
	
	public List<Computer> findAllInRange(int firstBound, int secondBound) {
		return DaoFactory.getComputerDao().findAllInRange(firstBound, secondBound);
	}
	
	public int count() {
		return DaoFactory.getComputerDao().count();
	}

}
