package com.breed.app.model


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("dogBreed")
data class DogBreed(
    @Id val breed_id: Long? = null,
    @Column("name") val name: String
)
