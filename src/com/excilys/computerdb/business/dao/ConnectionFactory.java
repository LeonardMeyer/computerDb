package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum ConnectionFactory {
	INSTANCE;
	private String dataSource = "java:/computerDS";
	
	public Connection getConnection(){
		Context ic;
		Connection conn = null;
		try {
			ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(dataSource);
			conn = (Connection) ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
}
