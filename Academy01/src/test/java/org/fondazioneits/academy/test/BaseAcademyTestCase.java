package org.fondazioneits.academy.test;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public abstract class BaseAcademyTestCase {

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
