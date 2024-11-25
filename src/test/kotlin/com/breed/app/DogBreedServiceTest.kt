package com.breed.app

import com.breed.app.model.DogBreed
import com.breed.app.repository.DogBreedRepository
import com.breed.app.service.DogBreedService
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class DogBreedServiceTest {

    @Test
    fun `should return all dog breeds`(): Unit = runBlocking {
        // given
        val dogBreedRepository = mock<DogBreedRepository>()
        val dogBreedService = DogBreedService(dogBreedRepository)
        val expectedBreeds = listOf(DogBreed(1, "Labrador"), DogBreed(2, "Beagle"))
        whenever(dogBreedRepository.findAll()).thenReturn(expectedBreeds.asFlow())

        // when
        val result = dogBreedService.getAllBreeds()

        // then
        assertEquals(expectedBreeds, result)
        verify(dogBreedRepository).findAll()
    }

    @Test
    fun `should add a dog breed successfully`(): Unit = runBlocking {
        // given
        val dogBreedRepository = mock<DogBreedRepository>()
        val dogBreedService = DogBreedService(dogBreedRepository)
        val dogBreed = DogBreed(3, "Poodle")
        whenever(dogBreedRepository.save(any())).thenReturn(dogBreed)

        // when
        val result = dogBreedService.addDogBreed(dogBreed)

        // then
        assertEquals(dogBreed, result)
        verify(dogBreedRepository).save(dogBreed)
    }

    @Test
    fun `should throw DatabaseOperationException when save fails`(): Unit = runBlocking {
        // given
        val dogBreedRepository = mock<DogBreedRepository>()
        val dogBreedService = DogBreedService(dogBreedRepository)
        val dogBreed = DogBreed(4, "Dalmatian")
        whenever(dogBreedRepository.save(any())).thenThrow(DataIntegrityViolationException("DB Error"))

        // when & then
        val exception = assertFailsWith<DatabaseOperationException> {
            dogBreedService.addDogBreed(dogBreed)
        }

        assertEquals("Failed to save the dog breed: DB Error", exception.message)
        verify(dogBreedRepository).save(dogBreed)
    }
}
