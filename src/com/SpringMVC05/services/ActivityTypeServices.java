package com.SpringMVC05.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.SpringMVC05.daoi.ActivityTypeServicesDAOI;
import com.SpringMVC05.entities.ActivityType;


public class ActivityTypeServices implements ActivityTypeServicesDAOI, PersistenceUnitInfo {

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityType> getAllActivityTypes() {
		List<ActivityType> activityTypeList = new ArrayList<ActivityType>();

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryAllActivityTypes");
		activityTypeList = (List<ActivityType>) query.getResultList();
		
		entityManager.close();
		entityManagerFactory.close();

		return activityTypeList;
	}

	@Override
	public ActivityType getActivityTypeByName(String sName) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryActivityTypeByName");
		query.setParameter("pName", sName);
		ActivityType activityType = null;
		
		try {
			activityType = (ActivityType) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activityType = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return activityType;
	}

	@Override
	public ActivityType getActivityType(String sName) {
		return this.getActivityTypeByName(sName);
	}

	@Override
	public boolean addActivityType(ActivityType activityType) {
		boolean result = true;

		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(activityType);
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e) {
			e.getMessage();
			result = false;
		}
		finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}

	@Override
	public boolean updateActivityType(ActivityType activityType) {
		
		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// Get the activityType from the database, then update it base on the 
		// passed in activityType's attributes.
		ActivityType temp = entityManager.find(ActivityType.class, activityType.getId());
		boolean result = true;
		
		try {
			entityManager.getTransaction().begin();
			
			// Modify the activityType and persist it back to the database within a transaction.
			temp.setName(activityType.getName());
			
			entityManager.persist(temp);
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e) {
			e.getMessage();
			result = false;
		}
		finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}
	
	@Override
	public boolean deleteActivityType(ActivityType activityType) {
		
		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean result = true;

		try {
			entityManager.getTransaction().begin();
			ActivityType temp = entityManager.find(ActivityType.class, activityType.getId());
			entityManager.remove(temp);
			entityManager.getTransaction().commit();
		}
		catch(PersistenceException e) {
			e.getMessage();
			result = false;
		}
		finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}

}

