package com.SpringMVC05.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.SpringMVC05.entities.Activity;

import springwork.customAnnotations.EmailConstraint;

@Entity
@Table
@NamedQueries ({
	@NamedQuery(query="select u from User u", name="queryAllUsers"),
	@NamedQuery(query="select u from User u where u.email = :pEmail", name="queryUserByEmail"),
	@NamedQuery(query="select u from User u where u.username = :pUsername", name="queryUserByUsername")	
})
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "username", length = 30, unique = true, nullable = false)
	@NotNull
	@Size (min=5, max=14, message="User name must be between {2} and {1} characters.")
	private String username;
	
	@Column (name = "password", length = 20, nullable = false)
	@NotNull
	@Size (min=5, max=14, message="Password must be between {2} and {1} characters.")
	private String password;
	
	@EmailConstraint
	private String email;
	
	@OneToMany
	private List<Activity> activityList;

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Activity> getActivityList() {
		return activityList;
	}
	
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id,
			@NotNull @Size(min = 5, max = 14, message = "User name must be between {2} and {1} characters.") String username,
			@NotNull @Size(min = 5, max = 14, message = "Password must be between {2} and {1} characters.") String password,
			String email, List<Activity> activityList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.activityList = activityList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", activityList=" + activityList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityList == null) ? 0 : activityList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (activityList == null) {
			if (other.activityList != null)
				return false;
		} else if (!activityList.equals(other.activityList))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
