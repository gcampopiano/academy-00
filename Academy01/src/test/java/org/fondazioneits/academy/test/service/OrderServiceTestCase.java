package org.fondazioneits.academy.test.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fondazioneits.academy.test.Academy01TestCase;
import org.junit.Test;

public class OrderServiceTestCase extends Academy01TestCase {

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@Test
	public void insertNewOrderSuccessful() {
		
	}

}
