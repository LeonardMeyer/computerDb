package com.excilys.computerdb.business.services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.business.domain.Company;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Override
	public List<Company> findAllNames() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


}
