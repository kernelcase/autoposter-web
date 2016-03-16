package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AgentActionParamQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public List<AgentActionParamEntity> getParams(Integer idAgentAction) {
		
		return entityManager.createQuery("FROM AgentActionParamEntity a WHERE a.agentAction.idAgentAction = :idAgentAction", AgentActionParamEntity.class)
				.setParameter("idAgentAction", idAgentAction)
				.getResultList();		
	}
	
	public void save(AgentActionParamEntity agentActionParamEntity) {
		entityManager.persist(agentActionParamEntity);
	}
}
