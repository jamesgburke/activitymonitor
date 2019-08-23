package com.SpringMVC05.daoi;

import java.util.List;

import com.SpringMVC05.entities.ActivityDetails;

public interface ActivityDetailsServicesDAOI {

	List<ActivityDetails> getAllActivityDetails();

	ActivityDetails getActivityDetailsById(int sId);

	void registerNewActivityDetails(String sEmail, int cId);

	boolean addActivityDetails(ActivityDetails activityDetails);

	boolean updateActivityDetails(ActivityDetails activityDetails);

	boolean deleteActivityDetails(ActivityDetails activityDetails);

	ActivityDetails getActivityDetailsByName(String sName);

}
