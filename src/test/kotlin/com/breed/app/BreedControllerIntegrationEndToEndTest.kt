package com.breed.app

import com.breed.app.model.DogBreed
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Import(TestConfig::class)
class BreedControllerIntegrationEndToEndTest(
    @Autowired private val webTestClient: WebTestClient,
    @Autowired private val flyway: Flyway
) {

    @BeforeEach
    fun setup() {
        flyway.clean()
        flyway.migrate()
    }

    @Test
    fun `Test Get All Breeds`() {
        webTestClient.get().uri("/v1/breeds")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(DogBreed::class.java)
            .consumeWith<WebTestClient.ListBodySpec<DogBreed>> { response ->
                Assertions.assertTrue(response.responseBody!!.size > 0)
            }
    }
}
