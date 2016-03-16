package com.facebook_autoposter.robot.core.job;

public class JobGroupDTO {

	private Integer idJobGroup;
	
	private String groupUrl;
	
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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}
