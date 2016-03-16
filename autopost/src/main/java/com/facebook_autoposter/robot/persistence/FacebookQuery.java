package com.facebook_autoposter.robot.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class FacebookQuery {

	@PersistenceContext(unitName="Facebook_AutoposterPublisherPU")
	private EntityManager entityManager;
	
	public FacebookEntity getFacebookById(Integer idFacebook) {
		return entityManager.createQuery("FROM FacebookEntity e WHERE e.idFacebook = :idFacebook", FacebookEntity.class)
		.setParameter("idFacebook", idFacebook)
		.getSingleResult();
	}
	
	public FacebookEntity getFacebookByUsername(String facebookUsername) {
		FacebookEntity facebookEntity = null;
		
		try {
			facebookEntity = entityManager.createQuery("FROM FacebookEntity e WHERE e.facebookUsername = :facebookUsername", FacebookEntity.class)
					.setParameter("facebookUsername", facebookUsername)
					.getSingleResult();
		} catch(NoResultException ex) {
			
		}
		
		return facebookEntity;
	}
	
	public List<FacebookEntity> getAll() {
		return entityManager.createQuery("FROM FacebookEntity e", FacebookEntity.class)		
		.getResultList();
	}
	
	public void save(FacebookEntity facebookEntity) {
		entityManager.persist(facebookEntity);
	}
	
	public void update(FacebookEntity facebookEntity) {
		entityManager.merge(facebookEntity);
	}
	
	public void delete(FacebookEntity facebookEntity) {
		entityManager.remove(facebookEntity);
	}
}
