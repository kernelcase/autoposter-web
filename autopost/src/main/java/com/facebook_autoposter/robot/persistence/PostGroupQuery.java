package com.facebook_autoposter.robot.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PostGroupQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public void save(PostGroupEntity postGroupEntity) {
		entityManager.persist(postGroupEntity);
	}
}
