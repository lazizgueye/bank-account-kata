
//configRoute.js
'use strict';

/**
 * Déclaration de l'application routeApp
 */
var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'routeAppControllers',
    'ngCookies',
    'ngSanitize'
]);

/**
 * Configuration du module principal : routeApp
 */
routeApp.config(['$routeProvider',
    function($routeProvider) { 
        
        // Système de routage
        $routeProvider
        .when('/home', {
            templateUrl: 'bank/app/views/home.html',
            controller: 'homeCtrl'
        })
        .when('/home/:idClient', {
            templateUrl: 'bank/app/views/home.html',
            controller: 'homeCtrl'
        })
        .when('/home/:idClient/:idAccount', {
            templateUrl: 'bank/app/views/home.html',
            controller: 'homeCtrl'
        })
		
        

		.otherwise({
            redirectTo: '/home'
        });
	
    }
]);


/**
 * Définition des contrôleurs
 */
var routeAppControllers = angular.module('routeAppControllers', ['ngDialog', 'ngCookies']);
var need_players_name2 = false;

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);


routeAppControllers.run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
	$rootScope.need_players_name = 111111;
}]);
