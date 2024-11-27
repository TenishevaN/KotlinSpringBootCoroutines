package com.breed.app.service

import com.breed.app.DatabaseOperationException
import com.breed.app.model.DogBreed
import com.breed.app.repository.DogBreedRepository
import kotlinx.coroutines.flow.toList
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service


@Service
class DogBreedService(private val dogBreedRepository: DogBreedRepository) {

    suspend fun addDogBreed(dogBreed: DogBreed): DogBreed = try {
        dogBreedRepository.save(dogBreed)
    } catch (e: DataAccessException) {
        throw DatabaseOperationException("Failed to save the dog breed: ${e.message}", e)
    }

    suspend fun getAllBreeds(): List<DogBreed> = dogBreedRepository.findAll().toList()

    suspend fun getBreedById(breedId: Long): DogBreed = dogBreedRepository.findById(breedId) ?: throw IllegalStateException("Breed not found")
 }
