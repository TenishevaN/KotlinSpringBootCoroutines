package com.breed.app.config


import org.springframework.context.annotation.Configuration
import com.breed.app.model.DogBreed
import com.breed.app.model.SubBreed
import com.breed.app.repository.DogBreedRepository
import com.breed.app.repository.SubBreedRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import kotlinx.coroutines.reactor.mono
import org.slf4j.LoggerFactory
import java.util.UUID

@Configuration
class DataLoaderConfiguration1 {

    private val logger = LoggerFactory.getLogger(DataLoaderConfiguration1::class.java)

//    @Bean
//    fun databaseInitializer(
//        webClient: WebClient,
//        dogBreedRepository: DogBreedRepository,
//        subBreedRepository: SubBreedRepository,
//        ctx: ConfigurableApplicationContext
//    ): CommandLineRunner {
//        return CommandLineRunner {
//            mono {
//                try {
//                    val count = dogBreedRepository.countDogBreeds()?.awaitSingleOrNull() ?: 0L
//                    if (count == 0L) {
//                        initializeDogBreeds(webClient, dogBreedRepository, subBreedRepository)
//                        logger.info("Database has been initialized successfully.")
//                    }
//                } catch (e: Exception) {
//                    logger.error("Error initializing database: ${e.message}")
//                    ctx.close() // Close the context if there is an error
//                }
//            }.subscribe()
//        }
//    }

//    private suspend fun initializeDogBreeds(
//        webClient: WebClient,
//        dogBreedRepository: DogBreedRepository,
//        subBreedRepository: SubBreedRepository
//    ) {
//        val response = webClient.get().uri("/breeds/list/all")
//            .retrieve()
//            .bodyToMono(DogBreedsResponse::class.java)
//            .awaitSingle()  // Await the single response within the coroutine
//
//        response.message.forEach { (breed, subBreeds) ->
//            val dogBreed = DogBreed(
//                id = UUID.randomUUID().toString(),
//                name = breed
//            )
//            dogBreedRepository.save(dogBreed)
//            logger.debug("Saving breed: {}", breed)
//
//            subBreeds.forEach { subBreedName ->
//                val subBreed = SubBreed(
//                    id = UUID.randomUUID().toString(),
//                    dogBreedId = dogBreed.id,
//                    name = subBreedName
//                )
//                subBreedRepository.save(subBreed)
//                logger.debug("Saving sub-breed: {}", subBreedName)
//            }
//        }
//    }

//    private suspend fun initializeDogBreeds(
//        webClient: WebClient,
//        dogBreedRepository: DogBreedRepository,
//        subBreedRepository: SubBreedRepository
//    ) {
//        val response = webClient.get().uri("/breeds/list/all")
//            .retrieve()
//            .bodyToMono(DogBreedsResponse::class.java)
//            .awaitSingle()  // This should work with the right imports and dependencies
//
//        response.message.forEach { (breed, subBreeds) ->
//            val dogBreed = DogBreed(
//                id = UUID.randomUUID().toString(),
//                name = breed
//            )
//            dogBreedRepository.save(dogBreed).awaitSingle()  // Ensure completion of save operation
//            logger.debug("Saving breed: {}", breed)
//
//            subBreeds.forEach { subBreedName ->
//                val subBreed = SubBreed(
//                    id = UUID.randomUUID().toString(),
//                    dogBreedId = dogBreed.id,
//                    name = subBreedName
//                )
//                subBreedRepository.save(subBreed).awaitSingle()  // Ensure completion of save operation
//                logger.debug("Saving sub-breed: {}", subBreedName)
//            }
//        }
//    }

    data class DogBreedsResponse(val message: Map<String, List<String>>, val status: String)
}

