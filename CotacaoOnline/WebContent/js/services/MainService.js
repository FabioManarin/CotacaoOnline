angular.module('MainService', []).factory('service', ['$http', function($http){
	const api = "/CotacaoOnline/rest/produto/";
		
    return {   
        getProduto: function(cbSuccess, cbError) {
            return $http.get(api + 'listarProduto', )
                    .then(cbSuccess)
        },
        getComprasId: function(id, cbSuccess, cbError) {
            return $http.get(api + id)
                    .then(cbSuccess)
        },
        addCompras: function(produto, cbSuccess, cbError) {
            return $http.post(api + 'add/', produto)
                    .then(cbSuccess)
        },
        excluirProduto: function(id, cbSuccess, cbError) {
            return $http.delete(api + 'delete/' + id)
                    .then(cbSuccess)
        },
        editarCompras: function(cbSuccess, cbError) {
            return $http.post(api + 'edit/')
                    .then(cbSuccess)
        }
    }
}]);