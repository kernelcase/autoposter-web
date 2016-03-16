package com.facebook_autoposter.robot.core.agent.action;

import java.util.List;

public class AgentActionDTO {
	
	private Integer idAction;
	
	private String actionStatus;

	private String actionName;
	
	private String facebookUsername;
	
	private Integer orderNumber;
	
	private List<AgentActionParamDTO> params;
	
	

	public Integer getIdAction() {
		return idAction;
	}

	public void setIdAction(Integer idAction) {
		this.idAction = idAction;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	public List<AgentActionParamDTO> getParams() {
		return params;
	}

	public void setParams(List<AgentActionParamDTO> params) {
		this.params = params;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
