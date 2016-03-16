package com.facebook_autoposter.robot.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JobQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public JobEntity getById(Integer idJob) {
		return entityManager.createQuery("FROM JobEntity e WHERE e.idJob = :idJob", JobEntity.class)
		.setParameter("idJob", idJob)
		.getSingleResult();
	}
	
	public void save(JobEntity jobEntity) {
		entityManager.persist(jobEntity);
	}
	
	public void update(JobEntity postEntity) {
		entityManager.merge(postEntity);
	}
}
