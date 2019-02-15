angular.module('MainService', []).factory('service', ['$http', function($http){
    const api = "/CotacaoOnline/rest/produto/";
    //const api = " http://contacaoonlineteste.jelasticlw.com.br/rest/produto/";
		
    return {   
        listarProduto: function(cbSuccess, cbError) {
            return $http.get(api + 'listarProduto')
                    .then(cbSuccess)
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
        alterarProduto: function(produto, cbSuccess, cbError) {
            return $http.post(api + 'alterarProduto/', produto)
                    .then(cbSuccess)
        }
    }
}]);