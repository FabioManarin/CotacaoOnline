angular.module('MainService', []).factory('service', ['$http', function($http){
	const api = "/CotacaoOnline/rest/";
	
    return {   
        listarProduto: function(comCotacao, cbSuccess, cbError) {
            return $http.get(api + 'produto/listarProduto?comCotacao=' + comCotacao)
                    .then(cbSuccess)
                    .catch(cbError)
        },
        listarProdutoId: function(id, cbSuccess, cbError) {
        	return $http.get(api + 'produto/' + id)
                    .then(cbSuccess)
        },
        inserirProduto: function(produto, cbSuccess, cbError) {
            return $http.post(api + 'produto/inserirProduto/', produto)
                    .then(cbSuccess)
        },
        removerProduto: function(id, cbSuccess, cbError) {
            return $http.delete(api + 'produto/removerProduto/' + id)
                    .then(cbSuccess)
        },
        listarProdutosDisponiveis: function(cbSuccess, cbError) {
        	return $http.get(api + 'produto/listarProdutosDisponiveis/')
        		.then(cbSuccess)
        },
        alterarProduto: function(produto, cbSuccess, cbError) {
            return $http.put(api + 'produto/alterarProduto/', produto)
                    .then(cbSuccess)
        },
        
        /*COTAÇÕES*/
        listarCotacoes: function(idProduto, cbSuccess, cbError) {
            return $http.get(api + 'cotacao/listarCotacao?prod=' + idProduto)
                    .then(cbSuccess)
        },
        inserirCotacao: function(cotacao, cbSuccess, cbError) {
            return $http.post(api + 'cotacao/inserirCotacao/', cotacao)
                    .then(cbSuccess)
        }
    }
}]);