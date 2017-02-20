package org.fondazioneits.academy.model;

public class Customer implements AcademyModel {

	private static final long serialVersionUID = 1291762193984123471L;

	private Long id;

	private String name;

	private String surname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
