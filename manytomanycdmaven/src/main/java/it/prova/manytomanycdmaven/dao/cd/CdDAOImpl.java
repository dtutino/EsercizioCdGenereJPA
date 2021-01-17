package it.prova.manytomanycdmaven.dao.cd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public class CdDAOImpl implements CdDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Cd> list() throws Exception {
		return entityManager.createQuery("from Cd", Cd.class).getResultList();
	}

	@Override
	public Cd get(Long id) throws Exception {
		return entityManager.find(Cd.class, id);
	}

	@Override
	public void update(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Cd input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Cd> findAllByGenere(Genere genereInstance) throws Exception {
		TypedQuery<Cd> query = entityManager.createQuery("select c FROM Cd c join c.generi g where g = :genere", Cd.class);
		query.setParameter("genere", genereInstance);
		return query.getResultList();
	}

}
