package com.excilys.computerdb.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdb.business.domain.Computer;

public class ComputerDao extends Dao<Computer> {
	
	private Logger logger = LoggerFactory.getLogger(ComputerDao.class);
	
	private static volatile ComputerDao instance = null;

	private ComputerDao() {
	}

	public static ComputerDao getInstance() {
		if (instance == null) {
			synchronized (ComputerDao.class) {
				// Double check
				if (instance == null) {
					instance = new ComputerDao();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Computer find(long id) {
		Computer comp = null;
		String query = "SELECT * FROM computer LEFT JOIN company ON (company.id = computer.company_id) WHERE computer.id = ?";
		try(PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				  int id2 = rs.getInt("id");
				  String name = rs.getString("name");
				  Timestamp introducedTs = rs.getTimestamp("introduced");
				  LocalDate introduced = null;
				  if (introducedTs != null) {
					  introduced = new LocalDate(introducedTs);
				  }
				  Timestamp discontinuedTs = rs.getTimestamp("discontinued");
				  LocalDate discontinued = null;
				  if (discontinuedTs != null) {
					  discontinued = new LocalDate(discontinuedTs);
				  }
				  int companyId = rs.getInt("company_id");
				  String companyName = rs.getString("company.name");

				  comp = new Computer(id2, name, introduced, discontinued, companyId, companyName);
				}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête find Computer", e);
		}
		return comp;
	}

	@Override
	public List<Computer> findAll() {
		return null;
		
	}

	@Override
	public boolean create(Computer obj) {
		String query = "INSERT INTO computer (id, name, introduced, discontinued, company_id)  VALUES(?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE name = ?, introduced = ?, discontinued = ?, company_id = ?";
		boolean success = false;
		try(PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
			stmt.setInt(1, obj.getComputerId());
			stmt.setString(2, obj.getName());
			stmt.setTimestamp(3, new Timestamp(obj.getIntroduced().toDateTimeAtStartOfDay().getMillis()));
			stmt.setTimestamp(4, new Timestamp(obj.getDiscontinued().toDateTimeAtStartOfDay().getMillis()));
			if (obj.getCompanyId() == -1) {
				stmt.setNull(5, Types.INTEGER);
			}else {
				stmt.setInt(5, obj.getCompanyId());
			}

			stmt.setString(6, obj.getName());
			stmt.setTimestamp(7, new Timestamp(obj.getIntroduced().toDateTimeAtStartOfDay().getMillis()));
			stmt.setTimestamp(8, new Timestamp(obj.getDiscontinued().toDateTimeAtStartOfDay().getMillis()));
			if (obj.getCompanyId() == -1) {
				stmt.setNull(9, Types.INTEGER);
			}else {
				stmt.setInt(9, obj.getCompanyId());
			}
			
			int rowsCreated = stmt.executeUpdate();
			if (rowsCreated > 0) {
				success = true;
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête create Computer", e);
			return false;
		}
		
		return success;
	}

	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM computer WHERE id = ?";
		boolean success = false;
		try(PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			int rowsDeleted = stmt.executeUpdate();
			if (rowsDeleted > 0) {
				success = true;
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête delete Computer", e);
		}
		
		return success;
	}
	
	public List<Computer> findAllInRange(int firstBound, int secondBound) {
		List<Computer> foundComputers = new ArrayList<Computer>();
		String query = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
				+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
				+ "ORDER BY computer.name "
				+ "LIMIT ?,?";
		try(PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
			stmt.setInt(1, firstBound);
			stmt.setInt(2, secondBound);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				  int id = rs.getInt("computer.id");
				  String name = rs.getString("computer.name");
				  Timestamp introducedTs = rs.getTimestamp("introduced");
				  LocalDate introduced = null;
				  if (introducedTs != null) {
					  introduced = new LocalDate(introducedTs);
				  }
				  Timestamp discontinuedTs = rs.getTimestamp("discontinued");
				  LocalDate discontinued = null;
				  if (discontinuedTs != null) {
					  discontinued = new LocalDate(discontinuedTs);
				  }
				  int companyId = rs.getInt("company_id");
				  String companyName = rs.getString("company.name");

				  Computer prod = new Computer(id, name, introduced, discontinued, companyId, companyName);
				  foundComputers.add(prod);

				}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête findAllInRange", e);
		}
		
		return foundComputers;
	}
	
	public List<Computer> filterByName(String toSearch) {
		List<Computer> foundComputers = new ArrayList<Computer>();
		String query = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
				+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
				+ "WHERE computer.name LIKE ?"
				+ "ORDER BY computer.name";
		try(PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
			stmt.setString(1, "%" + toSearch + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				  int id = rs.getInt("computer.id");
				  String name = rs.getString("computer.name");
				  Timestamp introducedTs = rs.getTimestamp("introduced");
				  LocalDate introduced = null;
				  if (introducedTs != null) {
					  introduced = new LocalDate(introducedTs);
				  }
				  Timestamp discontinuedTs = rs.getTimestamp("discontinued");
				  LocalDate discontinued = null;
				  if (discontinuedTs != null) {
					  discontinued = new LocalDate(discontinuedTs);
				  }
				  int companyId = rs.getInt("company_id");
				  String companyName = rs.getString("company.name");

				  Computer prod = new Computer(id, name, introduced, discontinued, companyId, companyName);
				  foundComputers.add(prod);

				}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête filterByName", e);
		}
		
		return foundComputers;
	}
	
	public int count() {
		String query = "SELECT COUNT(id) FROM computer";
		int count = 0;
		try(Statement stmt = (Statement) conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête Computer count", e);
		}
		
		return count;
	}
	
	

}
