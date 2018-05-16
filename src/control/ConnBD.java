package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnBD 
{
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void connect()
	{
		  entityManagerFactory=Persistence.createEntityManagerFactory("test");
		  entityManager=entityManagerFactory.createEntityManager();
		  System.out.println("create tablespace successfully");
	}
	
	public void close()
	{
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("ferme tablespace successfully");
	}  
}
