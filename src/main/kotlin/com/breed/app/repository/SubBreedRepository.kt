package com.breed.app.repository

import com.breed.app.model.SubBreed
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import kotlinx.coroutines.flow.Flow

interface SubBreedRepository : CoroutineCrudRepository<SubBreed, String> {
    fun findAllByDogBreedId(dogBreedId: String): Flow<SubBreed>
}