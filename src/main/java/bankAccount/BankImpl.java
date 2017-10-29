package bankAccount;

import java.util.ArrayList;

public class BankImpl implements Bank{
	
    private ArrayList<AccountImpl> listAccount;
    private String bank_name;
    private int bank_id;

    static int inc = 0;
    private synchronized int increment(){
    	return ++inc;
    }
    
    public BankImpl(String name){
		this.bank_name = name;
		this.bank_id =  increment() ;
		listAccount = new ArrayList<AccountImpl>();
    }
    
    @Override
    public String getBank_name() {
		return bank_name;
	}
    
    @Override
    public int getBank_id() {
		return bank_id;
	}

	@Override
    public Account createAccount(Client c) throws Exception{
		AccountImpl account = new AccountImpl(c, this);
		listAccount.add(account);
		return account;
    }

	@Override
    public Account getAccount (int compteid){
		for (int i  = 0 ; i < listAccount.size() ; i ++ ){
		    if (listAccount.get(i).getId() == compteid)
			return listAccount.get(i);
		} 
		return null ;		
    }

	@Override
    public ArrayList<AccountImpl> getAccountsList(){
		return listAccount;		
    }
	
	@Override
    public ArrayList<Account> getAccountsList(Client c){
		ArrayList<Account> lists  = new ArrayList<Account>();
		
		for (int i  = 0 ; i < listAccount.size() ; i ++ ){
		    if (listAccount.get(i).getClient()  == c)
			lists.add (listAccount.get(i)) ;
		}
		return lists ;		
    }
    

}
