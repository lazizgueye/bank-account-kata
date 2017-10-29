/*
 * Home Controller
 */
routeAppControllers.controller('homeCtrl', ['$scope', '$location','$routeParams','$http','$rootScope','TransactionService',
	function($scope, $location, $routeParams, $http,$rootScope,TransactionService){
        $scope.message = "Welcome";
        
        $scope.indexAccount_selected = ($routeParams.idAccount>0?$routeParams.idAccount:0);
        $scope.idAccount_selected = 0;
		
		$scope.client = {"id":"", "firstname":"", "lastname":"","bt_subscribe":false};
		$scope.account = {"id":"", "balance":"", "message":"", "thresholdState":""};
		$scope.operation = [];
		$scope.clientsAccounts = [];
		
		// load all account of the client and choose by default the first account for transaction and operation
		if($routeParams.idClient && $routeParams.idClient>0){
			$http.get('http://localhost:8080/bank-account-kata/rest/bank/getAccount/'+$routeParams.idClient).success(function(response){
					
			 	$scope.idClient = response.client.id
				$scope.client.id = $scope.idClient;
				$scope.client.firstname = response.client.firstname;
				$scope.client.lastname = response.client.lastname;
				$scope.client.bt_subscribe = true;				
					
				$scope.accounts = response.accounts;				

				// redirect to home if route don't exist
				if($routeParams.idAccount && $routeParams.idAccount>0){
					var found = false;						
					for(var i=0; i<Object.keys($scope.accounts).length; i++){
						if($scope.accounts[i].id==$routeParams.idAccount && $scope.accounts[i].clientId==$routeParams.idClient){
							found = true;
							$scope.idAccount = $routeParams.idAccount;
							
							TransactionService.option().getAccounts().success(function(response) {
								$scope.clientsAccounts = response.accounts;				 
				            });
							break;
						}							
					}
					if(!found){
						window.location.href= "#home";
						window.location.reload();
					}
				}
				
				if(!$routeParams.idAccount || typeof $routeParams.idAccount=="undefined"){
					var found = false;						
					for(var i=0; i<Object.keys($scope.accounts).length; i++){
						if($scope.accounts[i].clientId==$routeParams.idClient){
							found = true;
							$scope.idAccount = $scope.accounts[0].id;
							TransactionService.option().getAccounts().success(function(response) { 
								$scope.clientsAccounts = response.accounts;				 
				            });
							break;
						}							
					}
					if(!found){
						window.location.href= "#home";
						window.location.reload();
					}
				}				
				
			});
		}
		
		$scope.subscription = function(fname, lname){
			TransactionService.option().subscription(fname, lname).success(function(response) { 				
				var idClient = response.client.id;
				var idAccount =  response.accounts[Object.keys(response.accounts).length-1].id;
				for(var i=0; i<Object.keys(response.accounts).length; i++){
					if(response.accounts[i].clientId==idClient){
						idAccount = response.accounts[i].id;
						break;
					}
				}
				window.location.href= "#home/"+idClient+"/"+idAccount;
				window.location.reload();
            });			
	    }
		
		$scope.addAccount = function(idClient){
			id = parseInt(idClient);
			 TransactionService.option().addAccount(idClient).success(function(response) { 
				 var idClient = response.client.id;
				 var idAccount =  response.accounts[Object.keys(response.accounts).length-1].id;
					for(var i=Object.keys(response.accounts).length-1; i>0; i--){
						if(response.accounts[i].clientId==idClient){
							idAccount = response.accounts[i].id;
							break;
						}
					}
				 window.location.href= "#home/"+idClient+"/"+idAccount;
             });
	    }
		
		$scope.deposit = function(idAccount, amount){
			id = parseInt(idAccount);
			am = parseFloat(amount);
			if(id>0){
				if(am>0){
					TransactionService.option().deposit(idAccount, amount).success(function(response) { 
						var idClient = response.client.id;
						window.location.href= "#home/"+idClient+"/"+idAccount;
		             });
				}
				else
					alert("error : deposit ### check amount values");
			}
			else
				alert("error : deposit ### please select a account");
	    }
		
		$scope.withdrawal = function(idAccount, amount){
			id = parseInt(idAccount);
			am = parseFloat(amount);
			if(id>0){
				if(am>0){
					TransactionService.option().withdrawal(idAccount, amount).success(function(response) { 
						var idClient = response.client.id;
						window.location.href= "#home/"+idClient+"/"+idAccount;
		             });
				}
				else
					alert("error : withdrawal ### check amount values");
			}
			else
				alert("error : withdrawal ### please select a account");
	    }
		
		$scope.transfert = function(idAccountA, idAccountB, amount){
			idA = parseInt(idAccountA);
			idB = parseInt(idAccountB);
			am = parseFloat(amount);
			if(idA>0){
				if(idB>0){
					if(am>0){
						TransactionService.option().transfert(idA, idB, am).success(function(response) {
							var idClient = response.client.id;
							window.location.href= "#home/"+idClient+"/"+idA;
			             });
					}
					else
						alert("error : transfert ### check amount values");
				}
				else
					alert("error : transfert ### please select a account receiver");
			}
			else
				alert("error : transfert ### please select a account sender");
	    }
		
		$scope.selectAccount = function(idClient, idAccount, indexAccount){
			$location.path('/home/'+idClient+'/'+idAccount).replace();
			$scope.indexAccount_selected = indexAccount;
			$scope.idAccount_selected = idAccount;
	    }
    }
]);


/*
 * Nav Controller
 */
routeAppControllers.controller('navCtrl', ['$scope', '$location','$routeParams','$http','$rootScope','LoginService',
	function($scope, $location, $routeParams, $http,$rootScope,LoginService){
		$scope.login = false;
		$scope.client = null;
		$scope.idClient = $location.path().split("/")[2];
		
		
		if(!$scope.login){			
			LoginService.option().getClients().success(function(response) { 
				$scope.clients = response.clients;
				if($scope.clients && Object.keys($scope.clients).length>0){
					if($scope.idClient && $scope.idClient>0){
						$scope.login = true;
						for(var i=0; i<Object.keys($scope.clients).length; i++){
							if($scope.clients[i].id==$scope.idClient){
								$scope.client = $scope.clients[i];								
								break;
							}
						}
					}
				}				 
            });
		}
		
		$scope.connect = function(idClient){
			id = parseInt(idClient);
			//LoginService.option().connect(id).success(function(response) {
			$http.get('http://localhost:8080/bank-account-kata/rest/bank/login/'+id).success(function(response){
				window.location.href = "#home/"+idClient;
				window.location.reload();
            });
					
	    }
		
		$scope.disconnect = function(){
			LoginService.option().disconnect().success(function(response) { 
				 window.location.href = '#html';
			     window.location.reload();
				 $scope.login = "off";
             });				
	    }
	}
]);

