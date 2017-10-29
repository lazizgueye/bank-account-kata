'use strict'

/********* 
	Transaction factory
*********/

routeAppControllers.factory('TransactionService',['$http' ,function ($http) {
	return{
		option:function(){				
			var service = {};
			
			service.addAccount = addAccount;
			service.deposit = deposit;
			service.subscription = subscription;
			service.withdrawal = withdrawal;	
			return service;
		
						
			 /**
		      * ADD a new account to Client.		      
		      * @param int id	#idClient > 0
		      * @return list account of Client;
		      */
			function addAccount(id){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/addAccount/'+id
				};		
				return	$http(req);
			}
			
			/**
		      * DEPOSIT a amount to Client account.		      
		      * @param int id 		#id Account > 0
		      * @param double am	#amount > 0
		      * @return list account of Client;
		      */
			function deposit(id, am){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/deposit/'+id+'/'+am
				};		
				return	$http(req);
			}
						
			/**
		      * SUBSCRIPTION a new client.		      
		      * @param String fname 	#firstname of new client
		      * @param String lname		#lastname of new client
		      * @return the first account of client;
		      */
			function subscription(fname, lname){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/subscription/'+fname+'/'+lname
				};		
				return	$http(req);
			}
			
			/**
		      * WITHDRAWAL a amount to Client account.		      
		      * @param int id 		#id Account > 0
		      * @param double am	#amount > 0
		      * @return list account of Client;
		      */
			function withdrawal(id, am){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/withdrawal/'+id+'/'+am
				};		
				return	$http(req);
			}			
			
		}
	}
}]);


