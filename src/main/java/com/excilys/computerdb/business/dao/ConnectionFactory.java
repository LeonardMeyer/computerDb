package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ConnectionFactory {
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	private String dataSource = "java:/computerDS";
	
	public Connection getConnection(){
		Context ic;
		Connection conn = null;
		try {
			ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(dataSource);
			conn = (Connection) ds.getConnection();
		} catch (NamingException | SQLException e) {
			logger.error("Erreur lors de la récupération d'une connexion", e);
		} 
		return conn;
		
	}
}
