package com.todo.service

import com.todo.domain.Todo
import com.todo.repository.TodoRepository
import jakarta.inject.Singleton
import jakarta.transaction.Transactional


@Singleton
open class TodoService(private val todoRepository: TodoRepository) {

    open fun listTodos(): List<Todo> = todoRepository.findAll()

    @Transactional
    open fun createTodo(todo: Todo): Todo = todoRepository.save(todo)

    open fun findTodoById(id: Long): Todo? = todoRepository.findById(id).orElse(null)

    @Transactional
    open fun updateTodo(id: Long, updatedTodo: Todo): Todo? {
        val todo = findTodoById(id) ?: return null
        todo.name = updatedTodo.name
        todo.description = updatedTodo.description


        return todoRepository.update(todo)
    }

    @Transactional
    open fun deleteTodo(id: Long) {
        todoRepository.deleteById(id)
    }
}