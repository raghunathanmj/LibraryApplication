var bookSearch = angular.module('bookSearch', ['ngResource', 'httpService']);
bookSearch.controller('BookSearcherCtrl', ['$scope', '$rootScope', 'service', function($scope, $rootScope, service) {
    $scope.validSearch = function(name, a, b) {
        if ((typeof a === 'undefined') && (typeof b === 'undefined')) {
            alert("Search by " + name + "Search Entry can");
            return false;
        }
        return true;
    };

    $scope.bookSearch = function(criteria, value) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by book ';
        if (typeof value === 'undefined') {
            alert('ERROR:\n Please enter ' + criteria);
            return;
        }
        service('book/search/' + criteria + '/' + value, 'GET', {}).then(function(response) {
            $scope.collectResponseData(response);
        });

    };

    $scope.authorSearch = function(author) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by author';

        alert('searching for author\n' + JSON.stringify(author));
    };

    $scope.getAllBooks = function() {
        $scope.tableTitle = 'All Books in the Library';
        service('book/get-all/', 'GET', {}).then(function(response) {
            $scope.collectResponseData(response);
        });
    };

    $scope.collectResponseData = function(response) {
        if (typeof response.data === 'undefined' || response.data.length == 0 || response.data[0] == null) {
            $scope.bookResults = 'No results found';
            $scope.bookTable = [];
            return;
        }
        $scope.bookResults = '';
        $scope.bookTable = response.data;
    };

    $scope.tableTitle = 'No search initiated';
    $scope.bookTable = [];
    $rootScope.selectedBooks = [];
    $scope.bookResults = '';

    $scope.checkBoxSelect = function(row){
        if (row.check) {
            if ($scope.selectedBooks.length < $rootScope.limit) {
                for (var i = 0; i < $rootScope.selectedBooks.length; i++) {
                    if ($rootScope.selectedBooks[i].isbn == row.isbn) {
                        return;
                    }

                    else if ($rootScope.selectedBooks[i].isbn > row.isbn) {
                        $rootScope.selectedBooks.splice(i, 0, row);
                        return;
                    }
                }
                $rootScope.selectedBooks.push(row);
            }

            else {
                alert('Cannot choose more than ' + $rootScope.limit +  ' books at a time');
                row.check = false;
            }
        }
        else {
            //Insertion maintains the invariant of having a sorted selectedBooks table.
            //Hence for deletion, do binary search to search for table row to delete
            for (var low = 0, high = $rootScope.selectedBooks.length- 1, mid; low <= high;) {
                mid = Math.floor((low + high) / 2);
                if ($rootScope.selectedBooks[mid].isbn == row.isbn) {
                    $rootScope.selectedBooks.splice(mid, 1);
                    return;
                }
                else if ($rootScope.selectedBooks[mid].isbn < row.isbn) low = mid + 1;
                else high = mid - 1;
            }
        }
    };

}]);

bookSearch.factory('selectedBookService', ['$rootScope', function($rootScope) {
    return function(maxCount) {
        $rootScope.limit = maxCount;
        return $rootScope.selectedBooks;
    };
}]);