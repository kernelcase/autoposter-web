package com.facebook_autoposter.robot.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ConfigQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public ConfigEntity getByKey(String key) {		
		return entityManager.createQuery("FROM ConfigEntity c WHERE c.key = :key", ConfigEntity.class)
				.setParameter("key", key)
				.getSingleResult();
	}

	public void save(ConfigEntity configEntity) {
		entityManager.persist(configEntity);
	}
}