package org.fondazioneits.academy.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "CUSTOMER" database table.
 * 
 */
@Entity
@Table(name = "\"CUSTOMER\"")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer extends BaseAcademyEntity {

	private static final long serialVersionUID = 8560626053664179599L;

	@Column(name = "\"NAME\"", nullable = false, length = 2147483647)
	private String name;

	@Column(name = "\"SURNAME\"", nullable = false, length = 2147483647)
	private String surname;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public Customer() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}