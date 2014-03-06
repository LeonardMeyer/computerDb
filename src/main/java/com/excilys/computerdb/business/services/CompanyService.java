package com.excilys.computerdb.business.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.business.dao.CompanyDao;
import com.excilys.computerdb.business.domain.Company;

@Service
public class CompanyService implements ServiceProvider<Company> {

	private CompanyDao companyDao;


	@Override
	public Company find(long id) {
		Company foundCompany = companyDao.find(id);
		return foundCompany;
	}

	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}

	@Override
	public boolean create(Company obj) {
		boolean success = companyDao.create(obj);
		return success;
	}

	@Override
	public boolean delete(int id) {
		return companyDao.delete(id);
	}
	
	public Map<Integer, String> findAllCompanyNames() {
		return companyDao.findAllNames();	
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
