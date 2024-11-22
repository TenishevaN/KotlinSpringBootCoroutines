package com.breed.app.service

import com.breed.app.model.mapper.DogBreedDTO
import com.breed.app.repository.DogBreedRepository
import com.breed.app.repository.SubBreedRepository
import kotlinx.coroutines.flow.*


import org.springframework.stereotype.Service

@Service
class DogBreedService(
    private val dogBreedRepository: DogBreedRepository,
    private val subBreedRepository: SubBreedRepository
) {

    suspend  fun getDogBreedsByName(name: String): Flow<DogBreedDTO> {
        return dogBreedRepository.findAllByName(name)
            .map { breed ->
                val namesList = subBreedRepository.findAllByDogBreedId(breed.id)
                    .map { subBreed -> subBreed.name }
                    .toList()  // This is a suspending operation, collects names into List<String>
                DogBreedDTO(
                    id = breed.id,
                    name = breed.name,
                    subBreeds = namesList
                )
            }
    }

}