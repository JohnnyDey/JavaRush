var app = angular.module('usersList', []);

app.controller('usersCtrl', function ($scope, $http) {
    $scope.base_url = "http://localhost:8080";
    $scope.search_name ="!";
    $scope.search_age = "0";
    $scope.from = 0;
    $scope.step = 10;
    $scope.page = 1;
    $scope.userToEdite;
    $scope.userToAdd;

    getUsers();

    $scope.add = function (user) {
        $http.post($scope.base_url + "/add", user).then(function () {
            getUsers()
        })
    };

    $scope.edit = function (user) {
        $scope.userToEdite = user;
    };

    $scope.update = function (id, user) {
        $http.put($scope.base_url + "/update/" + id, user).then(function () {
            getUsers()
        })
    };

    $scope.delete = function (id) {
        $http.delete($scope.base_url + "/delete/" + id).then(function () {
            getUsers()
        })
    };

    $scope.prevPage = function () {
        if ($scope.page > 1) {
            $scope.page--;
            $scope.from -= $scope.step;
            getUsers()
        }
    };

    $scope.nextPage = function () {
        $http.get($scope.base_url + "/size?name=" + $scope.search_name + "&age=" + $scope.search_age + "&adm=" + $scope.isAdmin)
            .success(function (response) {
                $scope.size = response;
                console.log($scope.size);
                console.log($scope.from);
                console.log($scope.step);
                if ($scope.size > $scope.from + $scope.step) {
                    $scope.page++;
                    $scope.from += $scope.step;
                    getUsers();
                }
            });
    };

    $scope.search = function (name, age, admin) {
        if(name == "" || name == undefined) name = "!";
        if(age == "" || age == undefined) age = 0;
        $scope.search_name = name;
        $scope.search_age = age;
        $scope.page = 1;
        $scope.from = 0;
        getUsers();
    };

    $scope.clearsearch = function () {
        $scope.page = 1;
        $scope.from = 0;
        $scope.search_name ="!";
        $scope.search_age = "0";
        getUsers();
    };


    function getUsers() {
        var string  = "/users?from=" + $scope.from + "&step=" + $scope.step + "&name=" + $scope.search_name + "&age=" + $scope.search_age;
        console.log(string);
        $http.get($scope.base_url + string)
            .success(function (response) {
                $scope.users = response;
            });
        $http.get($scope.base_url + "/size?name=" + $scope.search_name + "&age=" + $scope.search_age)
            .success(function (response) {
                $scope.ofPages = Math.ceil(response/10);
            });
    }
});