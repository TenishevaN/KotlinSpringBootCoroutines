package com.breed.app.repository

import com.breed.app.model.DogBreed
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogBreedRepository : CoroutineCrudRepository<DogBreed, Long> {

    suspend fun findByName(name: String): DogBreed?

}
