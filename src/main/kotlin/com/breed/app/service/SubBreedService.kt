package com.breed.app.service

import com.breed.app.DatabaseOperationException
import com.breed.app.model.SubBreed
import com.breed.app.repository.SubBreedRepository
import kotlinx.coroutines.flow.toList
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

@Service
class SubBreedService(private val subBreedRepository: SubBreedRepository) {

    suspend fun addSubBreed(subBreed: SubBreed): SubBreed = try {
        subBreedRepository.save(subBreed)
    } catch (e: DataAccessException) {
        throw DatabaseOperationException("Failed to save the sub breed: ${e.message}", e)
    }

    suspend fun getAllSubBreeds(): List<SubBreed> = subBreedRepository.findAll().toList()

    suspend fun getSubBreedsByBreedId(breedId: Long): List<SubBreed> =
        subBreedRepository.findByBreedId(breedId).toList()

}
