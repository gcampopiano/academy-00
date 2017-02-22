package org.fondazioneits.academy.persistence.dao;

import org.fondazioneits.academy.persistence.entity.AcademyEntity;

public interface AcademyDao<AE extends AcademyEntity> {

	public void save(AE entity);

	public AE find(Long id);

	public AE update(AE entity);

	public void delete(AE entity);

}
