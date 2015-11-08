angular
    .module('htmlapp')
    .controller('LandingController', function (loginService, $location) {
        console.log("aaa");
        var vm = this;
        vm.login = function (){
        	username = vm.form.username.$viewValue;
        	password = vm.form.password.$viewValue;
        	loginService.Login(username, password, function (response) {
        		if(response.success === true){
        			loginService.SetCredentials(username, password);
        			$location.path('/administrare');
        		}
        	});
        	// console.log(vm.form.username.$viewValue);	
        };
    });