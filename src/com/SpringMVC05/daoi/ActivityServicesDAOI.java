package com.SpringMVC05.daoi;

import java.util.List;

import com.SpringMVC05.entities.Activity;

public interface ActivityServicesDAOI {

	List<Activity> getAllActivities();

	Activity getActivityByEmail(String sEmail);

	void registerNewActivity(String sEmail, int cId);

	Activity getActivity(String sEmail);

	boolean addActivity(Activity activity);

	boolean updateActivity(Activity activity);

	boolean deleteActivity(Activity activity);

}
