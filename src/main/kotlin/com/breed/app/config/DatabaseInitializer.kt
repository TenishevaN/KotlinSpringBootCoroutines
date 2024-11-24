package com.breed.app.config

import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component


@Component("databaseInitializer")
class DatabaseInitializer(private val databaseClient: DatabaseClient) {
    init {
        initializeDatabase()
    }

    private fun initializeDatabase() {
        val createDogBreedTable = """
            CREATE TABLE IF NOT EXISTS dogBreed (
                breed_id BIGSERIAL PRIMARY KEY,
                breed_name VARCHAR(255) NOT NULL
            );
            
            """.trimIndent()

        val createSubBreedTable = """
            CREATE TABLE IF NOT EXISTS subBreed (
                subbreed_id BIGSERIAL PRIMARY KEY,
                breed_id BIGINT,
                subbreed_name VARCHAR(255),
                FOREIGN KEY (breed_id) REFERENCES dogBreed(breed_id)
            );
            
            """.trimIndent()

        databaseClient.sql(createDogBreedTable)
            .fetch()
            .rowsUpdated()
            .doOnSuccess { count: Long? -> println("dogBreed table creation complete.") }
            .thenMany(
                databaseClient.sql(createSubBreedTable)
                    .fetch()
                    .rowsUpdated()
            )
            .subscribe(
                { count: Long? -> println("subBreed table creation complete " + count) },
                { error: Throwable -> System.err.println("Error during database initialization: " + error.message) })
    }
}
