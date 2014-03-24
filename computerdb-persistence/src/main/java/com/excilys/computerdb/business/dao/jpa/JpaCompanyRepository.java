package com.excilys.computerdb.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.CompanyRepository;
import com.excilys.computerdb.business.domain.Company;

@Repository
@Profile(value="jpa")
public class JpaCompanyRepository implements CompanyRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll() throws DataAccessException {
		Query query = em.createQuery("FROM Company ORDER BY name");
		return query.getResultList();
	}

	@Override
	public Company findById(int id) throws DataAccessException {
		Query query = em.createQuery("FROM Company WHERE id = :id");	
		query.setParameter("id", id);
		return (Company) query.getSingleResult();
	}

}
