package com.breed.app.controller

import com.breed.app.model.DogBreed
import com.breed.app.service.DogBreedService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dog-breeds")
class DogBreedController(private val dogBreedService: DogBreedService) {

    @GetMapping
    suspend fun getAllBreeds(): List<DogBreed> = dogBreedService.getAllBreeds()
}