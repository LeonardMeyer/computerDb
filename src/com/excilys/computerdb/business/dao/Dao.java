package com.excilys.computerdb.business.dao;

import java.sql.Connection;
import java.util.List;

public abstract class Dao<T> {

	protected Connection conn = ConnectionFactory.INSTANCE.getConnection();
	
	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T find(long id);
	
	/**
	 * Permet de récupérer tous les objets
	 * @return
	 */
	public abstract List<T> findAll();
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract boolean create(T obj);
	
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract boolean delete(int id);
	
}


