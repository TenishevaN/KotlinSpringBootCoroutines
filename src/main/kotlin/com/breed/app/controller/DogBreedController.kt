package com.breed.app.controller

import com.breed.app.model.DogBreed
import com.breed.app.model.SubBreed
import com.breed.app.service.DogBreedImageService
import com.breed.app.service.DogBreedService
import com.breed.app.service.SubBreedService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType;

@RestController
@RequestMapping("v1/breeds")
class DogBreedController(
    private val dogBreedService: DogBreedService,
    private val subBreedService: SubBreedService,
    private val dogBreedImageService: DogBreedImageService
) {

    @GetMapping
    suspend fun getAllBreeds(): List<DogBreed> = dogBreedService.getAllBreeds()

    @GetMapping("/sub-breeds")
    suspend fun getAllSubBreeds(): List<SubBreed> = subBreedService.getAllSubBreeds()

    @GetMapping("/{breedId}/sub-breeds")
    suspend fun getSubBreedsByBreedId(@PathVariable breedId: Long): List<SubBreed> {
        return subBreedService.getSubBreedsByBreedId(breedId)
    }

    @GetMapping("/{breedId}/image")
    suspend fun getBreedImage(@PathVariable breedId: Long): ResponseEntity<Any> {
        return try {
            val image = dogBreedImageService.getBreedImage(breedId)
            ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image)
        } catch (e: IllegalStateException) {
            ResponseEntity.badRequest().body(e.message)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body("An unexpected error occurred: ${e.message}")
        }
    }
}
