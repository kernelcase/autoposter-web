package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JogGroupQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;

	public List<JobGroupEntity> getJobGroups(Integer idJob) {
		return entityManager.createQuery("FROM JobGroupEntity e WHERE e.job.idJob = :idJob", JobGroupEntity.class)
		.setParameter("idJob", idJob)
		.getResultList();
	}
	
	public void save(JobGroupEntity jobGroupEntity) {
		entityManager.persist(jobGroupEntity);
	}
}
