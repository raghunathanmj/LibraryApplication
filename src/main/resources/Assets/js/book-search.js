var bookSearch = angular.module('bookSearch', ['ngResource']);
bookSearch.controller('BookSearcherCtrl', ['$scope', '$rootScope', '$http', function($scope, $rootScope, $http) {
    $scope.validSearch = function(name, a, b) {
        if ((typeof a === 'undefined') && (typeof b == 'undefined')) {
            alert("Search by " + name + " failed: Both entries of search cant be left empty");
            return false;
        }
        return true;
    };

    $scope.bookSearch = function(book) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by book';
        $scope.bookTable = [{isbn: 1, name: 'physics', authors:['res', 'hal'], remaining:2},
            {isbn: 2, name: 'chemistry', authors:['mor', 'boyd'], remaining:1},
            {isbn: 3, name: 'math', authors:['hall', 'kni'], remaining:1},
            {isbn: 4, name: 'eng', authors:['mart'], remaining:1},
            {isbn: 5, name: 'bio', authors:['rob', 'cook'], remaining:1},
            {isbn: 6, name: 'sher', authors:['arth', 'con'], remaining:1}];
        alert('searching for book\n' + JSON.stringify(book));
    };

    $scope.authorSearch = function(author) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by author';
        $scope.bookTable = [{isbn: 1, name: 'physics', authors:['iro', 'pop'], remaining:1}, {isbn: 2, name: 'chemistry', authors:['jd', 'lee'], remaining:0}];
        alert('searching for author\n' + JSON.stringify(author));
    };


    $scope.tableTitle = 'No search initiated';
    $scope.bookTable = [];
    $rootScope.selectedBooks = [];
    $scope.count = 0;

    $scope.checkBoxSelect = function(row, ind){
        if (row.check) {
            if ($scope.count < $rootScope.limit) {
                for (var i = 0; i < $rootScope.selectedBooks.length; i++) {
                    if ($rootScope.selectedBooks[i].isbn == row.isbn) {
                        return;
                    }

                    else if ($rootScope.selectedBooks[i].isbn > row.isbn) {
                        $rootScope.selectedBooks.splice(i, 0, row);
                        $scope.count += 1;
                        return;
                    }
                }
                $rootScope.selectedBooks.push(row);
                $scope.count += 1;
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
                    $scope.count -= 1;
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