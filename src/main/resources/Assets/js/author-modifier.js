var app = angular.module('authorModifyApp', ['ngResource', 'directives']);

app.controller('AuthorModifierCtrl', ['$scope', '$http', function($scope, $http) {

    $scope.insert = function(i){
        alert(JSON.stringify(i));
        $http({
            url: 'http://localhost:8080/author/create',
            method: 'POST',
            data: {authorID:i.id, name:i.name},
            headers: {'Content-Type': 'application/json'}
        }).success(function(data, status, headers, config) {
            alert('done');
        }).error(function(data, status, headers, config) {
            alert('fail');
        });
        alert('http complete');
    };

    $scope.modify = function(m) {
      alert(JSON.stringify(m));
    };

    $scope.deleter = function(d) {
        alert(JSON.stringify(d));
    };

    $scope.validSearch = function(a, b){
        if ((typeof a === 'undefined') && (typeof b === 'undefined')) {
            alert('Enter valid input for one or more search criteria');
            return false;
        }
        return true;
    };
    $scope.searchAuthor = function(s) {
        alert(JSON.stringify(s));
        $scope.authors=[{id:1, name:"alice", email:"alice@flipkart.com"}, {id:2, name:"bob", email:"bob@flipkart.com"}];
    };

    $scope.authors=[];

    $scope.selectedAuthor = [];
    $scope.limit = 1;
    $scope.checkBoxSelect = function(a) {
        if (a.check) {
            if ($scope.selectedAuthor.length >= $scope.limit) {
                alert('ERROR: Only one author can be selected');
                a.check = false;
                return;
            }
            for (var i = 0; i < $scope.selectedAuthor.length; i++) {
                if ($scope.selectedAuthor[i].isbn == a.isbn) {
                    return;
                }
                else if ($scope.selectedAuthor[i].isbn > a.isbn) {
                    selectedAuthor.splice(i, 0, a);
                    return;
                }
            }
            $scope.selectedAuthor.push(a);
            return;
        }
        else {
            for (var low = 0, high = $scope.selectedAuthor.length - 1, mid; low <= high;) {
                mid = Math.floor((high + low) / 2);
                if ($scope.selectedAuthor[mid].isbn == a.isbn) {
                    $scope.selectedAuthor.splice(i, 1);
                    return;
                }
                else if ($scope.selectedAuthor[mid].isbn < a.isbn) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
        }
        return;
    };

    $scope.modifyImport = function() {
        $scope.m = {id:$scope.selectedAuthor[0].id, name:$scope.selectedAuthor[0].name, email:$scope.selectedAuthor[0].email};
        return;
    };

    $scope.deleteImport = function() {
        $scope.d = {id:$scope.selectedAuthor[0].id};
        return;
    };

}]);




