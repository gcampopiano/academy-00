package org.fondazioneits.academy.model;

public enum ErrorCode {

	MISSING_ORDER_PAYLOAD("Order payload is missing"), MISSING_ORDER_CODE(
			"Order code is missing"), MISSING_ORDER_CUSTOMER_ID("Order's customer id is missing"), CUSTOMER_NOT_FOUND(
					"Customer was not found for given id"), ORDER_NOT_FOUND(
							"Order was not found for given id"), MISSING_CUSTOMER_NAME(
									"Customer name is missing"), MISSING_CUSTOMER_SURNAME(
											"Customer surname is missing");

	private String errorMessage;

	private ErrorCode(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
