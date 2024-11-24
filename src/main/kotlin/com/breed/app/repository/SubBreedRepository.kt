package com.breed.app.repository

import com.breed.app.model.DogBreed
import com.breed.app.model.SubBreed
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubBreedRepository : CoroutineCrudRepository<SubBreed, Long> {

    @Query("INSERT INTO subBreed (subbreed_id, breed_id, subbreed_name) VALUES (:subbreed_id, : breed_id, :subbreed_name) ON CONFLICT (id) DO NOTHING RETURNING *")
    suspend fun saveIgnoringConflict(subBreed: SubBreed): SubBreed
}