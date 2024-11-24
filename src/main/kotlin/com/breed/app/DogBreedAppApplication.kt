package com.breed.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class DogBreedAppApplication

fun main(args: Array<String>) {
    runApplication<DogBreedAppApplication>(*args)
}
