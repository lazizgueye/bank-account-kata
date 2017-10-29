package bankAccount;

public class ClientImpl implements Client{
    private int id;
    private String lastName;
    private String firstName;
   // private ArrayList<IAccount> comptes;

    
    //int countBank = 0 ;
    static int inc = 0;
    private synchronized int increment(){
    	return ++inc;
    }

    public ClientImpl(String firstName, String lastName ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = increment() ;
		//this.comptes = new ArrayList<IAccount>() ;
    }

    public void setFirstname (String firstname){
    	this.firstName = firstname;
    }

    public void setLastname(String lastname){
    	this.lastName = lastname;
    }

    @Override
    public String getFirstname(){
    	return firstName;
    }

    @Override
    public String getLastname(){
    	return lastName;
    }
    
    @Override
    public int getId(){
    	return id;
    }

  /*  public void addAccount3(IAccount c){
    	comptes.add(c) ;
    }*/

}
