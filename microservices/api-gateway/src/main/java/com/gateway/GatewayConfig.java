package com.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().
                route(
                        "food-service",
                        route ->
                                route.path("/foods/**")
                                        .filters(f ->
                                                f.rewritePath("/foods/?(?<remaining>.*)", "/${remaining}")
                                                        .circuitBreaker(circuitBreakerConfig ->
                                                                circuitBreakerConfig.setName("circuitBreakerFood")
                                                                        .setFallbackUri("forward:/circuitBreaker/fallback")
                                                        )
                                                        .requestRateLimiter(rateConfig->
                                                                rateConfig.setRateLimiter(rateLimiter())
                                                                        .setKeyResolver(keyResolver())
                                                                )
                                        )
                                        .uri("lb://food-service")


                ).
                route(
                        "restaurant-service",
                        route -> route.path("/restaurants/**")
                                .filters(f ->

                                        f.rewritePath("/restaurants/?(?<remaining>.*)", "/${remaining}").
                                                addRequestHeader("ExtraValue", "Samosa")
                                                .retry(retryConfig ->
                                                        retryConfig.setRetries(3)
                                                                .setMethods(HttpMethod.GET)
                                                                .setBackoff(
                                                                        Duration.ofMillis(100),
                                                                        Duration.ofMillis(800),
                                                                        2,
                                                                        true
                                                                )
                                                )

                                )
                                .uri("lb://restaurant-service")


                ).

                build();

    }



    //    key resolver
    //If you want to limit based on user IP
    @Bean(name = "keyResolver")
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress());
    }

//


    //    Rate Limiter bean:
    @Bean
    public RateLimiter rateLimiter() {
        return new RedisRateLimiter(1,1,1);
    }
}
