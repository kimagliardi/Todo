package com.leanix.service

import com.leanix.repository.TodoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TodoServiceTest {
    private val todoRepository = mockk<TodoRepository>()

    private val todoService = TodoService(todoRepository)

    @Test
    fun `should return an empty list of todos`(){
        every {
            todoRepository.findAll()
        } returns emptyList()

        val result = todoService.listAll()
        assertTrue(result.isEmpty())

        verify(exactly = 1) {todoRepository.findAll()}
    }

}