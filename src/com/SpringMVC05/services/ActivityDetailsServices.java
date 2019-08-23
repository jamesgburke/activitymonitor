package com.SpringMVC05.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.SpringMVC05.daoi.ActivityDetailsServicesDAOI;
import com.SpringMVC05.entities.ActivityDetails;
import com.SpringMVC05.entities.ActivityDetails;

public class ActivityDetailsServices implements ActivityDetailsServicesDAOI, PersistenceUnitInfo {

	@Override
	public List<ActivityDetails> getAllActivityDetails() {
		List<ActivityDetails> activityDetailsList = new ArrayList<ActivityDetails>();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryAllActivityDetails");
		activityDetailsList = query.getResultList();
		
		entityManager.close();
		entityManagerFactory.close();

		return activityDetailsList;
	}

	@Override
	public ActivityDetails getActivityDetailsById(int sId) {
		ActivityDetails activityDetails = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryActivityDetailsById");
		query.setParameter("pActivityDetailsId", sId);

		try {
			activityDetails = (ActivityDetails) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activityDetails = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return activityDetails;
	}
	
	@Override
	public ActivityDetails getActivityDetailsByName(String sName) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryActivityDetailstByDetailName");
		query.setParameter("pName", sName);
		ActivityDetails activityDetails = null;
		
		try {
			activityDetails = (ActivityDetails) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activityDetails = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return activityDetails;
	}

	@Override
	public void registerNewActivityDetails(String sEmail, int cId) {
		ActivityDetails activityDetails = null;
		Query query = null;

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		//
		// Check if activity exists.
		//
		query = entityManager.createNamedQuery("queryActivityDetailsById", ActivityDetails.class);
		query.setParameter("pId", cId);

		try {
			activityDetails = (ActivityDetails) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activityDetails = null;
			System.out.println("INVALID ActivityDetails #");
			return;
		}
		finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public boolean addActivityDetails(ActivityDetails activityDetails) {
		boolean result = true;

		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(activityDetails);
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
	public boolean updateActivityDetails(ActivityDetails activityDetails) {
		boolean result = true;
		
		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// Get the activity details from the database
		ActivityDetails temp = entityManager.find(ActivityDetails.class, activityDetails.getActivityDetailsId());

		try {
			entityManager.getTransaction().begin();
			
			// Modify the activity and persist it back to the database within a transaction.
			temp.setActivityDetailsId(activityDetails.getActivityDetailsId());
			temp.setActivityId(activityDetails.getActivityId());
			temp.setDateEngaged(activityDetails.getDateEngaged());
			temp.setDetailDataType(activityDetails.getDetailDataType());
			temp.setDetailName(activityDetails.getDetailName());
			temp.setDetailValue(activityDetails.getDetailValue());
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
	public boolean deleteActivityDetails(ActivityDetails activityDetails) {
		boolean result = true;

		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			ActivityDetails temp = entityManager.find(ActivityDetails.class, activityDetails.getActivityDetailsId());
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
