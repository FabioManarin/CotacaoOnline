angular.module('CotacaoController', []).controller('CotacaoController', function ($scope, service) {
	$scope.listaProdutosDisponiveis = [];
	$scope.listaCotacoes = [];
	$scope.cotacao = {};
	$scope.produtoDisponivel = {};
	
	$scope.listarProdutoDisponivelCotacao = () => {
        service.listarProdutosDisponiveis(function(resp){
        	if(resp.data.sucesso == undefined){
        		$scope.listaProdutosDisponiveis = resp.data;
        	}else{
        		alert(resp.data.message);
        	}
        });
    }
	
	$scope.salvarCotacao = function(){
    	service.inserirCotacao($scope.cotacao, function(resp){
    		alert(resp.data.message);
    		limparCotacao();
    		fecharCadastroCotacao();
    	});
    }
	
	$scope.onClickNovaCotacao = function(produtoDisponivel) {
		limparCotacao();
		abrirCadastroCotacoes();
		$scope.produtoDisponivel = produtoDisponivel;
		$scope.cotacao.produto = $scope.produtoDisponivel;
	}
	
	function limparCotacao() {
    	$scope.cotacao = {};
    }
	
	function abrirCadastroCotacoes () {
    	let modal = document.getElementsByClassName("modal-cad-cotacao")[0];

    	modal.style.display = "block";
    }
	
	function fecharCadastroCotacao () {
    	let modal = document.getElementsByClassName("modal-cad-cotacao")[0];

    	modal.style.display = "none";
     }
	
	$scope.onClickFecharCotacaoCad = function() {
		fecharCadastroCotacao();
    }
	
	$scope.listarProdutoDisponivelCotacao();
	
});