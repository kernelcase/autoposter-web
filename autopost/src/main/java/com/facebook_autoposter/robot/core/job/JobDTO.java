package com.facebook_autoposter.robot.core.job;

import java.util.List;

public class JobDTO {	
	
	private Integer idPost;

	private String name;
	
	private String description;
	
	private String link;
	
	private String photo;
	
	private List<JobGroupDTO> groups;
		
	private String facebookUsername;
	
	private String facebookPassword;

	private Integer idJobStatus;
	
	private Integer progress;
	
	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<JobGroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<JobGroupDTO> groups) {
		this.groups = groups;
	}

	public String getFacebookPassword() {
		return facebookPassword;
	}

	public void setFacebookPassword(String facebookPassword) {
		this.facebookPassword = facebookPassword;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public Integer getIdJobStatus() {
		return idJobStatus;
	}

	public void setIdJobStatus(Integer idJobStatus) {
		this.idJobStatus = idJobStatus;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	
	
	
}
