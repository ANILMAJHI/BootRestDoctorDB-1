package com.anil.boot.exception;

import java.util.List;

public class ExceptionResponse {

	private String messageError;
	private List<String> details;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(String messageError, List<String> details) {
		super();
		this.messageError = messageError;
		this.details = details;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [messageError=" + messageError + ", details=" + details + "]";
	}

}
