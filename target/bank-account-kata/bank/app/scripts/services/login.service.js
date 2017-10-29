'use strict'

/********* 
	Transaction factory
*********/

routeAppControllers.factory('LoginService',['$http' ,function ($http) {
	return{
		option:function(){				
			var service = {};
			
			service.getClients = getClients;
			service.connect = connect;
			service.disconnect = disconnect;
			return service;
		
						
			/**
		      * getCLIENTS list of clients.		
		      * @return list or null;
		      */
			function getClients(){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/getClients'
				};		
				return	$http(req);
			}
			
			/**
		      * LOGIN current client connected.		      
		      * @param int id	#idClient > 0
		      * @return disconnect the current client;
		      */
			function connect(id){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/login'+id
				};		
				return	$http(req);
			}
			
			/**
		      * LOGOUT current client connected.		      
		      * @param int id	#idClient > 0
		      * @return disconnect the current client;
		      */
			function disconnect(){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/logout'
				};		
				return	$http(req);
			}			
		}
	}
}]);


