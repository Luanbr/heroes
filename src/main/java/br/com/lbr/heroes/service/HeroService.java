package br.com.lbr.heroes.service;

import br.com.lbr.heroes.model.Hero;
import br.com.lbr.heroes.repository.HeroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Flux<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Mono<Hero> findByName(String name) {
        return heroRepository.findByName(name);
    }

    public Mono<Hero> save(Hero hero) {
        return heroRepository.save(hero);
    }

    public Mono<Hero> deleteByName(String name) {
        return heroRepository.deleteByName(name);
    }
}
