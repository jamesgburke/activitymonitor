package com.SpringMVC05.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@IdClass (ActivityDetailsID.class)
@Table
@NamedQueries ({
	@NamedQuery (query="select ad from ActivityDetails ad", name="queryAllActivityDetails"),
	@NamedQuery (query="select ad from ActivityDetails ad where ad.activityDetailsId = :pActivityDetailsId", name="queryActivityDetailstById"),
	@NamedQuery (query="select ad from ActivityDetails ad where ad.detailName = :pDetailName", name="queryActivityDetailstByDetailName")
})
public class ActivityDetails {
	
	@Id
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int activityDetailsId;
	
//	@OneToOne
	@Id
//	@JoinColumn(name="activityid", referencedColumnName="id")
	int activityId;
	
	@Column(name="detailname", length=50, unique=true, nullable=false)
	String detailName;
	
	@Column(name="detailValue", length=50, nullable=false)
	String detailValue;
	
	@Column(name="detaildatatype", length=50, nullable=false)
	String detailDataType;
	
	@Column(name="dateEngaged", nullable=true)
	LocalDate dateEngaged;

	
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
	
	public String getDetailName() {
		return detailName;
	}
	
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	
	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}
	
	public String getDetailDataType() {
		return detailDataType;
	}
	
	public void setDetailDataType(String detailDataType) {
		this.detailDataType = detailDataType;
	}
	
	public LocalDate getDateEngaged() {
		return dateEngaged;
	}
	
	public void setDateEngaged(LocalDate dateEngaged) {
		this.dateEngaged = dateEngaged;
	}

	public ActivityDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityDetails(int activityDetailsId, int activityId, String detailName, String detailValue,
			String detailDataType, LocalDate dateEngaged) {
		super();
		this.activityDetailsId = activityDetailsId;
		this.activityId = activityId;
		this.detailName = detailName;
		this.detailValue = detailValue;
		this.detailDataType = detailDataType;
		this.dateEngaged = dateEngaged;
	}

	@Override
	public String toString() {
		return "ActivityDetails [activityDetailsId=" + activityDetailsId + ", activityId=" + activityId
				+ ", detailName=" + detailName + ", detailValue=" + detailValue + ", detailDataType=" + detailDataType
				+ ", dateEngaged=" + dateEngaged + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activityDetailsId;
		result = prime * result + activityId;
		result = prime * result + ((dateEngaged == null) ? 0 : dateEngaged.hashCode());
		result = prime * result + ((detailDataType == null) ? 0 : detailDataType.hashCode());
		result = prime * result + ((detailName == null) ? 0 : detailName.hashCode());
		result = prime * result + ((detailValue == null) ? 0 : detailValue.hashCode());
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
		ActivityDetails other = (ActivityDetails) obj;
		if (activityDetailsId != other.activityDetailsId)
			return false;
		if (activityId != other.activityId)
			return false;
		if (dateEngaged == null) {
			if (other.dateEngaged != null)
				return false;
		} else if (!dateEngaged.equals(other.dateEngaged))
			return false;
		if (detailDataType == null) {
			if (other.detailDataType != null)
				return false;
		} else if (!detailDataType.equals(other.detailDataType))
			return false;
		if (detailName == null) {
			if (other.detailName != null)
				return false;
		} else if (!detailName.equals(other.detailName))
			return false;
		if (detailValue == null) {
			if (other.detailValue != null)
				return false;
		} else if (!detailValue.equals(other.detailValue))
			return false;
		return true;
	}

}
