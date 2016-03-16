package com.facebook_autoposter.robot.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorMessageDTO errorMessageDTO;

	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ErrorMessageDTO getErrorMessage() {
		return errorMessageDTO;
	}

	public void setErrorMessage(ErrorMessageDTO errorMessageDTO) {
		this.errorMessageDTO = errorMessageDTO;
	}

	

}
