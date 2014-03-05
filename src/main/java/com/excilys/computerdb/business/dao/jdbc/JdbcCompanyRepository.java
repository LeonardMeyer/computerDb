package com.excilys.computerdb.business.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.CompanyRepository;
import com.excilys.computerdb.business.domain.Company;

@Repository
public class JdbcCompanyRepository implements CompanyRepository{

	private Logger logger = LoggerFactory.getLogger(JdbcCompanyRepository.class);
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcCompanyRepository(DataSource dataSource) {
		this.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Company> findAllNames() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


}
