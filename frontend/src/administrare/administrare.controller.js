angular
	.module('htmlapp')
	.controller('AdministrareController', function ($scope){
		console.log('AdministrareController');
		$scope.tab = 'mese';
		$scope.pubId = $rootScope.pubId;
		$scope.changeLocation = function(loc){
			$scope.tab = loc;
		};		
	});	