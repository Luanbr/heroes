package br.com.lbr.heroes.handler;

import br.com.lbr.heroes.model.Hero;
import br.com.lbr.heroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class HeroHandler {
    @Autowired
    private HeroService service;

    public Mono<ServerResponse> listHeroes(ServerRequest request) {
        Flux<Hero> heroes = service.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(heroes, Hero.class);
    }

    public Mono<ServerResponse> getHero(ServerRequest request) {
        String name = request.pathVariable("name");
        return service.findByName(name)
                .flatMap(hero -> ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(hero))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createHero(ServerRequest request) {
        return request.bodyToMono(Hero.class)
                .flatMap(hero -> {
                   Mono<Hero> savedHero = service.save(hero);
                   return ServerResponse.created(URI.create("/heroes/")).contentType(APPLICATION_JSON)
                           .body(savedHero, Hero.class);
                });
    }

    public Mono<ServerResponse> deleteHero(ServerRequest request) {
        String name = request.pathVariable("name");
        return service.deleteByName(name)
                .flatMap(hero -> ServerResponse.ok().contentType(APPLICATION_JSON).build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
