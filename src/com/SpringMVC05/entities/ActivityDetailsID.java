package com.SpringMVC05.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class ActivityDetailsID  implements Serializable {
	
	/**
	 * If a serializable class does not explicitly declare a serialVersionUID, 
	 * then the serialization runtime will calculate a default serialVersionUID 
	 * value for that class based on various aspects of the class, as described 
	 * in the Java(TM) Object Serialization Specification. However, it is strongly 
	 * recommended that all serializable classes explicitly declare serialVersionUID 
	 * values, since the default serialVersionUID computation is highly sensitive 
	 * to class details that may vary depending on compiler implementations, and 
	 * can thus result in unexpected InvalidClassExceptions during deserialization. 
	 * Therefore, to guarantee a consistent serialVersionUID value across different 
	 * java compiler implementations, a serializable class must declare an explicit 
	 * serialVersionUID value. It is also strongly advised that explicit 
	 * serialVersionUID declarations use the private modifier where possible, since 
	 * such declarations apply only to the immediately declaring class--serialVersionUID 
	 * fields are not useful as inherited members. Array classes cannot declare an 
	 * explicit serialVersionUID, so they always have the default computed value, but 
	 * the requirement for matching serialVersionUID values is waived for array classes.
	 * 
	 * @see java.io.Serializable
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private int activityDetailsId;
	private int activityId;

	
	public int getActivityDetailsId() {
		return activityDetailsId;
	}

	public void setActivityDetailsId(int activityDetailsId) {
		this.activityDetailsId = activityDetailsId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public ActivityDetailsID() {
		super();
	}

	public ActivityDetailsID(int activityDetailsId, int activityId) {
		super();
		this.activityDetailsId = activityDetailsId;
		this.activityId = activityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityDetailsId;
		result = prime * result + activityId;
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
		ActivityDetailsID other = (ActivityDetailsID) obj;
		if (activityDetailsId != other.activityDetailsId)
			return false;
		if (activityId != other.activityId)
			return false;
		return true;
	}

}
