package com.SpringMVC05.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.SpringMVC05.daoi.UserServicesDAOI;
import com.SpringMVC05.entities.User;
//import com.SpringMVC05.services.PersistenceUnitInfo;

public class UserServices implements UserServicesDAOI, PersistenceUnitInfo {
	
//	private static String persistenceUnit = "SpringMVC05";

	@Override
	public boolean addUser(User user) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			result = false;
		}
		
		entityManager.close();
		entityManagerFactory.close();
				
		return result;
	}

	@Override
	public User getUserById(int id) {
		User foundUser = new User();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			foundUser = entityManager.find(User.class, id);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		entityManager.close();
		entityManagerFactory.close();
		
		return foundUser;
	}
	
	@Override
	public User getUserByEmail(String sEmail) {
		User user = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryUserByEmail");
		query.setParameter("pEmail", sEmail);

		try {
			user = (User) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			user = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return user;
	}
	
	@Override
	public User getUserByUsername(String sUsername) {
		User user = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryUserByUsername");
		query.setParameter("pUsername", sUsername);

		try {
			user = (User) query.getSingleResult();			
		}
		catch(NoResultException nre) {
			user = null;
		}

		entityManager.close();
		entityManagerFactory.close();

		return user;
	}
	
	@Override
	public List<User> getAllUsers(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("GetAllUsers");
		
		@SuppressWarnings("unchecked")
		List<User> userList = query.getResultList();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return userList;
	}

	@Override
	public boolean removeUser(int userId) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			User foundUser = entityManager.find(User.class, userId);
			entityManager.remove(foundUser);
			
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			result = false;
		}
		entityManager.close();
		entityManagerFactory.close();
		
		return result;
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = true;
		
		//update user here???
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			User foundUser = entityManager.find(User.class, user.getId());
			foundUser.setUsername(user.getUsername());
			foundUser.setPassword(user.getPassword());
			
			entityManager.getTransaction().commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			result = false;
		}
		
		entityManager.close();
		entityManagerFactory.close();
		
		return result;
	}
	
	@Override
	public boolean validateUser(String sUsername, String sPassword) {
		boolean result = true;
		User user = getUserByUsername(sUsername);

		if (user != null)
			if (!user.getPassword().equals(sPassword)) {
				System.out.println("Wrong Credentials!");
				result = false;
			}
			else {
				result = true;
			}
		else {
			System.out.println("User does not exist!");
			result = false;
		}
		
		return result;
	}

}
