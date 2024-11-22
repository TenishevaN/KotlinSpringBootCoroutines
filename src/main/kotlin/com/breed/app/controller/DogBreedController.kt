package com.breed.app.controller

import com.breed.app.model.mapper.DogBreedDTO
import com.breed.app.service.DogBreedService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dog-breeds")
class DogBreedController(private val dogBreedService: DogBreedService) {


    @GetMapping("/{name}")
    suspend fun getDogBreedsByName(@PathVariable name: String): Flow<DogBreedDTO> {
        return dogBreedService.getDogBreedsByName(name)
    }
}