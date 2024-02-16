package com.example

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/todos")
class Controller {
    val todos = mutableMapOf<String?, Todo>()

    @Post
    fun createNewTodo(@Body request: Todo): Todo {
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
    fun updateTodo(@Body request: Todo): Todo?{
        todos[request.id] = request
        return todos[request.id]
    }

}