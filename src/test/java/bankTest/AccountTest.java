package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bankAccount.*;

public class AccountTest {

	@Test
	public void accountTest() throws Exception {
		System.out.println("accountTest");
		Bank b1 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b1);
	    		
	    assertEquals(0.0, a1.getBalance(), 0);
		assertTrue(a1.getId()>=1);
	}
	
	@Test
	public void accountDepositTest() throws Exception {
		System.out.println("accountDepositTest");
		Bank b2 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Messi", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b2);
	    		
	    assertEquals(0.0, a1.getBalance(), 0);
		assertTrue(a1.getId()>=1);
		a1.deposit(15);
		assertEquals(15.0, a1.getBalance(), 0);
		a1.deposit(15);
		a1.deposit(15);
		a1.deposit(15);
		assertEquals(60.0, a1.getBalance(), 0);
	}
	
	@Test
	public void accountWithdrawTest() throws Exception{
		System.out.println("accountWithdrawTest");
		Bank b2 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Messi", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b2);
	    		
	    assertEquals(0.0, a1.getBalance(), 0);
		assertTrue(a1.getId()>=1);
		a1.deposit(45);
		assertEquals(45.0, a1.getBalance(), 0);
		a1.withdraw(15);
		assertFalse(a1.getThreshold());
		assertEquals(30.0, a1.getBalance(), 0);
		a1.withdraw(45);
		assertEquals(-15.0, a1.getBalance(), 0);
		assertTrue(a1.getThreshold());
	}
	
	@Test
	public void accountWithdrawThresholdMaxTest() throws Exception{
		System.out.println("accountWithdrawThresholdMaxTest");
		Bank b2 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Messi", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b2);
	    		
		a1.deposit(100);
		a1.withdraw(50);
		a1.withdraw(50);
		a1.withdraw(50);
		assertTrue(a1.getThreshold());
		assertEquals(-50.0, a1.getBalance(), 0);
		
		a1.withdraw(40);
		assertTrue(a1.getThreshold());
		assertEquals(-90.0, a1.getBalance(), 0);
		
		
		assertFalse(a1.withdraw(20));
		System.out.println(a1.getMessage());
		assertTrue(a1.getThreshold());
		assertEquals(-90.0, a1.getBalance(), 0);
	}
}
