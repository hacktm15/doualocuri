angular
    .module('htmlapp')
    .directive('groupReservationsDirective', function () {
    return {
        restrict: "E",
        scope: {
        },
        replace: true,
        bindToController: true,
        controller: function () {
            var vm = this;
        },
        link : function (scope, element, attrs, vm) {
        },
        controllerAs: 'vm',
        templateUrl: 'src/groupReservationsDirective/group-reservations.view.html'
    };
});