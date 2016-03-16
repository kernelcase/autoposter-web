package com.facebook_autoposter.robot.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PostGroup")
public class PostGroupEntity {

	@Id
	@Column(name="IdPostGroup")
	private Integer idPostGroup;
	
	@Column(name="GroupUrl")
	private String groupUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdPost")
	private PostEntity post;
	
	@Column(name="Success")
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

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}
