angular
	.module('htmlapp')
	.controller('AdministrareController', function (){
		console.log('AdministrareController');
	})
	.module('witchMenu', []);
    app.controller('switchMenu', ['$scope', function ($scope) {
        $scope.tab = 'Mese';
    }]);