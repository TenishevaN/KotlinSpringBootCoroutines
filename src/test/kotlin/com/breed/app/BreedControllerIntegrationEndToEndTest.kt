package com.breed.app

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import com.breed.app.model.SubBreed
import com.breed.app.repository.SubBreedRepository
import kotlinx.coroutines.flow.asFlow
import org.springframework.http.MediaType
import org.assertj.core.api.Assertions.assertThat
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BreedControllerIntegrationEndToEndTest {
    @LocalServerPort
    var port: Int = 0

    @Test
    fun givenRunningService_whenGetSingleCampaign_thenExpectStatus() {
        val webClient = WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()

        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val expectedSubBreeds = listOf(SubBreed(1, 1, "Bulldog"), SubBreed(2, 1, "Boxer"))
        whenever(subBreedRepository.findByBreedId(1L)).thenReturn(expectedSubBreeds.asFlow())

        //when & then
        webClient.get()
            .uri("/v1/breeds/6/sub-breeds")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(SubBreed::class.java)
            .consumeWith<WebTestClient.ListBodySpec<SubBreed>> { response ->
                assertThat(response.responseBody).hasSize(2)
            }
    }
}
