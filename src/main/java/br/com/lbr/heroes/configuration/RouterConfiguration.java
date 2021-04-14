package br.com.lbr.heroes.configuration;

import br.com.lbr.heroes.handler.HeroHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class RouterConfiguration implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> routes(HeroHandler heroHandler) {
        return RouterFunctions.route()
                .GET("/heroes", heroHandler::listHeroes)
                .GET("/heroes/{name}", heroHandler::getHero)
                .POST("/heroes/create", heroHandler::createHero)
                .DELETE("/heroes/{name}", heroHandler::deleteHero)
                .build();
    }
}
