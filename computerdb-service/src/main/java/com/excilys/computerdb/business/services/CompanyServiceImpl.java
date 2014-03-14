package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.CompanyRepository;
import com.excilys.computerdb.business.domain.Company;

@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyRepository companyRepo;
	
	@Override
	@Transactional(readOnly=true)
	public List<Company> findAll() throws DataAccessException {
		return companyRepo.findAll();
	}

	public CompanyRepository getCompanyRepo() {
		return companyRepo;
	}

	@Autowired
	public void setCompanyRepo(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}


}
