package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbSession {
		
		private Logger logger = LoggerFactory.getLogger(DbSession.class);
		private static final ThreadLocal<Connection> session = new ThreadLocal<Connection>();  

		private DbSession() {
		}
		
		public static boolean isSessionOpened() {  
	        return session.get() != null;  
	    }  
	  
	    public static Connection currentConnection() throws DbSessionException {  
	        Connection conn = session.get();  
	        if (conn == null) {  
	            throw new DbSessionException("Echec lors de la récupération de la session");  
	        }  
	        return conn;  
	    }  
	  
	    public static void closeSession() throws DbSessionException{ 
	    	Connection conn = null;
	        try {
	        	conn = currentConnection();
				conn.close();
			} catch (SQLException e) {
				throw new DbSessionException("Erreur lors la fermeture de connexion", e);
			}  
	        conn = null;  
	        session.set(null);  
	    }  
	  
	    public static void openSession(boolean isBeginTransaction) throws DbSessionException {  
	        Connection conn = session.get();  
	        if (conn != null) {  
	            throw new DbSessionException("La session est déjà ouverte");  
	        }  
	        Connection newConn = ConnectionFactory.INSTANCE.getConnection();  
	        try {
	        	newConn.setAutoCommit(isBeginTransaction);
			} catch (SQLException e) {
				throw new DbSessionException("Erreur lors du setAutoCommit de openSession", e);
			}  
	        conn = newConn;  
	        session.set(conn);  
	    }  
		

}
