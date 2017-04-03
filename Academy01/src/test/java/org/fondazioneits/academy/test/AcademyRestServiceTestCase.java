package org.fondazioneits.academy.test;

import java.net.URL;

import javax.ws.rs.ApplicationPath;

import org.fondazioneits.academy.rest.JAXActivator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AcademyRestServiceTestCase extends BaseAcademyTestCase {

	protected static final String RESOURCE_PREFIX = JAXActivator.class.getAnnotation(ApplicationPath.class).value()
			.substring(1);

	@ArquillianResource
	protected URL deploymentUrl;

	@Deployment(testable = false)
	public static Archive<?> deployTestArchive() {

		return createTestArchive();

	}

}
