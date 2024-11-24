package com.breed.app.model.mapper

data class DogBreedDTO(
    val id: String,
    val name: String,
    val subBreeds: List<String>
)