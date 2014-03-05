package com.excilys.computerdb.business.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.domain.Computer;

@Repository
public class JdbcComputerRepository implements ComputerRepository{
	
	private Logger logger = LoggerFactory.getLogger(JdbcComputerRepository.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcComputerRepository(DataSource dataSource) {
		this.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));

	}

	@Override
	@Transactional(readOnly=true)
	public Computer findById(int id) throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Computer> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Computer> findByName(String name) throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Computer computer) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void delete(Computer computer) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public int count() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	

}
