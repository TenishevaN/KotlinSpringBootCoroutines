package com.breed.app

import com.breed.app.model.DogBreedImage
import com.breed.app.repository.DogBreedImageRepository
import com.breed.app.service.DogBreedImageService
import com.breed.app.service.DogBreedService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.springframework.web.reactive.function.client.WebClient

class DogBreedImageServiceTest {

    private val webClient = mockk<WebClient>(relaxed = true)
    private val dogBreedImageRepository = mockk<DogBreedImageRepository>(relaxed = true)
    private val dogBreedService = mockk<DogBreedService>(relaxed = true)
    private val service = DogBreedImageService(webClient, dogBreedImageRepository, dogBreedService)


    @Test
    fun `should return image from repository if exists`() = runBlocking {
        val breedId = 1L
        val expectedImage = byteArrayOf(1, 2, 3)
        val breedImage = DogBreedImage(id = 1, breedId = 1, image = expectedImage)

        coEvery { dogBreedImageRepository.findByBreedId(breedId) } returns breedImage

        val result = service.getBreedImage(breedId)

        assertArrayEquals(expectedImage, result)
    }
}
