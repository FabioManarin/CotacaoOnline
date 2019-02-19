angular.module('MainService', []).factory('service', ['$http', function($http){
    const api = "/CotacaoOnline/rest/produto/";
    const apiCotacoes = "/CotacaoOnline/rest/cotacao/";

    //const api = "http://cotacaoonline.jelasticlw.com.br/rest/produto/";
		
    return {   
        listarProduto: function(comCotacao, cbSuccess, cbError) {
            return $http.get(api + 'listarProduto?comCotacao=' + comCotacao)
                    .then(cbSuccess)
                    .catch(cbError)
        },
        listarProdutoId: function(id, cbSuccess, cbError) {
            return $http.get(api + id)
                    .then(cbSuccess)
        },
        inserirProduto: function(produto, cbSuccess, cbError) {
            return $http.post(api + 'inserirProduto/', produto)
                    .then(cbSuccess)
        },
        removerProduto: function(id, cbSuccess, cbError) {
            return $http.delete(api + 'removerProduto/' + id)
                    .then(cbSuccess)
        },
        listarProdutosDisponiveis: function(cbSuccess, cbError) {
        	return $http.get(api + 'listarProdutosDisponiveis/')
        		.then(cbSuccess)
        },
        alterarProduto: function(produto, cbSuccess, cbError) {
            return $http.put(api + 'alterarProduto/', produto)
                    .then(cbSuccess)
        },
        
        /*COTAÇÕES*/
        listarCotacoes: function(idProduto, cbSuccess, cbError) {
            return $http.get(apiCotacoes + 'listarCotacao?prod=' + idProduto)
                    .then(cbSuccess)
        },
        inserirCotacao: function(cotacao, cbSuccess, cbError) {
            return $http.post(apiCotacoes + 'inserirCotacao/', cotacao)
                    .then(cbSuccess)
        }
    }
}]);