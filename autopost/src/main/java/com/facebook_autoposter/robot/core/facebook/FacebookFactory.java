package com.facebook_autoposter.robot.core.facebook;

import com.facebook_autoposter.robot.persistence.FacebookEntity;

public class FacebookFactory {

	public static FacebookDTO toDTO(FacebookEntity facebookEntity) {
		FacebookDTO facebookDTO = new FacebookDTO();
		
		facebookDTO.setFacebookPassword(facebookEntity.getFacebookPassword());
		facebookDTO.setFacebookUsername(facebookEntity.getFacebookUsername());
		facebookDTO.setIdFacebook(facebookEntity.getIdFacebook());
		
		return facebookDTO;
	}
}
