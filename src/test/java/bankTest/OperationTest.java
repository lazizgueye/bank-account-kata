package bankTest;

import static org.junit.Assert.*;

import org.junit.Test;

import bankAccount.*;

public class OperationTest {
	
	@Test
	public void operationDepositWrongValuesTest() throws Exception {
		System.out.println("operationDepositWrongValuesTest");
		Bank b1 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b1);
		assertFalse(a1.deposit(-34));	
		assertFalse(a1.deposit(-999999999));	
		assertFalse(a1.deposit(-34));	
		
		assertFalse(a1.getThreshold());
		assertEquals(0, a1.checkOperations().size());
		assertEquals(0.0, a1.getBalance(), 0);
	}
	
	@Test
	public void operationDepositTest() throws Exception {
		System.out.println("operationDepositTest");
		Bank b1 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b1);
		a1.deposit(0);		// Balance = still 0 and No Operation saved
		a1.deposit(100);	// Balance = 0 -> 100
		a1.withdraw(45);	// Balance = 100 -> 55
		a1.deposit(5);		// Balance = 55 -> 60
		
		assertFalse(a1.getThreshold());
		assertEquals(3, a1.checkOperations().size());
		assertEquals(60.0, a1.getBalance(), 0);
		assertEquals(0.0, a1.checkOperations().get(0).getBalance(), 0);
		assertEquals(55.0, a1.checkOperations().get(2).getBalance(), 0);
		assertEquals(5.0, a1.checkOperations().get(2).getAmount(), 0);
		assertEquals("DEPOSIT", a1.checkOperations().get(2).getType().toString());
	}
	
	@Test
	public void operationWithdrawWrongValuesTest() throws Exception {
		System.out.println("operationWithdrawWrongValuesTest");
		Bank b1 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b1);
	    
		assertFalse(a1.withdraw(-34));	
		assertFalse(a1.withdraw(-999999999));	
		assertFalse(a1.withdraw(-34));	
		
		assertFalse(a1.getThreshold());
		assertEquals(0, a1.checkOperations().size());
		assertEquals(0.0, a1.getBalance(), 0);
	}
	
	@Test
	public void operationWithdrawTest() throws Exception {
		System.out.println("operationWithdrawTest");
		Bank b1 = new BankImpl ("KataBank") ;
	    Client c1 = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = new AccountImpl(c1, b1);
		a1.deposit(45);		// Balance = 0 -> 45
		a1.deposit(15);		// Balance = 45 -> 60
		a1.withdraw(45);	// Balance = 60 -> 15
		a1.withdraw(5);		// Balance = 15 -> 10
		assertFalse(a1.getThreshold());
		assertEquals(10.0, a1.getBalance(), 0);
		assertEquals(60.0, a1.checkOperations().get(2).getBalance(), 0);
		assertEquals("WITHDRAWAL", a1.checkOperations().get(2).getType().toString());
	}
	
	
}
