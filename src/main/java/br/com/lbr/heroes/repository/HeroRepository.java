package br.com.lbr.heroes.repository;

import br.com.lbr.heroes.model.Hero;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface HeroRepository extends ReactiveMongoRepository<Hero, String> {
    Mono<Hero> findByName(String name);
    Mono<Hero> deleteByName(String name);
}
