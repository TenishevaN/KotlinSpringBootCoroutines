package com.breed.app

import com.breed.app.model.DogBreed
import com.breed.app.model.SubBreed
import com.breed.app.repository.DogBreedRepository
import com.breed.app.repository.SubBreedRepository
import com.breed.app.service.DogBreedService
import com.breed.app.service.SubBreedService
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class DogBreedControllerTest {

    @Test
    fun `should return list of breeds`() = runBlocking {
        // given
        val dogBreedRepository = mock<DogBreedRepository>()
        val dogBreedService = DogBreedService(dogBreedRepository)
        val expectedBreeds = listOf(DogBreed(1, "Labrador"), DogBreed(2, "Beagle"))
        whenever(dogBreedRepository.findAll()).thenReturn(expectedBreeds.asFlow())

        // when
        val result = dogBreedService.getAllBreeds()

        // then
        assertEquals(expectedBreeds, result)
    }

    @Test
    fun `should return list of sub breeds`() = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val expectedSubBreeds = listOf(SubBreed(2, 2, "kelpie"), SubBreed(1, 2, "kerryblue"))
        whenever(subBreedRepository.findAll()).thenReturn(expectedSubBreeds.asFlow())

        //when
        val result = subBreedService.getAllSubBreeds()

        //then
        assertEquals(expectedSubBreeds, result)
    }

    @Test
    fun `should return list of sub breeds for breed id`() = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val expectedSubBreeds = listOf(SubBreed(3, 6, "kelpie"), SubBreed(1, 2, "kerryblue"))
        whenever(subBreedRepository.findByBreedId(1L)).thenReturn(expectedSubBreeds.asFlow())

        //when
        val result = subBreedService.getSubBreedsByBreedId(1L)

        //then
        assertEquals(expectedSubBreeds, result)
    }
}
