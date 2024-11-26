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
            CREATE TABLE IF NOT EXISTS dogBreeds (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL
            );
            
            """.trimIndent()

        val createSubBreedTable = """
            CREATE TABLE IF NOT EXISTS subBreeds (
                id BIGSERIAL PRIMARY KEY,
                breed_id BIGINT,
                name VARCHAR(255),
                FOREIGN KEY (breed_id) REFERENCES dogBreeds(id)
            );
            
            """.trimIndent()

        val createDogBreedImageTable = """
            CREATE TABLE IF NOT EXISTS dogBreedImage (
                id SERIAL PRIMARY KEY,
                breed_id INTEGER NOT NULL,
                image BYTEA,
                FOREIGN KEY (breed_id) REFERENCES dogBreeds(id)
            );
            
            """.trimIndent()

        databaseClient.sql(createDogBreedTable)
            .fetch()
            .rowsUpdated()
            .doOnSuccess { count: Long? -> println("dogBreed table creation complete " + count) }
            .thenMany(
                databaseClient.sql(createSubBreedTable)
                    .fetch()
                    .rowsUpdated()
            )
            .thenMany(
                databaseClient.sql(createDogBreedImageTable)
                    .fetch()
                    .rowsUpdated()
            )
            .subscribe(
                { count: Long? -> println("subBreed table creation complete " + count) },
                { error: Throwable -> System.err.println("Error during database initialization: " + error.message) })
    }
}
