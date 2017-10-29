package bankAccount;

public class ClientImpl implements Client{
    private int id;
    private String lastName;
    private String firstName;

    static int inc = 0;
    private synchronized int increment(){
    	return ++inc;
    }

    public ClientImpl(String firstName, String lastName ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = increment() ;
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

    public String toString() {
    	return this.getFirstname()+" "+this.getLastname()+" (#"+this.getId()+")";
    }

}
