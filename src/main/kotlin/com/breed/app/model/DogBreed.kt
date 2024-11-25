package com.breed.app.model


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("dogBreeds")
data class DogBreed(
    @Id val id: Int = 0,
    val name: String
)
