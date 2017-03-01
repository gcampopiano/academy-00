package org.fondazioneits.academy.test.applexception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class RollbackingCheckedException extends Exception {

	private static final long serialVersionUID = -7397079598734269253L;

	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public RollbackingCheckedException(Long customerId) {
		super();
		this.customerId = customerId;
	}

}
