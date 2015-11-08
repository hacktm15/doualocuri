angular
    .module('htmlapp')
    .directive('groupTableDirective', function () {
    return {
        restrict: "E",
        scope: {
            pubId: '='
        },
        replace: true,
        bindToController: true,
        controller: function () {
            var vm = this;
            console.log(vm.pubId);
        },
        link : function (scope, element, attrs, vm) {
        },
        controllerAs: 'vm',
        templateUrl: 'src/groupTableDirective/group-table.directive.html'
    };
});