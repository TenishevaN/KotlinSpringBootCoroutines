package com.breed.app.config

import com.breed.app.model.DogBreed
import com.breed.app.model.SubBreed
import com.breed.app.repository.DogBreedRepository
import com.breed.app.service.DogBreedService
import com.breed.app.service.SubBreedService
import org.springframework.context.annotation.Bean
import org.springframework.boot.ApplicationRunner
import org.springframework.web.reactive.function.client.WebClient
import org.slf4j.LoggerFactory
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.awaitBody


@Component
@DependsOn("databaseInitializer")
class DataLoaderConfiguration(
    private val webClient: WebClient,
    private val breedService: DogBreedService,
    private val breedRepository: DogBreedRepository,
    private val subBreedService: SubBreedService
) {

    private val logger = LoggerFactory.getLogger(DataLoaderConfiguration::class.java)

    @Bean
    fun Initialize() = ApplicationRunner {
        runBlocking {
            if (breedRepository.count() == 0L) {
                logger.info("No breeds found in the database, initializing data...")

                val response: Map<String, Any> = webClient.get()
                    .uri("/breeds/list/all")
                    .retrieve()
                    .awaitBody()

                @Suppress("UNCHECKED_CAST")
                val breedMap = response["message"] as? Map<String, List<String>> ?: error("Invalid data format")
                    breedMap.forEach { (breed, subBreeds) ->
                    val dogBreed = DogBreed(id = 0, name = breed)
                    val savedDogBreed = breedService.addDogBreed(dogBreed)

                    subBreeds.forEach { subBreed ->
                        val subBreedEntity = SubBreed(
                            id = 0,
                            breedId = savedDogBreed.id,
                            name = subBreed
                        )
                        subBreedService.addSubBreed(subBreedEntity)
                    }

                    logger.info("Added breed: ${savedDogBreed.name} with ID: ${savedDogBreed.id}")
                }

                logger.info("Breeds and sub-breeds initialized in the database.")
            } else {
                logger.info("Breeds already exist in the database. Skipping initialization.")
            }
        }
    }
}
