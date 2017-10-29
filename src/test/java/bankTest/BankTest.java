package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bankAccount.*;

public class BankTest {
		
	@Test
	public void bankNameTest() {
		System.out.println("bankNameTest");
		Bank b1 = new BankImpl("SOS-BanK");
		
		assertEquals("SOS-BanK", b1.getBank_name());
		assertTrue(b1.getBank_id()>=1);
		assertEquals(0, b1.getAccountsList().size());
	}
	
	@Test
	public void bankCreatAccountTest() throws Exception {
		System.out.println("bankCreatAccountTest");
		Bank b2 = new BankImpl("SOS-BanK2");
		Client c1 = new ClientImpl("Cris", "Ronaldo");
		
		Account a1 = b2.createAccount(c1);
		assertEquals(a1, b2.getAccount(a1.getId()));
		assertTrue(b2.getBank_id()>=2);
		assertTrue(b2.getAccountsList().size()>=1);
	}
	
	@Test
	public void bankAccountTest() throws Exception {
		System.out.println("bankAccountTest");
		Bank b3 = new BankImpl("SOS-BanK3");
		Client c1 = new ClientImpl("Paul", "Messi");
		
		Account a1 = b3.createAccount(c1);
		Account a2 = b3.createAccount(c1);
				
		assertTrue(b3.getBank_id()>=1);
		assertEquals(2, b3.getAccountsList().size());
		assertEquals(a1, b3.getAccountsList(c1).get(0));
		assertEquals("Messi", a1.getClient().getLastname(), a2.getClient().getLastname());
		assertEquals(2, b3.getAccountsList(c1).size());
	}

}
