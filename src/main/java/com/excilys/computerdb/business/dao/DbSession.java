package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class DbSession {
		
		private Logger logger = LoggerFactory.getLogger(DbSession.class);
		private ThreadLocal<Connection> session = new ThreadLocal<Connection>();  
		
		private ConnectionFactory connFactory;

		public ConnectionFactory getConnFactory() {
			return connFactory;
		}

	  
	    public Connection currentConnection() throws DbSessionException {  
	        Connection conn = session.get();  
	        if (conn == null) {  
	            throw new DbSessionException("Echec lors de la récupération de la session");  
	        }  
	        return conn;  
	    }  
	  
	    public void closeSession() throws DbSessionException{ 
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
	  
	    public void openSession(final boolean isBeginTransaction) throws DbSessionException {  
	        Connection conn = session.get();  
	        if (conn != null) {  
	            throw new DbSessionException("La session est déjà ouverte");  
	        }  
	        Connection newConn = connFactory.getConnection();  
	        try {
	        	newConn.setAutoCommit(isBeginTransaction);
			} catch (SQLException e) {
				throw new DbSessionException("Erreur lors du setAutoCommit de openSession", e);
			}  
	        conn = newConn;  
	        session.set(conn);  
	    }  
	    
	    @Autowired
	    public void setConnFactory(ConnectionFactory connFactory) {
			this.connFactory = connFactory;
		}

		public boolean isSessionOpened() {  
	        return session.get() != null;  
	    }  
		

}
