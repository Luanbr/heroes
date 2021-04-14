package br.com.lbr.heroes;

import br.com.lbr.heroes.model.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@AutoConfigureWebTestClient
@SpringBootTest
public class HeroRouterTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void whenCreateHero_thenCreateAndReturnHTTPStatusCreated() {
        final var expectedName = "KATANA";
        final var biography = "Wielder of the fearsome Soultaker Sword, Tatsu Yamashiro escaped a tragic past to fight alongside the Outsiders and the Suicide Squad.";
        final var expectedGender = "woman";
        final var expectedUniverse = "DC Universe";
        Hero hero = new Hero(expectedName, biography, expectedGender, expectedUniverse, List.of("exceptional martial artist", "supernatural knowledge", "master swordsman"));

        webTestClient.post()
                .uri("/heroes/create")
                .bodyValue(hero)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("name").isEqualTo(expectedName)
                .jsonPath("biography").isEqualTo(biography)
                .jsonPath("gender").isEqualTo(expectedGender)
                .jsonPath("universe").isEqualTo(expectedUniverse);
    }

    @Test
    public void whenGetHeroExists_thenReturnCorrect() {
        final var expectedName = "KATANA_VALIDATE_EXISTS";
        final var biography = "Test Biography.";
        final var expectedGender = "woman";
        final var expectedUniverse = "DC Universe";
        Hero hero = new Hero(expectedName, biography, expectedGender, expectedUniverse, List.of("exceptional martial artist", "supernatural knowledge", "master swordsman"));

        webTestClient.post()
                .uri("/heroes/create")
                .bodyValue(hero)
                .exchange();

        webTestClient.get()
                .uri("/heroes/" + expectedName)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("name").isEqualTo(expectedName)
                .jsonPath("biography").isEqualTo(biography)
                .jsonPath("gender").isEqualTo(expectedGender)
                .jsonPath("universe").isEqualTo(expectedUniverse);
    }

    @Test
    public void whenGetHeroNotExists_thenReturnHTTPNotFound() {
        final var expectedName = "KATANA_NOTFOUND";

        webTestClient.get()
                .uri("/heroes/" + expectedName)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }

    @Test
    public void whenListHeroes_thenReturnHTTPStatusOk() {
        webTestClient.get()
                .uri("/heroes")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void whenDeleteHeroSuccess_thenReturnHTTPStatusOk() {
        final var expectedName = "KATANA_DELETE_TEST";
        final var biography = "Wielder of the fearsome Soultaker Sword, Tatsu Yamashiro escaped a tragic past to fight alongside the Outsiders and the Suicide Squad.";
        final var gender = "woman";
        final var universe = "DC Universe";
        Hero hero = new Hero(expectedName, biography, gender, universe, List.of("exceptional martial artist", "supernatural knowledge", "master swordsman"));

        webTestClient.post()
                .uri("/heroes/create")
                .bodyValue(hero)
                .exchange()
                .expectStatus().isCreated();

        webTestClient.delete()
                .uri("/heroes/" + expectedName)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        webTestClient.get()
                .uri("/heroes/" + expectedName)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }

    @Test
    public void whenTryDeleteHeroThatNotExists_thenReturnHTTPStatusNotFound() {
        final var expectedName = "KATANA_DELETE_TEST_NOTFOUND";

        webTestClient.delete()
                .uri("/heroes/" + expectedName)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }
}
