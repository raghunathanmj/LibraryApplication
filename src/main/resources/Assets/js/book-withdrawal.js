var app = angular.module('bookWithdrawalApp', ['ngResource', 'directives', 'bookSearch']);

app.controller('BookWithdrawalController', ['$rootScope', '$scope', 'selectedBookService', function($rootScope, $scope, selectedBookService) {
    $scope.chosenBooks = selectedBookService(1);
    $scope.validBook = function() {
        if (typeof $scope.chosenBooks === 'undefined') {
            alert('ERROR: Please select a book from the table for withdrawal');
            return false;
        }
        return true;
    }

    $scope.withdrawal = function() {//currently unimplemented
        alert('withdrawing:\n' + JSON.stringify($scope.chosenBooks));
    }
}]);