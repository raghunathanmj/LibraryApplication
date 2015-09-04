var app = angular.module('bookModifierApp', ['ngResource', 'directives', 'bookSearch', 'httpService']);

app.controller('BookModifierCtrl', ['$rootScope', '$scope', 'selectedBookService', 'service',
    function($rootScope, $scope, selectedBookService, service) {
        $scope.mAnames = [{}];
        $scope.modifyAddAuthor = function() {
            for (var i = 0; i < $scope.mAnames.length; i++) {
                if (typeof $scope.mAnames[i].id === 'undefined') {
                    alert("Please fill up existing authors before trying to add a new one");
                    return;
                }
            }
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
            for (var i = 0; i < $scope.iAnames.length; i++) {
                if (typeof $scope.iAnames[i].id === 'undefined') {
                    alert("Please fill up existing authors before trying to add a new one");
                    return;
                }
            }
            if ($scope.iAnames.length >= 5) {
                alert("ERROR:\n Number of authors can't exceed 5");
                return;
            }
            var temp = {};
            $scope.iAnames.push(temp);
            return;
        };

        $scope.insertBook = function(i) {
            service('book/create', 'POST', {isbn: i.isbn, name: i.name, quantity: i.qty, authors:$scope.iAnames});
            return;
        };

        $scope.modifyBook = function(m) {
            service('book/modify', 'PUT', {isbn: m.isbn, name: m.name, quantity: m.qty, authors: $scope.mAnames});
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
                $scope.mAnames.push({id: localBooks[0].authors[i].id});
            }
            $scope.m = {isbn:localBooks[0].isbn, name:localBooks[0].name,qty:localBooks[0].quantity};
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
    }
]);