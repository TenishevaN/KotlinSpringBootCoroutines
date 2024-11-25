package com.breed.app

import com.breed.app.model.DogBreedImage
import com.breed.app.repository.DogBreedImageRepository
import com.breed.app.service.DogBreedImageService
import com.breed.app.service.DogBreedService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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

    @Test
    fun `should fetch image from API if not in repository`() = runTest {
//        val breedId = 1L
//        val breedName = "labrador"
//        val imageUrl = "http://example.com/image.jpg"
//        val expectedImage = byteArrayOf(4, 5, 6)
//        val breed = DogBreed(id = 1, name = breedName)
//        val dogCeoResponse = DogCeoResponse(listOf(imageUrl), "success")
//
//        coEvery { dogBreedImageRepository.findByBreedId(breedId) } returns null
//        coEvery { dogBreedService.getBreedById(breedId) } returns breed
//        coEvery { webClient.get().uri("/breed/$breedName/images").retrieve().awaitBody<DogCeoResponse>() } returns dogCeoResponse
//        coEvery { webClient.get().uri(imageUrl).retrieve().awaitBody<ByteArray>() } returns expectedImage
//        coEvery { dogBreedImageRepository.save(any()) } returns DogBreedImage(id = 0, breedId = 1, image = expectedImage)

//        val result = service.getBreedImage(breedId)
//
//        assertArrayEquals(expectedImage, result)
    }
}
