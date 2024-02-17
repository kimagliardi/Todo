package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/todos")
class TodoController(private val todoService: TodoService) {

    @Get("/")
    fun listTodos(): List<Todo> = todoService.listTodos()

    @Post("/")
    fun createTodo(@Body todo: Todo): HttpResponse<Todo> {
        return HttpResponse.created(todoService.createTodo(todo))
    }

    @Get("/{id}")
    fun findTodoById(@PathVariable id: Long): HttpResponse<Todo> {
        val todo = todoService.findTodoById(id) ?: return HttpResponse.notFound()
        return HttpResponse.ok(todo)
    }

    @Put("/{id}")
    fun updateTodo(@PathVariable id: Long, @Body updatedTodo: Todo): HttpResponse<Todo> {
        println("todo id {id}")
        val todo = todoService.updateTodo(id, updatedTodo) ?: return HttpResponse.notFound()
        return HttpResponse.ok(todo)
    }

    @Delete("/{id}")
    fun deleteTodo(@PathVariable id: Long): HttpResponse<Unit> {
        todoService.deleteTodo(id)
        return HttpResponse.noContent()
    }
}