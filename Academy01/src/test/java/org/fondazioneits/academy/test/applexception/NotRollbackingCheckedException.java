package org.fondazioneits.academy.test.applexception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class NotRollbackingCheckedException extends Exception {

	private static final long serialVersionUID = 8632694724560105985L;

	private Long customerId;

	public NotRollbackingCheckedException(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
