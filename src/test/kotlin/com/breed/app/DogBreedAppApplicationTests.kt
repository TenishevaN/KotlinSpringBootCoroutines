package com.breed.app

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient


@SpringBootTest
class DogBreedAppApplicationTests {

    @Autowired
    private val databaseClient: DatabaseClient? = null

    @Test
    fun contextLoads() {
    }
}
