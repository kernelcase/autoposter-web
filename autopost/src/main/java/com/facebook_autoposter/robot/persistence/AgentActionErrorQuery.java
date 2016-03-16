package com.facebook_autoposter.robot.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AgentActionErrorQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public void save(AgentActionErrorEntity agentActionErrorEntity) {
		entityManager.persist(agentActionErrorEntity);
	}
}
