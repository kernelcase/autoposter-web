package com.facebook_autoposter.robot.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Job")
public class JobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdJob")
	private Integer idJob;
	
	@Column(name="IdPost")
	private Integer idPost;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Link")
	private String link;
	
	@Column(name="Description")
	private String description;

	@Column(name="Photo")
	private String photo;
	
	@Column(name="FacebookUsername")
	private String facebookUsername;

	@Column(name="FacebookPassword")
	private String facebookPassword;
	
	@Column(name="IdJobStatus")
	private Integer idJobStatus;
	
	@Column(name="Progress")
	private Integer progress;

	public Integer getIdJob() {
		return idJob;
	}

	public void setIdJob(Integer idJob) {
		this.idJob = idJob;
	}

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public String getFacebookPassword() {
		return facebookPassword;
	}

	public void setFacebookPassword(String facebookPassword) {
		this.facebookPassword = facebookPassword;
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
