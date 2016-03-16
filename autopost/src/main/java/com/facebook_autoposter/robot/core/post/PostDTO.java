package com.facebook_autoposter.robot.core.post;

import java.util.List;

public class PostDTO {
	
	private Integer idPost;

	private String name;
	
	private String description;
	
	private String link;
	
	private String photo;
	
	private List<PostGroupDTO> groups;
		
	private String facebookUsername;	

	private Integer idPostStatus;
	
	private String nextPublish;
	
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

	public List<PostGroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<PostGroupDTO> groups) {
		this.groups = groups;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public Integer getIdPostStatus() {
		return idPostStatus;
	}

	public void setIdPostStatus(Integer idPostStatus) {
		this.idPostStatus = idPostStatus;
	}

	public String getNextPublish() {
		return nextPublish;
	}

	public void setNextPublish(String nextPublish) {
		this.nextPublish = nextPublish;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	
}
