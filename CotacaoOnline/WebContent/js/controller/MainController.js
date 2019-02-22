angular.module('MainController', []).controller('MainController', function ($scope, service) {
	$scope.listaProdutos = [];
	$scope.listaCotacoes = [];
	$scope.produto = {};
	$scope.produtoDisponivel = {};
	$scope.produtosComCotacao = false;
	$scope.produtoAtual = {};
	
    $scope.listarProduto = (cb) => {
        service.listarProduto($scope.produtosComCotacao, function(resp){
        	if (resp.data.length) {
            	$scope.listaProdutos = resp.data;
            	showTable();
            	hiddenNoDataMessage();
        	} else {
        		$scope.listaProdutos = [];
        		hiddenTable();
        		showNoDataMessage();
        	}

        	if (cb) {
        		cb();
        	}
        });
    }
    
    $scope.salvar = function(){
    	if (!validacoesAntesDeSalvar()) {
    		return;
    	}
    	
    	if ($scope.produto.id){
    		service.alterarProduto($scope.produto, function(resp){
	    		limparProduto();
	    		$scope.listarProduto();
				fecharCadastro();
				alert(resp.data.message)
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
    		$scope.listarProduto(() => alert(resp.data.message));
    	});
    }
    
    $scope.onClickAbrirCotacoes = function (idProduto) {
    	listarCotacoes(idProduto);
    	
    	let produtoAtual = $scope.listaProdutos.find(item => item.id == idProduto);

    	$scope.produtoAtual = {
    		nome: produtoAtual.nome,
    		id: idProduto
    	}; 	
    }
    
    $scope.onClickAtualizar = function(cb) {
    	$scope.listarProduto(cb);
    	alert("Registros atualizados com sucesso.")
    }
    
    $scope.onChangeFiltro = function () {
    	$scope.listarProduto();
    }
    
    function validacoesAntesDeSalvar() {
    	let produto = $scope.produto;

    	if (produto.dataInicialCotacao > produto.dataFinalCotacao) {
    		alert("Data incial não pode ser menor que a data final.");
    		return false;
    	}
    	
    	return true;
    }
    
    function showTable(){
    	let table = document.getElementById("produtos");
    	let hr = document.getElementById("hr");

    	table.style.display = "table";
    	hr.style.display = "none";
    }
    
    function hiddenTable(){
    	let table = document.getElementById("produtos");
    	let hr = document.getElementById("hr");

    	table.style.display = "none";
    	hr.style.display = "block";

    }
    
    function showNoDataMessage(){
    	let noDataMsg = document.getElementsByClassName("no-data-msg")[0];

    	noDataMsg.style.display = "block";
    }
    
    function hiddenNoDataMessage(){
    	let noDataMsg = document.getElementsByClassName("no-data-msg")[0];

    	noDataMsg.style.display = "none";
    }
    
    
    function listarCotacoes(idProduto) {
    	 service.listarCotacoes(idProduto, function(resp){
         	$scope.listaCotacoes = resp.data;
        	
         	if ($scope.listaCotacoes.length > 0) {
         		abrirCotacoes();
         	} else {
         		alert('Não há cotações para exibir.')
         	}
         	        	
         });
    	 
    	 limparCotacao();
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
    
    function limparCotacao() {
    	$scope.listaCotacoes = [];
    }
    
    $scope.listarProduto();
   
});