package com.facebook_autoposter.robot.core.agent.action.error;

public class ActionErrorDTO {

	private Integer idActionError;
	
	private Integer idAction;
	
	private String errorCode;
	
	private String errorMessage;
	
	private String stacktrace;

	public Integer getIdActionError() {
		return idActionError;
	}

	public void setIdActionError(Integer idActionError) {
		this.idActionError = idActionError;
	}

	public Integer getIdAction() {
		return idAction;
	}

	public void setIdAction(Integer idAction) {
		this.idAction = idAction;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
	
	
}
