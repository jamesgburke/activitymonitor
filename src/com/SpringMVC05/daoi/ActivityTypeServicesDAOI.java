package com.SpringMVC05.daoi;

import java.util.List;

import com.SpringMVC05.entities.ActivityType;

public interface ActivityTypeServicesDAOI {
	
	List<ActivityType> getAllActivityTypes();

	ActivityType getActivityTypeByName(String sName);

	ActivityType getActivityType(String sName);

	boolean addActivityType(ActivityType activityType);

	boolean updateActivityType(ActivityType activityType);

	boolean deleteActivityType(ActivityType activityType);

}
