var app = angular.module('authorModifyApp', ['ngResource', 'directives', 'httpService']);

app.controller('AuthorModifierCtrl', ['$scope', 'service', function($scope, service) {

    $scope.insert = function(i){
        alert(JSON.stringify(i));
        service('author/insert', 'POST', {id: i.id, name: i.name, email: i.email}).then(function(response) {
            if (response.data.id == null) {
                alert('ERROR:\nAuthor with given id already exists or cannot be created');
                return;
            }
        });
    };

    $scope.modify = function(m) {
        alert(JSON.stringify(m));
        service('author/modify', 'PUT', {id: m.id, name: m.name, email: m.email}).then(function(response) {
            if (response.data.id == null) {
                alert('ERROR:\nAuthor with given id does not exist to be modified');
                return;
            }
        });
    };

    $scope.deleter = function(d) {
        alert(JSON.stringify(d));
        service('author/delete/' + d.id, 'DELETE', {}).then(function(response) {
            if (response.data.id == null) {
                alert('ERROR:\nAuthor does not exist or cannot be deleted');
                return;
            }
        });
    };


    $scope.searchAuthor = function(criteria, value) {
        service('author/search/' + criteria + '/' + value, 'GET', {}).then(function(response) {
            if (response.data == null || response.data.length == 0) {
                $scope.authorResults = "No results found";
                return;
            }
            $scope.authorResults = "";
            $scope.selectedAuthor = [];
            $scope.authors = response.data;
        });
    };

    $scope.authors=[];

    $scope.selectedAuthor = [];
    $scope.limit = 1;
    $scope.authorResults = "";
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




