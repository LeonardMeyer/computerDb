package com.excilys.computerdb.business.dao;

import java.util.List;

import com.excilys.computerdb.business.domain.Company;

public class CompanyDao extends Dao<Company>{
	private static volatile CompanyDao instance = null;

	private CompanyDao() {
	}

	public static CompanyDao getInstance() {
		if (instance == null) {
			synchronized (CompanyDao.class) {
				// Double check
				if (instance == null) {
					instance = new CompanyDao();
				}
			}
		}
		return instance;
	}

	@Override
	public Company find(long id) {
		return null;
	}
	
	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company create(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub
		
	}

}
