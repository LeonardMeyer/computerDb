package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdb.business.domain.Company;

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
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}
	
	public Map<Integer, String> findAllNames() {
		//LinkedHashMap pour retenir l'ordre d'insertion
		Map<Integer, String> foundNames = new LinkedHashMap<Integer, String>();
		String query = "SELECT DISTINCT * FROM company ORDER BY name";
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if(!DbSession.isSessionOpened()) {  
				DbSession.openSession(false);
			}  
			Connection conn = DbSession.currentConnection();
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int companyId = rs.getInt("id");
				String companyName = rs.getString("name");
				foundNames.put(companyId, companyName);
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL dans la requête findAllNames", e);
		} catch (DbSessionException e) {
			logger.error("Erreur de session à la base de données", e);
		}finally{
			try {
				stmt.close();
				rs.close();
				DbSession.closeSession();
			} catch (DbSessionException e) {
				logger.error("Erreur de session à la base de données", e);
			} catch (SQLException e) {
				logger.error("Erreur SQL lors de la fermeture", e);
			}
		}
		
		return foundNames;	
	}

}
