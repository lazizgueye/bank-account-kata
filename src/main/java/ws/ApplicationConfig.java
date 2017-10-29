package ws;

import java.util.*;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<>();
		addRestResourceClasses(resources);
		return resources;		
	}
	
	public void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(ws.BankRestful.class);
	}
}
