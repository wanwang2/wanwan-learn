define([], function () {
    return {
        defaultRoute: '/start',
        routes: {
            'start': {
                templateUrl: 'views/WeiWei.html',
                url: '/start',
                dependencies: ['controller/WeiWeiController'],
                allowAnonymous: true
            } ,
            
        }
    };
});