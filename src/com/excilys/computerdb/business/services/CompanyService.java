package com.excilys.computerdb.business.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.excilys.computerdb.business.dao.DaoFactory;
import com.excilys.computerdb.business.domain.Company;

public class CompanyService implements ServiceProvider<Company> {

	private static volatile CompanyService instance = null;

	private CompanyService() {
	}

	public static CompanyService getInstance() {
		if (instance == null) {
			synchronized (CompanyService.class) {
				// Double check
				if (instance == null) {
					instance = new CompanyService();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Company find(long id) {
		Company foundCompany = DaoFactory.getCompanyDao().find(id);
		return foundCompany;
	}

	@Override
	public List<Company> findAll() {
		return DaoFactory.getCompanyDao().findAll();
	}

	@Override
	public boolean create(Company obj) {
		boolean success = DaoFactory.getCompanyDao().create(obj);
		return success;
	}

	@Override
	public boolean delete(int id) {
		return DaoFactory.getCompanyDao().delete(id);
	}
	
	public Map<Integer, String> findAllCompanyNames() {
		return DaoFactory.getCompanyDao().findAllCompanyNames();	
	}

}
