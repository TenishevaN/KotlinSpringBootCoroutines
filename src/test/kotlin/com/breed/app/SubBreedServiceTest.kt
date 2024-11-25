package com.breed.app

import com.breed.app.DatabaseOperationException
import com.breed.app.model.SubBreed
import com.breed.app.repository.SubBreedRepository
import com.breed.app.service.SubBreedService
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class SubBreedServiceTest {

    @Test
    fun `should return all sub breeds`(): Unit = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val expectedSubBreeds = listOf(SubBreed(1, 1,"Bulldog"), SubBreed(2, 2,"Boxer"))
        whenever(subBreedRepository.findAll()).thenReturn(expectedSubBreeds.asFlow())

        // when
        val result = subBreedService.getAllSubBreeds()

        // then
        assertEquals(expectedSubBreeds, result)
        verify(subBreedRepository).findAll()
    }

    @Test
    fun `should add a sub breed successfully`(): Unit = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val subBreed = SubBreed(3, 3,"Pug")
        whenever(subBreedRepository.save(any())).thenReturn(subBreed)

        // when
        val result = subBreedService.addSubBreed(subBreed)

        // then
        assertEquals(subBreed, result)
        verify(subBreedRepository).save(subBreed)
    }

    @Test
    fun `should throw DatabaseOperationException when save fails`(): Unit = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val subBreed = SubBreed(4, 4,"Terrier")
        whenever(subBreedRepository.save(any())).thenThrow(DataIntegrityViolationException("DB Error"))

        // when & then
        val exception = assertFailsWith<DatabaseOperationException> {
            subBreedService.addSubBreed(subBreed)
        }

        assertEquals("Failed to save the sub breed: DB Error", exception.message)
        verify(subBreedRepository).save(subBreed)
    }

    @Test
    fun `should return all sub breeds for breed id`(): Unit = runBlocking {
        // given
        val subBreedRepository = mock<SubBreedRepository>()
        val subBreedService = SubBreedService(subBreedRepository)
        val expectedSubBreeds = listOf(SubBreed(1, 1,"Bulldog"), SubBreed(2, 2,"Boxer"))
        whenever(subBreedRepository.findByBreedId(1L)).thenReturn(expectedSubBreeds.asFlow())

        // when
        val result = subBreedService.getSubBreedsByBreedId(1L)

        // then
        assertEquals(expectedSubBreeds, result)
        verify(subBreedRepository).findByBreedId(1L)
    }
}
