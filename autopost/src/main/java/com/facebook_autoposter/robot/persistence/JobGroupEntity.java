package com.facebook_autoposter.robot.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JobGroup")
public class JobGroupEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdPJobGroup")
	private Integer idJobGroup;
	
	@Column(name="GroupUrl")
	private String groupUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdJob")
	private JobEntity job;
	
	@Column(name="Success")
	private Boolean success;

	public Integer getIdJobGroup() {
		return idJobGroup;
	}

	public void setIdJobGroup(Integer idJobGroup) {
		this.idJobGroup = idJobGroup;
	}

	public String getGroupUrl() {
		return groupUrl;
	}

	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	public JobEntity getJob() {
		return job;
	}

	public void setJob(JobEntity job) {
		this.job = job;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	
}
