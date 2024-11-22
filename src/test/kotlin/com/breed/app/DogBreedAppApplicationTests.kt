package com.breed.app

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DogBreedAppApplicationTests {

    @Autowired
    private val databaseClient: DatabaseClient? = null

    @Test
    fun contextLoads() {
    }

//    @Test
//    fun testDatabaseConnection() {
//        databaseClient!!.sql("SELECT * FROM dog_breed")
//            .fetch()
//            .first()
//            .subscribe(
//                { result: Map<String?, Any?> ->
//                    println(
//                        "Query succeeded: $result"
//                    )
//                },
//                { error: Throwable -> System.err.println("Query failed: $error") })
//    }

}
