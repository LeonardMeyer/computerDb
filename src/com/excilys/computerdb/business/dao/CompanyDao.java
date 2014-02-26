package com.excilys.computerdb.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.Computer;

public class CompanyDao extends Dao<Company>{
	
	private Logger logger = LoggerFactory.getLogger(CompanyDao.class);
	
	private static volatile CompanyDao instance = null;

	private CompanyDao() {
		
	}

	public static CompanyDao getInstance() {
		if (instance == null) {
			synchronized (CompanyDao.class) {
				// Double check
				if (instance == null) {
					instance = new CompanyDao();
				}
			}
		}
		return instance;
	}

	@Override
	public Company find(long id) {
		return null;
	}
	
	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company create(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<Integer, String> findAllCompanyNames() {
		Map<Integer, String> foundNames = new HashMap<Integer, String>();
		try {
			String query = "SELECT DISTINCT * FROM company ORDER BY name";
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int companyId = rs.getInt("id");
				String companyName = rs.getString("name");
				foundNames.put(companyId, companyName);
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête findAllInRange", e);
		}
		
		return foundNames;	
	}

}
