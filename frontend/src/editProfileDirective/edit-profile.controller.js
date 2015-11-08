angular
    .module('htmlapp')
    .directive('editProfileDirective', function () {
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
        templateUrl: 'src/editProfileDirective/edit-profile.view.html'
    };
});