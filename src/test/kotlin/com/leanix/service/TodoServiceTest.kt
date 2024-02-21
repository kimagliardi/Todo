package com.leanix.service

import com.leanix.model.Task
import com.leanix.model.Todo
import com.leanix.repository.TodoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class TodoServiceTest {
    private val todoRepository = mockk<TodoRepository>()

    private val todoService = TodoService(todoRepository)


    //listAll tests
    @Test
    fun `should return an empty list of todos`(){
        every {
            todoRepository.findAll()
        } returns emptyList()

        val result = todoService.listAll()
        assertTrue(result.isEmpty())

        verify(exactly = 1) {todoRepository.findAll()}
    }

    @Test
    fun `should return a list with todos`(){
        val expectedTodos = listOf(
            Todo(name="First Todo", description = "First subtask"),
            Todo(name="Second Todo", description = "Second subtask")
        )

        every { todoRepository.findAll() } returns expectedTodos
        val  actualTodos = todoService.listAll()

        assertEquals(expectedTodos, actualTodos)
    }

    //findById
    @Test
    fun `not found todo by ID`(){
        val id = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee")
        val expectedTodos: Todo? = null
        every { todoRepository.findById(id) } returns null
        val  actualTodo = todoRepository.findById(id)

        assertEquals(expectedTodos, actualTodo)
    }

    @Test
    fun `found todo by ID`() {
        // Setup
        val expectedId = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee")
        val expectedTodo = Todo(id = expectedId, name = "Test", description = "Test Description")

        // Mock the repository's findById method to return an Optional of expectedTodo
        every { todoRepository.findById(expectedId) } returns Optional.of(expectedTodo)

        // Act
        val result = todoService.findTodoById(expectedId)

        // Assert
        assertEquals(expectedTodo, result, "The found todo should match the expected one")
    }

    // Delete Todo
    @Test

    fun `deleted todo by ID`(){
        val expectedId = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee")
        val expectedResult = Unit

        every { todoRepository.deleteById(expectedId) } returns Unit

        val result = todoRepository.deleteById(expectedId)

        assertEquals(expectedResult, result)
    }
    @Test
    fun `not found todo to be deleted`(){
        val expectedId = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee")
        val expectedResult = Unit

        every { todoRepository.deleteById(expectedId) } returns Unit

        val result = todoRepository.deleteById(expectedId)

        assertEquals(expectedResult, result)
    }


    // Update Todo
    @Test
    fun `Todo was updated`() {
        val task1 = Task(name = "test Task", description = "This is a test task")
        val actualTodo = Todo(
            id = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee"),
            name = "Test",
            description = "Test Description",
            tasks = mutableListOf(task1)
        )
        every { todoRepository.findById(actualTodo.id!!) } returns Optional.of(actualTodo)

        val task2 = Task(name = "This is a test", description = "updating a todo")
        val updatedTodo = Todo(id = actualTodo.id, name = actualTodo.name, tasks = mutableListOf(task2))

        every { todoRepository.update(actualTodo) } returns updatedTodo
        val response = todoService.updateTodo(actualTodo.id!!, actualTodo)


        assertEquals(updatedTodo, response)
    }

    @Test
    fun `Todo was not updated due to missing name`() {
        val task1 = Task(name = "test Task", description = "This is a test task")
        val actualTodo = Todo(
            id = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee"),
            name = "Test",
            description = "Test Description",
            tasks = mutableListOf(task1)
        )
        every { todoRepository.findById(actualTodo.id!!) } returns Optional.of(actualTodo)

        val task2 = Task(name = "This is a test", description = "updating a todo")
        val updatedTodo = Todo(id = actualTodo.id, name = "", tasks = mutableListOf(task2))

        every { todoRepository.update(actualTodo) } returns null
        val response = todoService.updateTodo(actualTodo.id!!, actualTodo)


        assertEquals(null, response)
    }

    @Test
    fun `Todo was not updated due to missing name in the task`() {
        val task1 = Task(name = "test Task", description = "This is a test task")
        val actualTodo = Todo(
            id = UUID.fromString("381483a2-843c-42ca-80c2-5ecd4a0bf6ee"),
            name = "Test",
            description = "Test Description",
            tasks = mutableListOf(task1)
        )
        every { todoRepository.findById(actualTodo.id!!) } returns Optional.of(actualTodo)

        val task2 = Task(name = "This is a test", description = "")
        val updatedTodo = Todo(id = actualTodo.id, name = actualTodo.name, tasks = mutableListOf(task2))

        every { todoRepository.update(actualTodo) } returns null
        val response = todoService.updateTodo(actualTodo.id!!, actualTodo)


        assertEquals(null, response)
    }

}
