package com.breed.app.repository

import com.breed.app.model.DogBreed
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogBreedRepository : CoroutineCrudRepository<DogBreed, Long> {

    @Query("INSERT INTO dogBreed (breed_id, breed_name) VALUES (:breed_id, :breed_name)")
    suspend fun saveIgnoringConflict(breedId: Long?, breedName: String): DogBreed?

}