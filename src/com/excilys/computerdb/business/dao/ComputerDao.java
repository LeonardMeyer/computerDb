package com.excilys.computerdb.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> findAll() {
		throw new NotImplementedException();
	}

	@Override
	public Computer create(Computer obj) {
		try {
			String query = "INSERT INTO produits (name, introduced, discontinued, company_id)  VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.setString(1, obj.getName());
			stmt.setTimestamp(2, new Timestamp(obj.getIntroduced().getMillis()));
			stmt.setTimestamp(3, new Timestamp(obj.getDiscontinued().getMillis()));
			stmt.setInt(4, obj.getCompanyId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Computer update(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Computer> findAllInRange(int firstBound, int secondBound) {
		List<Computer> foundComputers = new ArrayList<Computer>();
		try {
			String query = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name "
					+ "FROM computer LEFT JOIN company ON (company.id = computer.company_id) "
					+ "ORDER BY computer.name "
					+ "LIMIT ?,?";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
			stmt.setInt(1, firstBound);
			stmt.setInt(2, secondBound);
			ResultSet rs = stmt.executeQuery();
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy").withLocale(Locale.FRANCE);
			while (rs.next()) {
				  int id = rs.getInt("computer.id");
				  String name = rs.getString("computer.name");
				  Timestamp introducedTs = rs.getTimestamp("introduced");
				  DateTime introduced = null;
				  if (introducedTs != null) {
					  introduced = new DateTime(introducedTs);
				  }
				  Timestamp discontinuedTs = rs.getTimestamp("discontinued");
				  DateTime discontinued = null;
				  if (discontinuedTs != null) {
					  discontinued = new DateTime(rs.getTimestamp("discontinued"));
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
	
	

}
