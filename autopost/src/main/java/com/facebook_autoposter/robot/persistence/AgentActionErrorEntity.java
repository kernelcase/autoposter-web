package com.facebook_autoposter.robot.persistence;

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
@Table(name="AgentActionError")
public class AgentActionErrorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAgentActionError")
	private Integer idAgentActionError;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdAgentActionEntity")
	private AgentActionEntity agentAction;
	
	@Column(name="Param")
	private String errorCode;
	
	@Column(name="Value")
	private String errorMessage;
	
	@Column(name="Stacktrace")
	private String stacktrace;

	

	public Integer getIdAgentActionError() {
		return idAgentActionError;
	}

	public void setIdAgentActionError(Integer idAgentActionError) {
		this.idAgentActionError = idAgentActionError;
	}

	

	public AgentActionEntity getAgentAction() {
		return agentAction;
	}

	public void setAgentAction(AgentActionEntity agentAction) {
		this.agentAction = agentAction;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	
}
