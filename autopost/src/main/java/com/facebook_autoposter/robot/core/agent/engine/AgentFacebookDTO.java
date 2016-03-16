package com.facebook_autoposter.robot.core.agent.engine;

import java.util.Date;

public class AgentFacebookDTO {

	private Integer idAgentFacebook;
	
	private Integer idFacebook;
	
	private Boolean loggedIn;
	
	private Date lastConnection;
	
	private Boolean current;

	public Integer getIdAgentFacebook() {
		return idAgentFacebook;
	}

	public void setIdAgentFacebook(Integer idAgentFacebook) {
		this.idAgentFacebook = idAgentFacebook;
	}

	public Integer getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(Integer idFacebook) {
		this.idFacebook = idFacebook;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}
	
	
}
