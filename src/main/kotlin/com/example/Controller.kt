package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/todos")

class Controller(val todoRepository: TodoRepository) {
    val todos = mutableMapOf<String?, TodoRequest>()

    @Post
    fun createNewTodo(@Body @Valid request: TodoRequest): TodoRequest {
        val todo = request.newTodo()

        todoRepository.save(todo)
        return request
    }

    @Get
    fun getTodo(): Collection<TodoRequest> {
        return todos.values
    }

/*
@Get("/{id}")
fun getTodoById(id: String): TodoRequest? {
    return todos[id]
}

@Put("/{id}")
fun updateTodo(@Body @Valid request: TodoRequest): TodoRequest?{
    todos[request.id] = request
    return todos[request.id]
}
*/
}