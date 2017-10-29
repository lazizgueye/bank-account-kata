package ws;

import java.util.ArrayList;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

import bankAccount.*;

@Path("bank")
public class BankRestful {	
	static Bank bank = new BankImpl("SOS-Bank");
	static Client client = null;
	static ArrayList<Account> accounts = new ArrayList<Account>();
	static ArrayList<Client> clients = new ArrayList<Client>();

	@GET
	@Path("subscription/{firstname}/{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject newClient(@PathParam("firstname") String fname, @PathParam("lastname") String lname){			
		
		try {
			client = new ClientImpl(fname, lname);
			clients.add(client);
			accounts.add(bank.createAccount(client));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return getAccount(client.getId());		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("getClients")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getClients(){
		JSONObject  result = new JSONObject();
		result.put("clients", clients);
		return result;		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("getAccount/{idClient}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getAccount(@PathParam("idClient") int id){			
		if(client != null) {
			if(accounts.isEmpty())
				return null;
			else {		
				JSONObject  result = new JSONObject();
				JSONObject  accountTab = new JSONObject();
				int indexTab = 0;
				for(int i=0; i<accounts.size(); i++) {			
					JSONObject  tab = new JSONObject();				
					if(accounts.get(i).getClient().getId() == id) {
						tab.put("id", accounts.get(i).getId());
						tab.put("clientId", accounts.get(i).getClient().getId());
						tab.put("bank", accounts.get(i).getBank().getBank_name());			
						tab.put("balance", accounts.get(i).getBalance());
						tab.put("message", accounts.get(i).getMessage());
						tab.put("thresholdState", accounts.get(i).getThreshold());			
						tab.put("operation", accounts.get(i).checkOperations());
						accountTab.put(indexTab,tab);
						indexTab++;
					}			
				}
				result.put("client", client);
				result.put("accounts", accountTab);
				return result;
			}
		}
		return null;
	}
	
	@GET
	@Path("addAccount/{idClient}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject addAccount(@PathParam("idClient") int idClient){			
		try {
			accounts.add(bank.createAccount(client));
			return getAccount(idClient);
		} catch (Exception e) {
			e.printStackTrace();
			return getAccount(0);
		}				
	}
	
	@GET
	@Path("deposit/{idAccount}/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject deposit(@PathParam("idAccount") int idAccount, @PathParam("amount") double amount){			
		int indexAccount = 0;		
		try {			
			for(int i=0; i<accounts.size(); i++) {			
				if(accounts.get(i).getId() == idAccount) {
					accounts.get(i).deposit(amount);
					indexAccount = i;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}				
		return getAccount(accounts.get(indexAccount).getClient().getId());
	}
	
	@GET
	@Path("withdrawal/{idAccount}/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject withdrawal(@PathParam("idAccount") int idAccount, @PathParam("amount") double amount){			
		int indexAccount = 0;		
		try {			
			for(int i=0; i<accounts.size(); i++) {			
				if(accounts.get(i).getId() == idAccount) {
					accounts.get(i).withdraw(amount);
					indexAccount = i;
				}
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return getAccount(accounts.get(indexAccount).getClient().getId());		
	}
		
	@GET
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(){		
		client = null;
	}
	
	@GET
	@Path("login/{idClient}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject login(@PathParam("idClient") int idClient){		
		for(int i=0; i<clients.size(); i++) {
			if(clients.get(i).getId() == idClient) {
				client = clients.get(i);
				return this.getAccount(idClient);
			}
		}
		return null;
	}
}
