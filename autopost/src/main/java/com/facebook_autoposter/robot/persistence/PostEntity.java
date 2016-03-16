package com.facebook_autoposter.robot.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Post")
public class PostEntity {

	@Id
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
	
	@Column(name="IdPostStatus")
	private Integer idPostStatus;
	
	@Column(name="Progress")
	private Integer progress;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdFacebook")
	private FacebookEntity facebook;
	
	@Column(name="NextPublish")
	private Date nextPublish;

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

	public Integer getIdPostStatus() {
		return idPostStatus;
	}

	public void setIdPostStatus(Integer idPostStatus) {
		this.idPostStatus = idPostStatus;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public FacebookEntity getFacebook() {
		return facebook;
	}

	public void setFacebook(FacebookEntity facebook) {
		this.facebook = facebook;
	}

	public Date getNextPublish() {
		return nextPublish;
	}

	public void setNextPublish(Date nextPublish) {
		this.nextPublish = nextPublish;
	}
	
	
}
