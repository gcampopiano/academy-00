package org.fondazioneits.academy.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AcademyServiceTestCase extends BaseAcademyTestCase {

	public static final String PERSISTENCE_UNIT_NAME = "Academy01";

	@Deployment
	public static Archive<?> deployTestArchive() {

		return createTestArchive();

	}

}
