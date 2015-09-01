var app = angular.module('bookModifierApp', ['ngResource', 'directives', 'bookSearch']);

app.controller('BookModifierCtrl', ['$scope', '$rootScope', 'selectedBookService', function($rootScope, $scope, selectedBookService) {
    $scope.chosenBook = selectedBookService(1);

    $scope.mAnames = [{}];
    $scope.modifyAddAuthor = function() {
        if ($scope.mAnames.length >= 5) {
            alert("ERROR:\n Number of authors can't exceed 5");
            return;
        }
        var temp = {};
        $scope.mAnames.push(temp);
        return;
    };

    $scope.iAnames = [{}];
    $scope.insertAddAuthor = function() {
        if ($scope.iAnames.length >= 5) {
            alert("ERROR:\n Number of authors can't exceed 5");
            return;
        }
        var temp = {};
        $scope.iAnames.push(temp);
        return;
    };

    $scope.insertBook = function(details) {//Currently unimplemented
        alert("Book Details: " + JSON.stringify(details) + "Authors: " + JSON.stringify($scope.iAnames));
        return;
    };

    $scope.modifyBook = function(updatedDetails) {//Currently unimplemented
        alert("Book Details: " + JSON.stringify(updatedDetails) + "Authors: " + JSON.stringify($scope.mAnames));
        return;
    };

    $scope.import = function() {
        alert($scope.chosenBook);
    }

}]);