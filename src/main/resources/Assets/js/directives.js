var directives = angular.module('directives', ['ngResource']);

var ALPHABETS_REGEXP = /^[A-Za-z]+$/i;
directives.directive('alphabets', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$validators.alphabets = function(modelValue, viewValue) {
                if (ALPHABETS_REGEXP.test(viewValue)) {
                    return true;
                }
                return false;
            }
        }
    };
});

var INTEGERS_REGEXP = /^\d+$/;
directives.directive('integers', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$validators.integers = function(modelValue, viewValue) {
                if (typeof viewValue === 'undefined') { //This part would already be taken care of by required directive
                    return true;
                }

                if (INTEGERS_REGEXP.test(viewValue + "")) {
                    return true;
                }
                if (viewValue < 0) {
                    return false;
                }
                return false;
            }
        }
    };
});
