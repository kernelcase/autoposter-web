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
@Table(name="AgentTask")
public class AgentTaskEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAgentTask")
	private Integer idAgentTask;
	
	@Column(name="TaskName")
	private String taskName;
	
	@Column(name="TaskStatus")
	private String taskStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdFacebook")
	private FacebookEntity facebook;
	
	@Column(name="OrderNumber")
	private Integer orderNumber;
	
	
}
