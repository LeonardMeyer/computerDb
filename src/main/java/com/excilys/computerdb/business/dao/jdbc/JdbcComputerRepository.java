package com.excilys.computerdb.business.dao.jdbc;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerMapper;

@Repository(value="JdbcComputerRepository")
@Profile(value="jdbc")
public class JdbcComputerRepository implements ComputerRepository {

	private Logger logger = LoggerFactory
			.getLogger(JdbcComputerRepository.class);

	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcComputerRepository(DataSource dataSource) {
		this.setNamedJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	@Override
	public Computer findById(int id) throws DataRetrievalFailureException {
		String sql = "SELECT * FROM computer LEFT JOIN company ON (company.id = computer.company_id) WHERE computer.id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id)); 
		return (Computer) namedJdbcTemplate.queryForObject(sql, namedParameters, new ComputerMapper());
	}

	@Override
	public List<Computer> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer "
				+ "LEFT JOIN company ON (company.id = computer.company_id) "
				+ "ORDER BY computer.name LIMIT :fromBound,:maxResult";
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("fromBound", fromBound);
		argMap.put("maxResult", maxResult);
		return namedJdbcTemplate.query(sql, argMap, new ComputerMapper());
	}

	@Override
	public List<Computer> findByName(String name)
			throws DataRetrievalFailureException {
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
				+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
				+ "WHERE computer.name LIKE :name" + "ORDER BY computer.name";
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("name", name);
		return namedJdbcTemplate.query(sql, argMap, new ComputerMapper());
	}

	@Override
	public void save(Computer computer) throws DataAccessException {
		String sql = "INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES(:id, :name, :intro, :disc, :compId)"
				+ "ON DUPLICATE KEY UPDATE name = :name, introduced = :intro, discontinued = :disc, company_id = :compId";
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("id", computer.getComputerId());
		argMap.put("name", computer.getName());
		argMap.put("intro", new Timestamp(computer.getIntroduced()
				.toDateTimeAtStartOfDay().getMillis()));
		argMap.put("disc", new Timestamp(computer.getDiscontinued()
				.toDateTimeAtStartOfDay().getMillis()));
		if (computer.getCompany().getCompanyId() == -1) {
			argMap.put("compId", null);
		} else {
			argMap.put("compId", computer.getCompany().getCompanyId());
		}
		namedJdbcTemplate.update(sql, argMap);
	}

	@Override
	public void delete(int id) throws DataAccessException {
		String sql = "DELETE FROM computer WHERE id = :id";  
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);  
		namedJdbcTemplate.update(sql, namedParameters);  
	}

	@Override
	public int count() throws DataAccessException {
		String sql = "SELECT COUNT(id) FROM computer";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


}
