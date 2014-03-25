package com.excilys.computerdb.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
import com.excilys.computerdb.business.domain.QCompany;
import com.excilys.computerdb.business.domain.QComputer;
import com.mysema.query.jpa.impl.JPAQuery;

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

		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(em);
		query.from(computer).leftJoin(computer.company, company);
		if (name != null) {
			query.where(computer.name.like("%"+name+"%"));
		}

		switch (orderBy) {
		case COMPANY_ASC:
			query.orderBy(company.name.asc());
			break;
		case COMPANY_DESC:
			query.orderBy(company.name.desc());
			break;
		case DISC_ASC:
			query.orderBy(computer.discontinued.asc());
			break;
		case DISC_DESC:
			query.orderBy(computer.discontinued.desc());
			break;
		case INTRO_ASC:
			query.orderBy(computer.introduced.asc());
			break;
		case INTRO_DESC:
			query.orderBy(computer.introduced.desc());
			break;
		case NAME_ASC:
			query.orderBy(computer.name.asc());
			break;
		case NAME_DESC:
			query.orderBy(computer.name.desc());
			break;
		default:
			query.orderBy(computer.name.asc());
			break;
		}
		return query.limit(maxResult).offset(fromBound).list(computer);
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
		QComputer computer = QComputer.computer;
		JPAQuery query = new JPAQuery(em);
		query.from(computer);
		return query.count();
	}

	@Override
	public long countFiltered(String name) throws DataAccessException {
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(em);
		query.from(computer).leftJoin(computer.company, company);

		if (name != null) {
			query.where(computer.name.like("%"+name+"%"));
		}

		return query.count();
	}

}
