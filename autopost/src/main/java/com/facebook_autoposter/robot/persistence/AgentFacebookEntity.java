package com.facebook_autoposter.robot.persistence;

import java.util.Date;

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
@Table(name="AgentFacebook")
public class AgentFacebookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAgentFacebook")
	private Integer idAgentFacebook;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdFacebook")
	private FacebookEntity facebook;
	
	@Column(name="LastConnection")
	private Date lastConnection;
	
	@Column(name="Current")
	private Boolean current;
	
	@Column(name="LoggedIn")
	private Boolean loggedIn;

	public Integer getIdAgentFacebook() {
		return idAgentFacebook;
	}

	public void setIdAgentFacebook(Integer idAgentFacebook) {
		this.idAgentFacebook = idAgentFacebook;
	}

	public FacebookEntity getFacebook() {
		return facebook;
	}

	public void setFacebook(FacebookEntity facebook) {
		this.facebook = facebook;
	}
	
	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
}
