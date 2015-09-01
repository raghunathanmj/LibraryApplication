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

    $scope.delete = function(d) {
        alert(JSON.stringify(d));
    };

    $scope.validSearch = function(a, b){
        if ((typeof a === 'undefined') && (typeof b === 'undefined')) {
            alert('Enter valid input for one or more search criteria');
            return false;
        }
        return true;
    };

    $scope.authors=[{id:1, name:"alice", email:"alice@flipkart.com"}, {id:2, name:"bob", email:"bob@flipkart.com"}]
}]);




