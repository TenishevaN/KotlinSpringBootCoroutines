package com.breed.app.model

import org.springframework.data.relational.core.mapping.Table

import org.springframework.data.annotation.Id

@Table("dog_breed")
data class DogBreed(
    @Id val id: String,
    val name: String
)

@Table("sub_breed")
data class SubBreed(
    @Id val id: String,
    val dogBreedId: String,
    val name: String
)