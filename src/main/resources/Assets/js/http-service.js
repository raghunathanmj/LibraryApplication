var httpService = angular.module('httpService', []);

httpService.factory('service', ['$http', function($http) {
    return function(relativeUrl, method, obj) {
        $http({
            url: 'http://localhost:8080/' + relativeUrl,
            method: method,
            data: obj,
            headers: {'Content-Type': 'application/json'}
        }).success(function(data, status, headers, config) {
            alert('done');
            console.log('Object sent to server: ' + JSON.stringify(obj));
            console.log('Server response: ' + JSON.stringify(data));
            return data;
        }).error(function(data, status, headers, config) {
            alert('fail');
            alert(JSON.stringify(obj));
        });
    }
}]);