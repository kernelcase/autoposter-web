package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;

public class PostQuery {
	
	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;

	public List<PostEntity> getPostOnHold() {
		
		return entityManager.createQuery("FROM PostEntity p WHERE p.nextPublish >= :nextPublish", PostEntity.class)
			.setParameter("nextPublish", DateTime.now().toDate())
			.getResultList();
		
	}
	
	public void save(PostEntity postEntity) {
		entityManager.persist(postEntity);
	}
}	
