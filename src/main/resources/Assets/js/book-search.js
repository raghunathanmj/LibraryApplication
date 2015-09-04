var bookSearch = angular.module('bookSearch', ['ngResource', 'httpService']);
bookSearch.controller('BookSearcherCtrl', ['$scope', '$rootScope', 'service', function($scope, $rootScope, service) {
    $scope.validSearch = function(name, a, b) {
        if ((typeof a === 'undefined') && (typeof b === 'undefined')) {
            alert("Search by " + name + " failed: Both entries of search cant be left empty");
            return false;
        }
        return true;
    };

    $scope.bookSearch = function(book) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by book';
        var isbn = book.isbn, name = book.name;
        if (typeof book.isbn === 'undefined') {
            isbn = -1;
        }
        if (typeof book.name === 'undefined') {
            name = '';
        }
        alert(name);
        var books = service('book/search/' + isbn + '/' + name, 'GET', {});
        $scope.bookTable = [];
        for (var i = 0; i < books.size(); i++) {
            var authIds = [];
            for (var j = 0; i < books[i].authors.length; j++)
                authIds.push(books[i].authors[j].id);
            var authNames = [];
            for (var j = 0; j < books[i].authors.length; j++)
                authNames.push(books[i].authors[j].name);
            $scope.bookTable.push({isbn:books[i].isbn, name:books[i].name, authNames:authNames, authIds:authIds, remaining:books[i].isbn});
        }
    };

    $scope.authorSearch = function(author) {//currently unimplemented
        $scope.tableTitle = 'Table results for search by author';
        //$scope.bookTable = [{isbn: 1, name: 'physics', authors:['iro', 'pop'], remaining:1}, {isbn: 2, name: 'chemistry', authors:['jd', 'lee'], remaining:0}];
        alert('searching for author\n' + JSON.stringify(author));
    };

    $scope.getAllBooks = function() {
        $scope.tableTitle = 'All Books in the Library';
    };

    $scope.tableTitle = 'No search initiated';
    $scope.bookTable = [];
    $rootScope.selectedBooks = [];

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