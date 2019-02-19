angular.module('CotacaoOnline', ['MainController', 'MainService', 'CotacaoController'])
    .config([
        '$compileProvider',
        function ($compileProvider) {
            $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension|magnet):/);
        }
]);;