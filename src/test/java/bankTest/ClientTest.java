package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bankAccount.Client;
import bankAccount.ClientImpl;

public class ClientTest {

	@Test
	public void clientTest() {
		System.out.println("clientTest");
		Client c1 = new ClientImpl("Leo", "Gueye");
		
		assertEquals("Leo", c1.getFirstname());
		assertEquals("Gueye", c1.getLastname());
		assertTrue(c1.getId()>=1);
	}
	
	@Test
	public void clientIdIncrementTest() {
		System.out.println("clientIdIncrementTest");
		Client c2 = new ClientImpl("Leo", "Gueye");
		Client c3 = new ClientImpl("Cris", "Ronaldo");
		Client c4 = new ClientImpl("Paul", "Messi");
		
		assertTrue(c2.getId()>=1);
		assertTrue(c3.getId()<c4.getId());
		assertFalse(c3.getId()>c4.getId());
		assertTrue(c4.getId()>=3);
	}

}
