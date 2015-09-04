var httpService = angular.module('httpService', []);

httpService.factory('service', ['$http', '$rootScope', function($http, $rootScope) {
    return function(relativeUrl, method, obj) {
        var req = {
            url: 'http://localhost:8080/' + relativeUrl,
            method: method,
            data: obj,
            headers: {'Content-Type': 'application/json'}
        };

        var retVal = $http(req).then(function(data, status, headers, config) {
            console.log('Object sent to server: ' + JSON.stringify(obj));
            console.log('Server response: ' + JSON.stringify(data));
            $rootScope.retVal = data;
            return $rootScope.retVal;
        }, function(data, status, headers, config) {
            console.log('fail');
        });

        return retVal;
    }
}]);