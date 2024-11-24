package com.breed.app.service

import com.breed.app.model.SubBreed
import com.breed.app.repository.SubBreedRepository
import org.springframework.stereotype.Service

@Service
class SubBreedService(private val subBreedRepository: SubBreedRepository) {

    suspend fun addSubBreed(subBreed: SubBreed): SubBreed? {
        return subBreedRepository.saveIgnoringConflict(subBreed)
    }
}