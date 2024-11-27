package com.breed.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("dogBreedImage")
data class DogBreedImage(
    @Id val id: Int = 0,
    @Column("breed_id") val breedId: Int,
    var image: ByteArray
)
