angular.module('MainController', []).controller('MainController', function ($scope, service) {
	$scope.listaProdutos = [];
	$scope.produto = {};
	
    $scope.listar = () => {
    	let fn = null;
        service.getProduto(function(resp){
        	$scope.listaProdutos = resp.data;
        });
    }
    
    $scope.listar();
});