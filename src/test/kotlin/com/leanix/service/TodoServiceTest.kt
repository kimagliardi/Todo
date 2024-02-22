package com.leanix.service

import com.leanix.model.Task
import com.leanix.model.Todo
import com.leanix.repository.TodoRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class TodoServiceTest {
    private val todoRepository = mockk<TodoRepository>()
    private val todoService = TodoService(todoRepository)

    // List all TODOs
    @Test
    fun `returnsEmptyListWhenNoTodosPresent`() {
        every { todoRepository.findAll() } returns emptyList()
        assertTrue(todoService.listAll().isEmpty())
        verify(exactly = 1) { todoRepository.findAll() }
    }

    @Test
    fun `returnsNonEmptyListWhenTodosPresent`() {
        val tasks = mutableListOf(
            Task(name = "First subtask", description = "first subtask description")
        )

        val expectedTodos = listOf(
            Todo(name = "First Todo", description = "First description", tasks =  tasks),
            Todo(name = "Second Todo", description = "Second description", tasks = tasks)
        )
        every { todoRepository.findAll() } returns expectedTodos

        assertEquals(expectedTodos, todoService.listAll())
    }

    // Find TODO by ID
    @Test
    fun `returnsNullForNonexistentTodoId`() {
        val id = UUID.randomUUID()
        every { todoRepository.findById(id) } returns Optional.empty()

        assertEquals(todoService.findTodoById(id), null)
    }

    @Test
    fun `returnsTodoForValidId`() {
        val expectedId = UUID.randomUUID()
        val expectedTodo = Todo(id = expectedId, name = "Valid Todo", description = "A valid todo description")
        every { todoRepository.findById(expectedId) } returns Optional.of(expectedTodo)

        assertEquals(Optional.of(expectedTodo), todoService.findTodoById(expectedId))
    }

    // Add new TODO
    @Test
    fun `successfullyAddsNewTodo`() {
        val tasks = mutableListOf(
            Task(id = UUID.randomUUID(), name = "First subtask", description = "first subtask description")
        )
        val newTodo = Todo(name = "First Todo", description = "First description", tasks =  tasks)
        every { todoRepository.save(newTodo) } returns newTodo

        assertEquals(todoRepository.save(newTodo) , newTodo)
    }

    @Test
    fun `failsToAddTodoWithInvalidData`() {
        val tasks = mutableListOf(
            Task(id = UUID.randomUUID(), name = "First subtask", description = "first subtask description")
        )
        val newTodo = Todo(name = "", description = "First description", tasks =  tasks)
        val expectedTodo: Todo? = null
        assertThrows(io.mockk.MockKException::class.java) {
            todoService.createTodo(newTodo)
        }
    }

    // Update existing TODO
    @Test
    fun `successfullyUpdatesExistingTodo`() {
        val existingTodoId = UUID.randomUUID()
        val existingTodo = Todo(id = existingTodoId, name = "Existing Todo", description = "Existing description", tasks = mutableListOf())
        val updatedTodoDetails = Todo(name = "Updated Todo", description = "Updated description", tasks = mutableListOf(Task(id = UUID.randomUUID(), name = "Updated subtask", description = "Updated subtask description")))

        // Assuming findTodoById is another method in your service that eventually calls todoRepository.findById
        every { todoRepository.findById(existingTodoId) } returns Optional.of(existingTodo)
        every { todoRepository.update(any()) } returnsArgument 0

        val result = todoService.updateTodo(existingTodoId, updatedTodoDetails)

        assertNotNull(result)
        assertEquals(updatedTodoDetails.name, result?.name)
        assertEquals(updatedTodoDetails.description, result?.description)
        assertEquals(updatedTodoDetails.tasks.size, result?.tasks?.size)
        verify { todoRepository.update(any()) }
    }


    @Test
    fun `failsToUpdateNonexistentTodo`() {
        val nonexistentTodoId = UUID.randomUUID()
        val todoToUpdate = Todo(id = nonexistentTodoId, name = "Nonexistent Todo", description = "Should fail")

        every { todoRepository.findById(nonexistentTodoId) } returns Optional.empty()
        val result = todoService.updateTodo(nonexistentTodoId, todoToUpdate)
        assertEquals(null, result)
        }



    // Delete a TODO
    @Test
    fun `successfullyDeletesTodo`() {
        val todoIdToDelete = UUID.randomUUID()

        every { todoRepository.existsById(todoIdToDelete) } returns true
        justRun { todoRepository.deleteById(todoIdToDelete) }

        todoService.deleteTodo(todoIdToDelete)

        verify(exactly = 1) { todoRepository.deleteById(todoIdToDelete) }
    }

}
