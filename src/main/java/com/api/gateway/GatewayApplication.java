package com.api.gateway;

import enums.DestinationRoute;
import enums.Route;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	private static String ecomBaseUrl="http://localhost:8080/";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gateway(RouteLocatorBuilder rlb){
		return rlb
				.routes()
				.route(routeSpec-> routeSpec
					.path(Route.USERS.getPath())

						.filters(gatewayFilterSpec ->
								gatewayFilterSpec.setPath(DestinationRoute.USERS.getPath())
						)
						.uri(ecomBaseUrl)
				)
				.route(r->r.path("/get").filters(filter->filter.addRequestHeader("X-RequestHeader","header")).uri("http://httpbin.org:80"))
				.route(r->r.host("*.myhost.org").filters(filter->filter.addRequestHeader("another 1", "another 1")).uri("http://httpbin.org:80"))
				.route(r->r.path("/user/login").filters(filter->filter.setPath("/user/login")).uri(ecomBaseUrl))
				.build();
	}
}
