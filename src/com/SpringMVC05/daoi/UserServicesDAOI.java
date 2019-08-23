package com.SpringMVC05.daoi;

import java.util.List;

import com.SpringMVC05.entities.User;

public interface UserServicesDAOI {

	boolean addUser(User user);

	User getUserById(int id);

	User getUserByEmail(String email);

	List<User> getAllUsers();

	boolean removeUser(int userId);

	boolean updateUser(User user);

	boolean validateUser(String sEmail, String sPassword);

	User getUserByUsername(String sUsername);

}
