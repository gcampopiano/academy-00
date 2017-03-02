package org.fondazioneits.academy.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the "ORDER" database table.
 * 
 */
@Entity
@Table(name = "\"ORDER\"")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order extends BaseAcademyEntity {

	private static final long serialVersionUID = 1276145755638019321L;

	@Column(name = "\"CODE\"", nullable = false, length = 2147483647)
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "\"SUBMISSION_DATE\"", nullable = false)
	private Date submissionDate;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"CUSTOMER_ID\"")
	private Customer customer;

	public Order() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getSubmissionDate() {
		return this.submissionDate;
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