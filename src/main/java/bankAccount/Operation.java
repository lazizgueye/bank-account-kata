package bankAccount;

import java.util.Date;

public class Operation{
	
	public enum  Optype{WITHDRAWAL, DEPOSIT};
    private Date dateOp;
    private double amount;
    private double balance;
    private Optype type;


    public Operation(Optype type, double balance, double amount){
    	dateOp = new Date ();
		this.type = type;
		this.amount = amount;
		this.balance = balance;
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

    public Optype getType (){
    	return type;
    }
    
    public String toString() {
    	return this.getDate()+" #### Balance : "+this.getBalance() +" **** "+this.getType()+" : "+this.getAmount();
    }
    
}
