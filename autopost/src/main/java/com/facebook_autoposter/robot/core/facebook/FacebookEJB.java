package com.facebook_autoposter.robot.core.facebook;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.facebook_autoposter.robot.persistence.FacebookEntity;
import com.facebook_autoposter.robot.persistence.FacebookQuery;

@Stateless
public class FacebookEJB {	
	
	@Inject
	private CreateFacebookEJB createFacebookEJB;
	
	@Inject
	private FacebookQuery facebookQuery;
	
	public void createFacebook(FacebookDTO facebookDTO) {
		
		Integer idFacebook = createFacebookEJB.createFacebook(facebookDTO);
		
		boolean verify = createFacebookEJB.verify(idFacebook);
		if(!verify) {
			createFacebookEJB.deleteFacebook(idFacebook);
		}
		
	}	
	
	public FacebookDTO getFacebook(Integer idFacebook) {
		
		FacebookEntity facebookEntity = facebookQuery.getFacebookById(idFacebook);
		
		FacebookDTO facebookDTO  = new FacebookDTO();
		facebookDTO.setFacebookPassword(facebookEntity.getFacebookPassword());
		facebookDTO.setFacebookUsername(facebookEntity.getFacebookUsername());
		facebookDTO.setFacebookStatus(facebookEntity.getFacebookStatus());
		
		return facebookDTO;
	}
	
	public void changeStatus(Integer idFacebook, String facebookStatus) {
		FacebookEntity facebookEntity = facebookQuery.getFacebookById(idFacebook);
		facebookEntity.setFacebookStatus(facebookStatus);
		facebookQuery.update(facebookEntity);
	}
	
	public List<FacebookDTO> getAll() {
		
		List<FacebookDTO> facebookDTOList = new ArrayList<FacebookDTO> ();
		
		List<FacebookEntity> facebookEntityList = facebookQuery.getAll();
		for(FacebookEntity facebookEntity: facebookEntityList) {
			FacebookDTO facebookDTO = FacebookFactory.toDTO(facebookEntity);			
			facebookDTOList.add(facebookDTO);
		}
		
		return facebookDTOList;
	}
}
