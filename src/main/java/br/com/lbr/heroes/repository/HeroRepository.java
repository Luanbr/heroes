package br.com.lbr.heroes.repository;

import br.com.lbr.heroes.model.Hero;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface HeroRepository extends ReactiveMongoRepository<Hero, String> {
    Mono<Hero> findByName(String name);
    Mono<Hero> deleteByName(String name);
}
