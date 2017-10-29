package bankAccount;

import java.util.List;

public interface Bank{
	
	public String getBank_name();
	public int getBank_id();
    public Account createAccount(Client c) throws Exception ;
    public Account getAccount(int compteid);
    public List<AccountImpl> getAccountsList();
    public List<Account> getAccountsList(Client c);

}
