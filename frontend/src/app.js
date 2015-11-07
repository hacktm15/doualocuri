angular
    .module('htmlapp', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
        .when('/',{
            templateUrl: 'src/main/main.html',
            controller: 'MainController',
            controllerAs: 'vm'})
        ;
        // $locationProvider.html5Mode(true);
    });