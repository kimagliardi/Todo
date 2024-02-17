package com.example

import io.micronaut.http.annotation.*
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/todos")

class Controller {
    val todos = mutableMapOf<String?, Todo>()

    @Post
    fun createNewTodo(@Body @Valid request: Todo): Todo {
        todos[request.id] = request
        return request
    }

    @Get
    fun getTodo(): Collection<Todo> {
        return todos.values
    }
    @Get("/{id}")
    fun getTodoById(id: String): Todo? {
        return todos[id]
    }

    @Put("/{id}")
    fun updateTodo(@Body @Valid request: Todo): Todo?{
        todos[request.id] = request
        return todos[request.id]
    }

}