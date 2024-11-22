package com.breed.app.repository

import com.breed.app.model.DogBreed
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DogBreedRepository : CoroutineCrudRepository<DogBreed, String> {

    suspend fun findAllByName(name: String): Flow<DogBreed>
}