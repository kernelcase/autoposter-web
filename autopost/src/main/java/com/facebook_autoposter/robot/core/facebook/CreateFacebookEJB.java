package com.facebook_autoposter.robot.core.facebook;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.facebook_autoposter.robot.exception.BusinessException;
import com.facebook_autoposter.robot.exception.Validation;
import com.facebook_autoposter.robot.persistence.FacebookEntity;
import com.facebook_autoposter.robot.persistence.FacebookQuery;

@Stateless
public class CreateFacebookEJB {
	
	@Inject
	private FacebookQuery facebookQuery;

	@TransactionAttribute( TransactionAttributeType.REQUIRES_NEW)
	public Integer createFacebook(FacebookDTO facebookDTO) {
		// Validate the input
		Validation.required().setParam("facebookDTO", facebookDTO).validate();
				
		Validation.required()
			.setParam("idFacebook", facebookDTO.getIdFacebook())
			.setParam("facebookUsername", facebookDTO.getFacebookUsername())
			.setParam("facebookPassword", facebookDTO.getFacebookPassword())		
			.validate();
				
				
				
		FacebookEntity duplicated = facebookQuery.getFacebookByUsername(facebookDTO.getFacebookUsername());
		boolean isDuplicated =  duplicated != null;
		if(isDuplicated) {
			throw new BusinessException("the facebook account is duplicated");
		}
				
		FacebookEntity facebookEntity = new FacebookEntity();
		facebookEntity.setIdFacebook(facebookDTO.getIdFacebook());
		facebookEntity.setFacebookUsername(facebookDTO.getFacebookUsername());
		facebookEntity.setFacebookPassword(facebookDTO.getFacebookPassword());
		facebookEntity.setFacebookStatus(FacebookStatus.NEW);
		facebookQuery.save(facebookEntity);

		return facebookEntity.getIdFacebook();
	}
	
	@TransactionAttribute( TransactionAttributeType.REQUIRES_NEW)
	public boolean verify(Integer idFacebook) {
		boolean verify = false;
		
		while(!verify) {
			FacebookEntity facebookEntity = facebookQuery.getFacebookById(idFacebook);
			verify = facebookEntity.getFacebookStatus().equals(FacebookStatus.VERIFIED);
		}
		
		return verify;
	}
	
	@TransactionAttribute( TransactionAttributeType.REQUIRES_NEW)
	public void deleteFacebook(Integer idFacebook) {
		FacebookEntity facebookEntity = facebookQuery.getFacebookById(idFacebook);
		facebookQuery.delete(facebookEntity);		
	}
}
