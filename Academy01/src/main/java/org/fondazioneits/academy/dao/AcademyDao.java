package org.fondazioneits.academy.dao;

import org.fondazioneits.academy.entity.AcademyEntity;

public interface AcademyDao<AE extends AcademyEntity> {

	public void save(AE entity);

	public AE find(Long id);

	public void update(AE entity);

	public void delete(AE entity);

}
