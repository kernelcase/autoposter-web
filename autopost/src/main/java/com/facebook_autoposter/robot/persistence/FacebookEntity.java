package com.facebook_autoposter.robot.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Facebook")
public class FacebookEntity {

	@Id
	@Column(name="IdFacebook")
	private Integer idFacebook;
	
	@Column(name="FacebookUsername")
	private String facebookUsername;
	
	@Column(name="FacebookPassword")
	private String facebookPassword;
	
	@Column(name="FacebookStatus")
	private String facebookStatus;

	public Integer getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(Integer idFacebook) {
		this.idFacebook = idFacebook;
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

	public String getFacebookStatus() {
		return facebookStatus;
	}

	public void setFacebookStatus(String facebookStatus) {
		this.facebookStatus = facebookStatus;
	}
	
	
}
