package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class AgentFacebookQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public AgentFacebookEntity getCurrent(Integer idFacebook) {
		AgentFacebookEntity agentFacebookEntity = null;
		
		try {
			agentFacebookEntity = entityManager.createQuery("FROM AgentFacebookEntity a WHERE a.current = :current AND a.facebook.idFacebook = :idFacebook", AgentFacebookEntity.class)			
			.setParameter("current", true)
			.setParameter("idFacebook", idFacebook)
			.getSingleResult();
		} catch(NoResultException ex) {
			
		}
		return agentFacebookEntity;
	}
	
	public List<AgentFacebookEntity> getCurrentList() {
		return entityManager.createQuery("FROM AgentFacebookEntity a WHERE a.current = :current", AgentFacebookEntity.class)			
			.setParameter("current", true)
			.getResultList();
	}
	
	public void save(AgentFacebookEntity agentFacebookEntity) {
		entityManager.persist(agentFacebookEntity);
	}
	
	public void update(AgentFacebookEntity agentFacebookEntity) {
		entityManager.merge(agentFacebookEntity);
	}
	
	public void delete(AgentFacebookEntity agentFacebookEntity) {
		entityManager.remove(agentFacebookEntity);
	}
}
