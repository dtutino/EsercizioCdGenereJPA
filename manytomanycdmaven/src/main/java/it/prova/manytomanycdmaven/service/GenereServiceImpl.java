package it.prova.manytomanycdmaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.manytomanycdmaven.dao.EntityManagerUtil;
import it.prova.manytomanycdmaven.dao.MyDaoFactory;
import it.prova.manytomanycdmaven.dao.cd.CdDAO;
import it.prova.manytomanycdmaven.dao.genere.GenereDAO;
import it.prova.manytomanycdmaven.model.Cd;
import it.prova.manytomanycdmaven.model.Genere;

public class GenereServiceImpl implements GenereService {

	private GenereDAO genereDAO;
	
	private CdDAO cdDAO;

	@Override
	public List<Genere> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Genere caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			genereDAO.update(genereInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Genere genereInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			genereDAO.insert(genereInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Genere genereInstance) throws Exception {
EntityManager entityManager = EntityManagerUtil.getEntityManager();
		

		try {

			entityManager.getTransaction().begin();
			
			cdDAO = MyDaoFactory.getCdDAOInstance();
			cdDAO.setEntityManager(entityManager);

			List<Cd> lista = cdDAO.findAllByGenere(genereInstance);
			
			for (Cd cdItem : lista) {
				cdItem.getGeneri().remove(genereInstance);
				cdDAO.update(cdItem);
			} 

			genereDAO.setEntityManager(entityManager);
			
			genereDAO.delete(genereInstance);
			
			entityManager.getTransaction().commit();			
		
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Genere cercaPerDescrizione(String descrizione) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return genereDAO.findByDescrizione(descrizione);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void aggiungiCd(Genere genereInstance, Cd cdInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			genereDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			genereInstance = entityManager.merge(genereInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			cdInstance = entityManager.merge(cdInstance);

			genereInstance.getCds().add(cdInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setGenereDAO(GenereDAO genereDAO) {
		this.genereDAO = genereDAO;
	}

}
