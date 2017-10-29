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
			service.getAccounts = getAccounts;
			service.subscription = subscription;
			service.transfert = transfert;
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
		      * getACCOUNTS list of Client account.		
		      * @return list account of Client;
		      */
			function getAccounts(){				 
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/getAccounts'
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
		      * TRANSFERT amount to client account.	
		      * @param int a		#idAccount client (sender)      
		      * @param int b 	#idAccount client (receiver)
		      * @param double m	#amount to transfert
		      * @return true/false;
		      */
			function transfert(a, b, m){
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/bank-account-kata/rest/bank/transfert/'+a+'/'+b+'/'+m
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


