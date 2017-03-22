package org.fondazioneits.academy.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class Academy01TestCase {

	public static final String PERSISTENCE_UNIT_NAME = "Academy01";

	@Deployment
	public static Archive<?> createTestArchive() {

		File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies()
				.resolve().withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "Academy01Tests.war").addPackages(true, "org.fondazioneits.academy")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml").addAsLibraries(dependencies)
				.addAsResource("dozerBeanMapping.xml").addAsWebInfResource("test-ds.xml")
				// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
				.addAsWebInfResource("beans.xml", "beans.xml");

	}

}
