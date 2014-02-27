package com.excilys.computerdb.business.services;

import java.util.List;

import com.excilys.computerdb.business.domain.Company;

public interface ServiceProvider<T> {
	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public T find(long id);
	
	/**
	 * Permet de récupérer tous les objets
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Permet de créer un objet
	 * par rapport à un objet
	 * @param obj
	 */
	public boolean create(T obj);
	
	/**
	 * Permet la suppression d'un objet
	 * @param obj
	 */
	public boolean delete(int id);
}
