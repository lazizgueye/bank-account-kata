package bankAccount;

import java.util.ArrayList;

import bankAccount.Operation.Optype;

public class AccountImpl implements Account{
    
	static final double THRESHOLD_MAX = 100;
	
    private ArrayList<Operation> histories;
    private int compteid;
    private int balance;
    public String message;
    private Client c;
    private Bank b;
    
    static int inc = 0;
    private synchronized int increment(){
    	return ++inc;
    }

    public AccountImpl(Client c, Bank b){
    	this.c = c;
		this.b = b;
		this.compteid = increment();
		this.balance = 0;
		message = c.getFirstname()+" "+c.getLastname()+" account Created with balance "+balance;
		histories = new ArrayList<Operation>() ;
    }

    @Override
    public int getId(){
    	return compteid;
    }
    
    @Override
    public double getBalance(){
    	return balance;
    }
    
    @Override
    public boolean getThreshold(){
    	if(balance<0)
    		return true;
    	return false;
    }
    
    @Override
    public String getMessage(){
    	return message;
    }
    @Override
    public void setMessage(String msg){
    	this.message = msg;
    }

    @Override
    public Bank getBank(){
    	return b;
    }
    
    @Override
    public Client getClient(){
    	return c;
    }
    
    @Override
    public boolean deposit(double amount) throws Exception{			
		if(amount>0) {
			histories.add( new Operation(Optype.DEPOSIT, balance, amount));
			balance += amount;
			message = "Deposit : "+amount+" success";
			return true;
		}
		else {
			message = "Deposit : "+amount+" error ### check amount values";
			return false;
		}
    }
   
    @Override
    public boolean withdraw(double amount) throws Exception{		
		if(amount>0) {
			if((balance + THRESHOLD_MAX)-amount>=0){
				histories.add( new Operation(Optype.WITHDRAWAL, balance, amount) );
				balance -= amount;
				message = "Withdraw : "+amount+" success";
				
				if(balance == -100) {					
					message = "Withdraw : "+amount+" success but ### threshold reached";
				}
				return true;
			}
			else {				
	    		double min = THRESHOLD_MAX + balance;
	    		message = "Withdraw : "+amount+" error: threshold reached, you just can withdraw "+min+"€";
	    		return false;	
			}
		}
		else { 
			message = "Withdraw : "+amount+" error ### check amount values";
			return false;
		}
    }
    
    @Override
	public boolean transfert(Account receiver, double amount) throws Exception {
		boolean send = this.withdraw(amount);
		if(send) {
			boolean state = receiver.deposit(amount);
			if(state) {
				String client = receiver.getClient().toString();
				receiver.setMessage("TRANSFERT Received ("+amount+") from "+client);				
				histories.add(new Operation(Optype.TRANSFERT, balance, amount, client));
				message = "Transfert : "+amount+" success: from account "+ this.compteid+" to account "+client ;
				return true;
			}
			return false;
		}
		return false;
	}
    
    @Override
    public ArrayList<Operation> checkOperations(){
    	return histories;
    }
	

}
