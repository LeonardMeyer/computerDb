package com.excilys.computerdb.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.CompanyRepository;
import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.Computer;

@Repository
@Profile(value="jpa")
public class JpaCompanyRepository implements CompanyRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Company> findAll() throws DataAccessException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> root = criteria.from(Company.class);
		criteria.orderBy(builder.asc(root.get("name")));
		criteria.select(root);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Company findById(int id) throws DataAccessException {
		return em.find(Company.class, id);
	}

}
