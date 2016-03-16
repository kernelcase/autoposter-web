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
@Table(name="AgentActionParam")
public class AgentActionParamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAgentActionParam")
	private Integer idAgentActionParam;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdAgentAction")
	private AgentActionEntity agentAction;
	
	@Column(name="Param")
	private String param;
	
	@Column(name="Value")
	private String value;

	public Integer getIdAgentActionParam() {
		return idAgentActionParam;
	}

	public void setIdAgentActionParam(Integer idAgentActionParam) {
		this.idAgentActionParam = idAgentActionParam;
	}

	public AgentActionEntity getAgentAction() {
		return agentAction;
	}

	public void setAgentAction(AgentActionEntity agentAction) {
		this.agentAction = agentAction;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
}
