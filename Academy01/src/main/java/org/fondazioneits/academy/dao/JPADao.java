package org.fondazioneits.academy.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fondazioneits.academy.entity.AcademyEntity;

public abstract class JPADao<AE extends AcademyEntity> implements AcademyDao<AE> {

	public static final String PERSISTENCE_UNIT_NAME = "Academy01";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager em;

	private final Class<AE> type;

	public JPADao(Class<AE> type) {
		super();
		this.type = type;
	}

	public Class<AE> getType() {
		return type;
	}

	@Override
	public AE find(Long id) {
		return this.em.find(this.getType(), id);
	}

	@Override
	public void save(AE entity) {
		this.em.persist(entity);
	}

	@Override
	public void update(AE entity) {
		this.em.merge(entity);
	}

	@Override
	public void delete(AE entity) {
		this.em.remove(entity);
	}

}
