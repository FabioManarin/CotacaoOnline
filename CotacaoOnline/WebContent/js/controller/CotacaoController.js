angular.module('CotacaoController', []).controller('CotacaoController', function ($scope, service) {
	$scope.listaProdutosDisponiveis = [];
	$scope.listaCotacoes = [];
	$scope.cotacao = {};
	$scope.produtoDisponivel = {};
	
	$scope.listarProdutoDisponivelCotacao = (cb) => {
        service.listarProdutosDisponiveis(function(resp){
        	if(resp.data.length){
        		$scope.listaProdutosDisponiveis = resp.data;
        		showTable();
            	hiddenNoDataMessage();
        	}else{
        		hiddenTable();
        		showNoDataMessage();
        	}
        	
        	if (cb) {
        		cb();
        	}
        });
    }
	
	$scope.salvarCotacao = function(){
		if (!validacoesAntesDeSalvar()) {
    		return;
    	}
		
    	service.inserirCotacao($scope.cotacao, function(resp){
    		limparCotacao();
    		fecharCadastroCotacao();
    		alert(resp.data.message);
    	});
    }
	
	function validacoesAntesDeSalvar() {
    	let cotacao = $scope.cotacao;
    	debugger
    	if (cotacao.valor <= 0) {
    		alert("Valor da cotação deve ser maior que zero.");
    		return false;
    	}
    	
    	return true;
    }
	
	$scope.onClickAtualizar = function(cb) {
		$scope.listarProdutoDisponivelCotacao(cb);
		alert("Registros atualizados com sucesso.")
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
	
	function showTable(){
    	let table = document.getElementById("produtos");
    	let topTableCotacao = document.getElementById("top-table-cotacao");

    	table.style.display = "table";
    	topTableCotacao.style.display = "none";
    }
    
    function hiddenTable(){
    	let table = document.getElementById("produtos");
    	let topTableCotacao = document.getElementById("top-table-cotacao");
    	
    	table.style.display = "none";
    	topTableCotacao.style.display = "block";
    }

    function showNoDataMessage(){
    	let noDataMsg = document.getElementsByClassName("no-data-msg")[0];

    	noDataMsg.style.display = "block";
    }
    
    function hiddenNoDataMessage(){
    	let noDataMsg = document.getElementsByClassName("no-data-msg")[0];

    	noDataMsg.style.display = "none";
    }
	
	$scope.listarProdutoDisponivelCotacao();
	
});