angular.module('MainController', []).controller('MainController', function ($scope, service) {
	$scope.listaProdutos = [];
	$scope.listaCotacoes = [];
	$scope.produto = {};
	$scope.cotacao = {};
	$scope.produtoDisponivel = {};
	$scope.produtosComCotacao = false;
	
    $scope.listarProduto = () => {
        service.listarProduto($scope.produtosComCotacao, function(resp){
        	$scope.listaProdutos = resp.data;
        });
    }
    /*
    $scope.listarProdutoDisponivelCotacao = () => {
        service.listarProdutosDisponiveis(function(resp){
        	if(resp.data.sucesso == undefined){
        		resp.data.dataInicialCotacao = Date.parse(resp.data.dataInicialCotacao);
        		$scope.listaProdutosDisponiveis = resp.data;
        	}else{
        		alert(resp.data.mensagem);
        	}
        });
    }
    */
    $scope.salvar = function(){
    	if ($scope.produto.id){
    		service.alterarProduto($scope.produto, function(resp){
	    		limparProduto();
				$scope.listarProduto();
				fecharCadastro();
				alert(resp.data.message);
    		});
    	} else {
    		service.inserirProduto($scope.produto, function(resp){
    			limparProduto();
				$scope.listarProduto();
				fecharCadastro();
				alert(resp.data.message);
    		}); 
    	}
    }
    
    $scope.onClickEditar = function(id) {
    	service.listarProdutoId(id, function(resp) {
    		$scope.produto.id = resp.data.id;
    		$scope.produto.nome = resp.data.nome;
    		$scope.produto.dataInicialCotacao = new Date(resp.data.dataInicialCotacao)
    		$scope.produto.dataFinalCotacao = new Date(resp.data.dataFinalCotacao)
    	});
    	
    	abrirCadastro();
    }
    
    $scope.onClickNovo = function() {
    	limparProduto();
    	abrirCadastro()
    }
    
    $scope.onClickFechar = function(id) {
    	fecharCadastro();
    }
    
    $scope.onClickFecharCotacoes = function() {
    	fecharCotacoes();
    }
    
    $scope.onClickRemover = function(id) {
    	service.removerProduto(id, function(resp) {
    		$scope.listarProduto();
    		alert(resp.data.message);
    	});
    }
    
    $scope.onClickAbrirCotacoes = function (idProduto) {
    	listarCotacoes(idProduto);
    }
    
    $scope.onChangeFiltro = function () {
    	$scope.listarProduto();
    }
    
    function listarCotacoes(idProduto) {
    	setCarregando();
    	 service.listarCotacoes(idProduto, function(resp){
         	$scope.listaCotacoes = resp.data;
        	abrirCotacoes();
        	
        	removeCarregando();
         });
    }
    
    function setCarregando () {
    	let body = document.body;

    	body.classList.add("mouse-waiting");
    }
    
    function removeCarregando () {
    	let body = document.body;

    	body.classList.remove("mouse-waiting");
    }
    
    function fecharCadastro () {
    	let modal = document.getElementsByClassName("modal-cad")[0];

    	modal.style.display = "none";
     }
    
    function abrirCadastro () {
    	let modal = document.getElementsByClassName("modal-cad")[0];

    	modal.style.display = "block";
    } 
    
    function fecharCotacoes () {
    	let modal = document.getElementsByClassName("modal-cotacoes")[0];

    	modal.style.display = "none";
     }
    
    function abrirCotacoes () {
    	let modal = document.getElementsByClassName("modal-cotacoes")[0];

    	modal.style.display = "block";
    }  
    
    function limparProduto() {
    	$scope.produto = {};
    }
    
    /*
     * COTAÇÕES
    
    
	$scope.salvarCotacao = function(){
    	service.inserirCotacao($scope.cotacao, function(resp){
    		alert(resp.data);
    		limparCotacao();
    		fecharCadastroCotacao();
    	});
    }
	
	function limparCotacao() {
    	$scope.cotacao = {};
    }

	$scope.onClickNovaCotacao = function(produtoDisponivel) {
		limparCotacao();
		abrirCadastroCotacoes();
		$scope.produtoDisponivel = produtoDisponivel;
		$scope.cotacao.produto = $scope.produtoDisponivel;
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
    */
    $scope.listarProduto();
    //$scope.listarProdutoDisponivelCotacao();
});