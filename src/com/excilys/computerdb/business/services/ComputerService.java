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
	public Computer create(Computer obj) {
		Computer createdComptuer = DaoFactory.getComputerDao().create(obj);
		return createdComptuer;
	}

	@Override
	public Computer update(Computer obj) {
		Computer updatedComputer = DaoFactory.getComputerDao().create(obj);
		return updatedComputer;
	}

	@Override
	public void delete(Computer obj) {
		DaoFactory.getComputerDao().delete(obj);
	}
	
	public List<Computer> findAllInRange(int firstBound, int secondBound) {
		return DaoFactory.getComputerDao().findAllInRange(firstBound, secondBound);
	}

}
