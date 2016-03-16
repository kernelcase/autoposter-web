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
@Table(name="AgentAction")
public class AgentActionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAgentAction")
	private Integer idAgentAction;
	
	@Column(name="ActionName")
	private String actionName;
	
	@Column(name="ActionStatus")
	private String actionStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdFacebook")
	private FacebookEntity facebook;
	
	@Column(name="OrderNumber")
	private Integer orderNumber;

	

	public Integer getIdAgentAction() {
		return idAgentAction;
	}

	public void setIdAgentAction(Integer idAgentAction) {
		this.idAgentAction = idAgentAction;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public FacebookEntity getFacebook() {
		return facebook;
	}

	public void setFacebook(FacebookEntity facebook) {
		this.facebook = facebook;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	
}
