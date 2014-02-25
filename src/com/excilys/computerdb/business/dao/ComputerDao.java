package com.excilys.computerdb.business.dao;

import java.util.List;

import com.excilys.computerdb.business.domain.Computer;

public class ComputerDao extends Dao<Computer> {
	
	private static volatile ComputerDao instance = null;

	private ComputerDao() {
	}

	public static ComputerDao getInstance() {
		if (instance == null) {
			synchronized (ComputerDao.class) {
				// Double check
				if (instance == null) {
					instance = new ComputerDao();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Computer find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Computer create(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Computer update(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}

}
