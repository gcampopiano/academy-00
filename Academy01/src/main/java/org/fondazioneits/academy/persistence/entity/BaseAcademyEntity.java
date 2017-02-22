package org.fondazioneits.academy.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseAcademyEntity implements AcademyEntity {

	private static final long serialVersionUID = -8136673831542284134L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", unique = true, nullable = false)
	protected Long id;

	@Column(name = "\"LAST_MODIFIED_DATE\"")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
