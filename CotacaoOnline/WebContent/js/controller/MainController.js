angular.module('MainController', []).controller('MainController', function ($scope, service) {
	$scope.listaProdutos = [];
	$scope.produto = {};
	
    $scope.listar = () => {
    	let fn = null;
        service.listarProduto(function(resp){
        	$scope.listaProdutos = resp.data;
        });
    }
    
    $scope.salvar = function(){
    	if ($scope.produto.id > 0) {
    		service.alterarProduto($scope.produto,function(resp){
				limpar();
		    	$scope.listar();
		    	fecharCadastro();
    		});
    	} else {
    		service.inserirProduto($scope.produto,function(resp){
				limpar();
		    	$scope.listar();
		    	fecharCadastro();
    		});
    	}
    }
    
    $scope.removerProduto = function(id) {
    	service.removerProduto(id, function(resp) {
    		$scope.listar();
    	});
    }
    
    $scope.onClickEditar = function(id) {
    	service.listarProdutoId(id, function(resp) {
    		$scope.produto = resp.data
    	});
    	abrirCadastro();
    }
    
    $scope.onClickFechar = function(id) {
    	fecharCadastro();
    }
    
    function fecharCadastro () {
    	let modal = document.getElementsByClassName("modal")[0];

    	modal.style.display = "none";
    }
    
    function abrirCadastro () {
    	let modal = document.getElementsByClassName("modal")[0];

    	modal.style.display = "block";
    }  
    
    function limpar() {
    	$scope.produto = {};
    }
    
    $scope.listar();
});