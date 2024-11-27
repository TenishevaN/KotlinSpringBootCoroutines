package com.breed.app.repository

import com.breed.app.model.DogBreedImage
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DogBreedImageRepository : CoroutineCrudRepository<DogBreedImage, Long> {
    suspend fun findByBreedId(breedId: Long): DogBreedImage?
}
