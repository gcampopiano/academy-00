package org.fondazioneits.academy.test;

import org.fondazioneits.academy.entity.Customer;
import org.fondazioneits.academy.test.em.EntityManagerTestCase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class Academy01TestCase {

	@Deployment
	public static Archive<?> createTestArchive() {

		return ShrinkWrap.create(WebArchive.class, "Academy01Tests.war")
				.addPackage(Academy01TestCase.class.getPackage()).addPackage(EntityManagerTestCase.class.getPackage())
				.addPackage(Customer.class.getPackage())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

}
