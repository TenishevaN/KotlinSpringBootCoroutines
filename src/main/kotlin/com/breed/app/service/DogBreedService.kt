package com.breed.app.service

import com.breed.app.BreedDataInvalidException
import com.breed.app.DatabaseOperationException
import com.breed.app.model.DogBreed
import com.breed.app.repository.DogBreedRepository
import kotlinx.coroutines.flow.toList
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

@Service
class DogBreedService(private val dogBreedRepository: DogBreedRepository) {

    suspend fun addDogBreed(dogBreed: DogBreed): DogBreed {
        val breedId = dogBreed.breed_id
        val breedName = dogBreed.name ?: throw BreedDataInvalidException("Breed name cannot be null")

        try {
            return dogBreedRepository.saveIgnoringConflict(breedId, breedName)
                ?: throw IllegalStateException("Failed to save the dog breed due to unknown reasons.")
        } catch (e: DataAccessException) {
            throw DatabaseOperationException("Failed to save the dog breed: ${e.message}", e)
        }
    }

    suspend fun getAllBreeds(): List<DogBreed> = dogBreedRepository.findAll().toList()
}