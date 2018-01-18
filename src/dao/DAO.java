package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import entity.UserEntity;

public class DAO {
	
	private String configName = "crowdfunding_project";
	private EntityManagerFactory 	emf;
	private EntityManager 			em;
	private EntityTransaction 		t;
	
	public void init() {
		emf = Persistence.createEntityManagerFactory(configName);
		em 	= emf.createEntityManager();
		t 	= em.getTransaction();
	}
	
	public void destroy() {
		em.close();
	}
	
	public void persist(Object o) {
		init();
		t.begin();
		em.persist(o);
		t.commit();
		destroy();
	}
	
}
