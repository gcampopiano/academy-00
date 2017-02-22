package org.fondazioneits.academy.model;

import java.util.Date;

public class Order implements AcademyModel {

	private static final long serialVersionUID = -7831350485174921851L;

	private Long id;

	private String code;

	private Date submissionDate;

	private Customer customer;

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
