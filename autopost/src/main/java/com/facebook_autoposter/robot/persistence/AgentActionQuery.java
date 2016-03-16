package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.facebook_autoposter.robot.core.agent.action.ActionStatus;

public class AgentActionQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public List<AgentActionEntity> getActionOnHold(Integer idFacebook) {
		return entityManager.createQuery("FROM AgentActionEntity a WHERE a.facebook.idFacebook = :idFacebook AND a.actionStatus = :actionStatus ORDER BY a.orderNumber ", AgentActionEntity.class)
				.setParameter("idFacebook", idFacebook)
				.setParameter("actionStatus", ActionStatus.HOLD)
				.getResultList();
	}
	
	public AgentActionEntity getActionById(Integer idAgentAction) {
		AgentActionEntity actionEntity = null;
		
		try {
			actionEntity =	entityManager.createQuery("FROM AgentActionEntity a WHERE a.idAgentAction = :idAgentAction", AgentActionEntity.class)
				.setParameter("idAgentAction", idAgentAction)			
				.getSingleResult();
		} catch(NoResultException ex) {
			
		}
		
		return actionEntity;
	}
	
	public AgentActionEntity getNextAction(Integer idFacebook) {
		List<AgentActionEntity> actionOnHold = getActionOnHold(idFacebook);
		
		AgentActionEntity actionEntity = null;
		if(actionOnHold.size() > 0) {
			actionEntity = actionOnHold.get(actionOnHold.size()-1);
		}
		
		return actionEntity;
	}
	
	public void save(AgentActionEntity actionEntity) {
		entityManager.persist(actionEntity);
	}
	
	public void update(AgentActionEntity actionEntity) {
		entityManager.merge(actionEntity);
	}
}
