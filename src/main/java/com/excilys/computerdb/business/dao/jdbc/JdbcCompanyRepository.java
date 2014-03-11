package com.excilys.computerdb.business.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.CompanyRepository;
import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.CompanyMapper;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerMapper;

@Repository(value="JdbcCompanyRepository")
@Profile(value="jdbc")
public class JdbcCompanyRepository implements CompanyRepository{

	private Logger logger = LoggerFactory.getLogger(JdbcCompanyRepository.class);
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public JdbcCompanyRepository(DataSource dataSource) {
		this.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));

	}
	
	@Override
	public List<Company> findAll() throws DataAccessException {
		String sql = "SELECT * FROM company ORDER BY company.name";
		return namedJdbcTemplate.query(sql, new CompanyMapper());
	}
	

	@Override
	public Company findById(int id) throws DataAccessException {
		String sql = "SELECT * FROM company WHERE company.id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id); 
		return (Company) namedJdbcTemplate.queryForObject(sql, namedParameters, new CompanyMapper());
	}


	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.namedJdbcTemplate = jdbcTemplate;
	}


}
