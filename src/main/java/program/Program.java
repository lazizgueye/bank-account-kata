package program;

import bankAccount.Account;
import bankAccount.Bank;
import bankAccount.BankImpl;
import bankAccount.Client;
import bankAccount.ClientImpl;

public class Program{
    
	public static void main(String [] args) throws Exception{
	    Bank b = new BankImpl ("KataBank") ;
	    Client c = new ClientImpl ("Leo", "Gueye");
	    
	    Account a1 = b.createAccount(c);
	    System.out.println("idAccount "+a1.getClient().getFirstname());
	    
	    a1.deposit(15);
	    a1.deposit(15);
	    a1.deposit(15);
	    a1.withdraw(15);
	    
	    for(int i=0; i<a1.checkOperations().size(); i++) {
	    	System.out.println(a1.checkOperations().get(i));	    
	    }
		
	}


}
