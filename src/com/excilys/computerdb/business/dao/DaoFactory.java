/**
 * 
 */
package com.excilys.computerdb.business.dao;

import java.sql.Connection;

/**
 * @author Léonard
 * Classe de fabrique des Dao
 */
public class DaoFactory{
	
	public static CompanyDao getCompanyDao(){
		return CompanyDao.getInstance();
	}
	
	public static ComputerDao getComputerDao(){
		return ComputerDao.getInstance();
	}
	
}
