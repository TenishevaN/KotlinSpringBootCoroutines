package com.breed.app.service

import com.breed.app.model.DogBreedImage
import com.breed.app.repository.DogBreedImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class DogBreedImageService(
    private val webClient: WebClient,
    private val dogBreedImageRepository: DogBreedImageRepository,
    private val dogBreedService: DogBreedService
) {

    suspend fun getBreedImage(breedId: Long): ByteArray {
        val breedImage = dogBreedImageRepository.findByBreedId(breedId)
        if (breedImage != null) {
            return breedImage.image
        } else {
            val breed = dogBreedService.getBreedById(breedId)
            val imageUrl = fetchImageUrlFromApi(breed.name)
            val imageBytes = downloadImage(imageUrl)
            val newBreedImage = DogBreedImage(id = 0, breedId = breed.id, image = imageBytes)
            dogBreedImageRepository.save(newBreedImage)
            return imageBytes
        }
    }

    private suspend fun fetchImageUrlFromApi(breedName: String): String {
        val response: DogCeoResponse =
            webClient.get().uri("/breed/$breedName/images").retrieve().awaitBody<DogCeoResponse>()
        return response.message.firstOrNull() ?: throw IllegalStateException("No image URL found for breed: $breedName")
    }


    private suspend fun downloadImage(url: String): ByteArray {
        return webClient.get().uri(url).retrieve().awaitBody<ByteArray>()
    }
}

data class DogCeoResponse(val message: List<String>, val status: String)
