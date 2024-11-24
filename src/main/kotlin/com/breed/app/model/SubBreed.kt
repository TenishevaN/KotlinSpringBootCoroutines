package com.breed.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("subBreed")
data class SubBreed(
    @Id val subbreed_id: Long? = null,
    @Column("breed_id") val breedId:Long,
    @Column("subbreed_name") val name: String
)
