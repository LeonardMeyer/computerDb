package com.excilys.computerdb.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.Computer;
import com.mysql.jdbc.NotImplemented;

@Repository
@Profile(value = "jpa")
public class JpaComputerRepository implements ComputerRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Computer findById(int id) throws DataRetrievalFailureException {
		return em.find(Computer.class, id);
	}


	@Override
	public List<Computer> search(String name, SearchOrder orderBy,
			int fromBound, int maxResult) throws DataRetrievalFailureException {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class);
		Root<Computer> root = criteria.from(Computer.class);
		Join<Computer, Company> company = root.join("company", JoinType.LEFT);
		criteria.select(root);
		if (name != null) {
			String search = new StringBuilder("%").append(name).append("%")
					.toString();
			criteria.where(builder.like(root.get("name").as(String.class),
					search));
		}

		switch (orderBy) {
		case COMPANY_ASC:
			criteria.orderBy(builder.asc(company.get("name")));
			break;
		case COMPANY_DESC:
			criteria.orderBy(builder.desc(company.get("name")));
			break;
		case DISC_ASC:
			criteria.orderBy(builder.asc(root.get("discontinued")));
			break;
		case DISC_DESC:
			criteria.orderBy(builder.desc(root.get("discontinued")));
			break;
		case INTRO_ASC:
			criteria.orderBy(builder.asc(root.get("introduced")));
			break;
		case INTRO_DESC:
			criteria.orderBy(builder.desc(root.get("introduced")));
			break;
		case NAME_ASC:
			criteria.orderBy(builder.asc(root.get("name")));
			break;
		case NAME_DESC:
			criteria.orderBy(builder.desc(root.get("name")));
			break;
		default:
			criteria.orderBy(builder.asc(root.get("name")));
			break;
		}
		return (List<Computer>) em.createQuery(criteria)
				.setFirstResult(fromBound).setMaxResults(maxResult)
				.getResultList();
	}

	@Override
	public void save(Computer computer) throws DataAccessException {
		em.merge(computer);
	}

	@Override
	public void delete(int id) throws DataAccessException {
		em.remove(em.find(Computer.class, id));

	}

	@Override
	public long count() throws DataAccessException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Computer> root = criteria.from(Computer.class);
		criteria.select(builder.count(root));
		root.join("company", JoinType.LEFT);

		return (long) em.createQuery(criteria).getSingleResult();
	}

	@Override
	public long countFiltered(String name) throws DataAccessException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Computer> root = criteria.from(Computer.class);
		criteria.select(builder.count(root));
		root.join("company", JoinType.LEFT);

		if (name != null) {
			String search = new StringBuilder("%").append(name).append("%")
					.toString();
			criteria.where(builder.like(root.get("name").as(String.class),
					search));
		}

		return (long) em.createQuery(criteria).getSingleResult();
	}

}
