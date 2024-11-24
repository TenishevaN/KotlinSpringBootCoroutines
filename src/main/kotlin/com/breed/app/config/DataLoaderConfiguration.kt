package com.breed.app.config

import com.breed.app.model.DogBreed
import com.breed.app.repository.DogBreedRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.boot.ApplicationRunner
import org.springframework.web.reactive.function.client.WebClient
import org.slf4j.LoggerFactory
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.reactor.awaitSingle
import java.util.*

@Configuration
class DataLoaderConfiguration(private val webClient: WebClient) {

    private val logger = LoggerFactory.getLogger(DataLoaderConfiguration::class.java)

    @Bean
    fun databaseInitializer(breedRepository: DogBreedRepository) = ApplicationRunner {
        runBlocking {
             if (breedRepository.count() == 0L) {
                logger.info("No breeds found in the database, initializing data...")

                val response = webClient.get().uri("/breeds/list/all")
                    .retrieve()
                    .bodyToMono(Map::class.java)
                    .awaitSingle()

                val breedMap = response["message"] as Map<String, List<String>>
                val breeds = breedMap.map { (breed) ->
                    DogBreed( id = UUID.randomUUID().toString(), name = breed)
                }

               breedRepository.saveAll(breeds)

                breeds.forEach { breed ->
                    logger.info("Added breed: ${breed.name}")
                }

                logger.info("Breeds initialized in the database.")
            } else {
                logger.info("Breeds already exist in the database. Skipping initialization.")
            }
        }
    }
}