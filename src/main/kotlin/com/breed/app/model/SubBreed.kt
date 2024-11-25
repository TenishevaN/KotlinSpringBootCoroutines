package com.breed.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("subBreeds")
data class SubBreed(
    @Id val id: Int = 0,
    @Column("breed_id") val breedId:Int,
    val name: String
)
