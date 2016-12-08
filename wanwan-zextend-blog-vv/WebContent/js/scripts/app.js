define([ 'routes', 'loader', 'angularAMD', 'ui-bootstrap',
		'angular-sanitize', 'blockUI', 'ui.route' ], function(routes, loader,
		angularAMD) {
	var app = angular.module("webapp", [  'ngSanitize',
			'ui.bootstrap', 'ui.router' ]);

	app.config(function($stateProvider, $urlRouterProvider) {
		// 配置路由
		if (routes.routes != undefined) {
			angular.forEach(routes.routes, function(route, path) {
				$stateProvider.state(path, {
					templateUrl : route.templateUrl,
					url : route.url,
					resolve : loader(route.dependencies),
					// allowAnonymous: route.allowAnonymous
				});
			});
		}
		// 默认路由
		if (routes.defaultRoute != undefined) {
			$urlRouterProvider.when("", routes.defaultRoute);
		}
	})

	return angularAMD.bootstrap(app);
});
