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
import com.excilys.computerdb.business.domain.QCompany;
import com.excilys.computerdb.business.domain.QComputer;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
@Profile(value="jpa")
public class JpaCompanyRepository implements CompanyRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Company> findAll() throws DataAccessException {
		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(em);
		query.from(company).orderBy(company.name.asc());
		return query.list(company);
	}

	@Override
	public Company findById(int id) throws DataAccessException {
		return em.find(Company.class, id);
	}

}
