package com.SpringMVC05.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.SpringMVC05.daoi.ActivityServicesDAOI;
import com.SpringMVC05.entities.Activity;

public class ActivityServices implements ActivityServicesDAOI, PersistenceUnitInfo {

	@Override
	public List<Activity> getAllActivities() {
		List<Activity> activityList = new ArrayList<Activity>();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryAllActivitys");
		activityList = query.getResultList();
		
		entityManager.close();
		entityManagerFactory.close();

		return activityList;
	}

	@Override
	public Activity getActivityByEmail(String sEmail) {
		Activity activity = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryActivityByEmail");
		query.setParameter("pEmail", sEmail);

		try {
			activity = (Activity) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activity = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return activity;
	}

	@Override
	public void registerNewActivity(String sEmail, int cId) {
		Activity activity = null;
		Query query = null;

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		//
		// Check if activity exists.
		//
		query = entityManager.createNamedQuery("queryActivityById", Activity.class);
		query.setParameter("pId", cId);

		try {
			activity = (Activity) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			activity = null;
			System.out.println("INVALID Activity #");
			return;
		}
		finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public Activity getActivity(String sEmail) {
		return this.getActivityByEmail(sEmail);
	}

	@Override
	public boolean addActivity(Activity activity) {
		boolean result = true;

		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(activity);
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
	public boolean updateActivity(Activity activity) {
		boolean result = true;
		
		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// Get the activity from the database
		Activity temp = entityManager.find(Activity.class, activity.getId());

		try {
			entityManager.getTransaction().begin();
			
			// Modify the activity and persist it back to the database within a transaction.
			temp.setId(activity.getId());
			temp.setName(activity.getName());
			temp.setType(activity.getType());
			
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
	public boolean deleteActivity(Activity activity) {
		boolean result = true;

		// (1) ManagerFactory (2) Manager (3) Begin transaction (4) Persist
		// (5) Commit (6) Close Manager (7) Close ManagerFactory

		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Activity temp = entityManager.find(Activity.class, activity.getId());
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
