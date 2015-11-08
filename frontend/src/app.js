angular
    .module('htmlapp', ['ngRoute','ngCookies'])
    .config(function ($routeProvider) {
        $routeProvider
        .when('/',{
            templateUrl: 'src/main/main.html',
            controller: 'MainController',
            controllerAs: 'vm'})
        .when('/landing',{
            templateUrl: 'src/main/main.html',
            controller: 'MainController',
            controllerAs: 'vm'})
        .when('/login',{
            templateUrl: 'src/landing/landing.html',
            controller: 'LandingController',
            controllerAs: 'vm'})
        .when('/administrare',{
            templateUrl: 'src/administrare/administrare.view.html',
            controller: 'AdministrareController',
            controllerAs: 'vm'})
        ;
        // $locationProvider.html5Mode(true);   
    })
    .run(function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        // if ($rootScope.globals.currentUser) {
        //     $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        // }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            // var restrictedPage = $.inArray($location.path(), ['/', '/register']) === -1;
            console.log($rootScope.globals);
            var loggedIn = $rootScope.globals.username;
            if (!loggedIn) {
                $location.path('/login');
            }
        });
    })
    ;