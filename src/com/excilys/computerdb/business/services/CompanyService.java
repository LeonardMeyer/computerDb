package com.excilys.computerdb.business.services;

import java.util.List;

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
		List<Company> foundCompanies = DaoFactory.getCompanyDao().findAll();
		return foundCompanies;
	}

	@Override
	public Company create(Company obj) {
		Company createdCompany = DaoFactory.getCompanyDao().create(obj);
		return createdCompany;
	}

	@Override
	public Company update(Company obj) {
		Company updatedCompany = DaoFactory.getCompanyDao().update(obj);
		return updatedCompany;
	}

	@Override
	public void delete(Company obj) {
		DaoFactory.getCompanyDao().delete(obj);
	}

}
