package br.com.lbr.heroes.service;

import br.com.lbr.heroes.model.Hero;
import br.com.lbr.heroes.repository.HeroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public Flux<Hero> findAll() {
        log.info("List all Heroes");
        return heroRepository.findAll();
    }

    public Mono<Hero> findByName(String name) {
        log.info("Get Hero by name - {}", name);
        return heroRepository.findByName(name);
    }

    public Mono<Hero> save(Hero hero) {
        log.info("Creating Hero - {} ", hero);
        return heroRepository.save(hero);
    }

    public Mono<Hero> deleteByName(String name) {
        log.info("Deleting Hero by name - {} ", name);
        return heroRepository.deleteByName(name);
    }
}
