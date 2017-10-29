package bankAccount;

import java.util.Date;

public class Operation{
	
	public enum  Optype{WITHDRAWAL, DEPOSIT, TRANSFERT};
    private Date dateOp;
    private double amount;
    private double balance;
    private Optype type;
    private String receiver = null;


    public Operation(Optype type, double balance, double amount){
    	dateOp = new Date ();
		this.type = type;
		this.amount = amount;
		this.balance = balance;
    }
    
    public Operation(Optype type, double balance, double amount, String receiver){
    	dateOp = new Date ();
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.receiver = receiver;
    }

    public Date getDate() {
    	return dateOp;
    }

    public double getAmount(){
    	return amount;
    }

    public double getBalance(){
    	return balance;
    }
    
    public String getReceiver(){
    	return receiver;
    }

    public Optype getType (){
    	return type;
    }
    
    public String toString() {
    	if(this.receiver != null)
    		return this.getDate()+" #### Balance : "+this.getBalance() +" **** "+this.getType()+" : "+this.getAmount()+" to "+this.getReceiver();
    	return this.getDate()+" #### Balance : "+this.getBalance() +" **** "+this.getType()+" : "+this.getAmount();
    }
    
}
