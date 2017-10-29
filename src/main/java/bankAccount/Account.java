package bankAccount;

import java.util.ArrayList;

public interface Account{

	public int getId();
    public double getBalance();
    public boolean getThreshold();
    public String getMessage();
    public void setMessage(String msg);
    public Bank getBank();
    public Client getClient();
    public boolean deposit(double amount) throws Exception;
    public boolean withdraw(double amount) throws Exception;
    public boolean transfert(Account receiver, double amount) throws Exception;
    public ArrayList<Operation> checkOperations() ; 

}
