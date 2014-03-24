package com.excilys.computerdb.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.Computer;

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
	public List<Computer> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> search(String name, SearchOrder orderBy,
			int fromBound, int maxResult) throws DataRetrievalFailureException {
		Query query;
		if (name == null) {
			query = em.createQuery("SELECT computer FROM Computer AS computer "
					+ "LEFT JOIN computer.company company "
					+ orderBy.toString());
		} else {
			query = em.createQuery("SELECT computer FROM Computer AS computer "
					+ "LEFT JOIN computer.company company WHERE computer.name LIKE :name "
					+ orderBy.toString());
			query.setParameter("name", "%" + name + "%");
		}
		query.setFirstResult(fromBound);
		query.setMaxResults(maxResult);
		return query.getResultList();
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
		Query query = em.createQuery("SELECT COUNT(computer) FROM Computer AS computer");
		return (long) query.getSingleResult();
	}

	@Override
	public long countFiltered(String name) throws DataAccessException {
		Query query = em.createQuery("SELECT COUNT(computer) "
				+ "FROM Computer AS computer LEFT JOIN computer.company company "
				+ "WHERE computer.name LIKE :name");
		query.setParameter("name", "%" + name + "%");
		return (long) query.getSingleResult();
	}

}
