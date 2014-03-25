package com.excilys.computerdb.business.dao.jdbc;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerMapper;

@Repository(value="JdbcComputerRepository")
@Profile(value="jdbc")
public class JdbcComputerRepository implements ComputerRepository {

	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcComputerRepository(DataSource dataSource) {
		this.setNamedJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	@Override
	public Computer findById(int id) throws DataRetrievalFailureException {
		String sql = "SELECT * FROM computer LEFT JOIN company ON (company.id = computer.company_id) WHERE computerId = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id); 
		Computer toReturn;
		try {
			toReturn = (Computer) namedJdbcTemplate.queryForObject(sql, namedParameters, new ComputerMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new DataRetrievalFailureException(e.getMessage(), e.getRootCause());
		}
		
		return toReturn;
	}

	@Override
	public List<Computer> search(String name, SearchOrder orderBy, int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		String sql = null;
		Map<String, Object> argMap = new HashMap<String, Object>();
		if (name  == null) {
			sql = "SELECT computerId, computer.name, introduced, discontinued, company_id, company.name "
					+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
					+  orderBy.toString() + " LIMIT :fromBound, :maxResult";
		}else {
			sql = "SELECT computerId, computer.name, introduced, discontinued, company_id, company.name "
					+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
					+ "WHERE computer.name LIKE :name " + orderBy.toString() + " LIMIT :fromBound,:maxResult";
			argMap.put("name", "%"+name+"%");
		}
		argMap.put("orderBy", orderBy);
		argMap.put("fromBound", fromBound);
		argMap.put("maxResult", maxResult);
		return namedJdbcTemplate.query(sql, argMap, new ComputerMapper());
	}

	@Override
	public void save(Computer computer) throws DataAccessException {
		String sql = "INSERT INTO computer (computerId, name, introduced, discontinued, company_id) VALUES(:id, :name, :intro, :disc, :compId)"
				+ "ON DUPLICATE KEY UPDATE name = :name, introduced = :intro, discontinued = :disc, company_id = :compId";
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("id", computer.getComputerId());
		argMap.put("name", computer.getName());
		argMap.put("intro", new Timestamp(computer.getIntroduced()
				.toDateTimeAtStartOfDay().getMillis()));
		argMap.put("disc", new Timestamp(computer.getDiscontinued()
				.toDateTimeAtStartOfDay().getMillis()));
		if (computer.getCompany() == null) {
			argMap.put("compId", null);
		} else {
			argMap.put("compId", computer.getCompany().getCompanyId());
		}
		namedJdbcTemplate.update(sql, argMap);
	}

	@Override
	public void delete(int id) throws DataAccessException {
		String sql = "DELETE FROM computer WHERE computerId = :toDel";  
		SqlParameterSource namedParameters = new MapSqlParameterSource("toDel", id);  
		namedJdbcTemplate.update(sql, namedParameters);  
	}

	@Override
	public long count() throws DataAccessException {
		String sql = "SELECT COUNT(computerId) FROM computer";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public long countFiltered(String name) throws DataAccessException {
		String sql = "SELECT COUNT(computerId) "
					+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
					+ "WHERE computer.name LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, "%"+name+"%");
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
