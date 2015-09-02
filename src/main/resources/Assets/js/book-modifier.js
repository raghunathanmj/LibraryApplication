var app = angular.module('bookModifierApp', ['ngResource', 'directives', 'bookSearch']);

app.controller('BookModifierCtrl', ['$rootScope', '$scope' , 'selectedBookService', function($rootScope, $scope, selectedBookService) {


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

    selectedBookService(1);
    $scope.modifyImport = function() {
        var localBooks = selectedBookService(1);//Why here??
        if (typeof localBooks === 'undefined' || localBooks.length == 0) {
            alert('ERROR: Select Book to import into modification form');
            return;
        }
        $scope.mAnames = [];
        for (var  i = 0; i < localBooks[0].authors.length; i++) {
            $scope.mAnames.push({name: localBooks[0].authors[i]});
        }
        $scope.m = {isbn:localBooks[0].isbn, name:localBooks[0].name,qty:localBooks[0].remaining};
        return;
    };

    $scope.deleteImport = function() {
        var localBooks = selectedBookService(1);
        if (typeof localBooks === 'undefined' || localBooks.length == 0) {
            alert('ERROR: Select book to import into deletion form');
            return;
        }
        $scope.d = {isbn:localBooks[0].isbn};
        return;
    };

    $scope.modifyRemove = function(ind) {
        if ($scope.mAnames.length == 1) {
            alert('ERROR: A minimum of one author is required');
            return;
        }
        $scope.mAnames.splice(ind, 1);
    }

    $scope.insertRemove = function(ind) {
        if ($scope.iAnames.length == 1) {
            alert("ERROR: A minimum of one author is required");
            return;
        }
        $scope.iAnames.splice(ind, 1);
    }
}]);