package org.fondazioneits.academy.persistence.dao;

import org.fondazioneits.academy.persistence.entity.AcademyEntity;

public abstract class JdbcDao<AE extends AcademyEntity> implements AcademyDao<AE> {

	protected static final String JNDI_WEB_CTX = "java:comp/env";
	protected static final String JNDI_ACADEMY01DS = "java:jboss/datasources/Academy01DS";
	
}
