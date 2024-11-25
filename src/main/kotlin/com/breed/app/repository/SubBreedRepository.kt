package com.breed.app.repository

import com.breed.app.model.SubBreed
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubBreedRepository : CoroutineCrudRepository<SubBreed, Long> {

    fun findByBreedId(breedId: Long): Flow<SubBreed>

}
