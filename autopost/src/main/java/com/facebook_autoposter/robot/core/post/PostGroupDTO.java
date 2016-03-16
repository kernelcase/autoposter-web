package com.facebook_autoposter.robot.core.post;

public class PostGroupDTO {

	private Integer idPostGroup;
	
	private String groupUrl;
	
	private Boolean success;

	public Integer getIdPostGroup() {
		return idPostGroup;
	}

	public void setIdPostGroup(Integer idPostGroup) {
		this.idPostGroup = idPostGroup;
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
