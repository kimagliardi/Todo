package com.leanix.controller

import com.leanix.model.Todo
import com.leanix.service.TodoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.serde.ObjectMapper
import io.micronaut.validation.Validated
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/todos")
@Validated
class TodoController(private val todoService: TodoService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Get("/")
    fun listTodos(): List<Todo> = todoService.listAll()

    @Post("/")
    fun createTodo(@Valid @Body todo: Todo): HttpResponse<Todo> {
        val startTime = System.currentTimeMillis()

        // Assuming todoService.createTodo returns a Todo object with an ID
        val createdTodo = todoService.createTodo(todo)

        val endTime = System.currentTimeMillis()
        val timeTaken = endTime - startTime


        logger.info("Created new Todo with ID: ${createdTodo.id}, Response Code: 201, Time Taken: ${timeTaken}ms")

        return HttpResponse.created(createdTodo)
    }

    @Transactional
    @Get("/{id}")
    fun findTodoById(@PathVariable id: UUID,): HttpResponse<Todo> {
        val todo = todoService.findTodoById(id) ?: return HttpResponse.notFound()

        var objectMapper = ObjectMapper.getDefault()
        println(objectMapper.writeValueAsString(todo))
        return HttpResponse.ok(todo)
    }


    @Put("/{id}")
    fun updateTodo(@PathVariable id: UUID, @Body updatedTodo: Todo): HttpResponse<Todo> {
        val todo = todoService.updateTodo(id, updatedTodo) ?: return HttpResponse.notFound()
        return HttpResponse.ok(todo)
    }

    @Delete("/{id}")
    fun deleteTodo(@PathVariable id: UUID): HttpResponse<Unit> {
        todoService.deleteTodo(id)
        return HttpResponse.noContent()
    }
}