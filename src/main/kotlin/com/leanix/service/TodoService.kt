package com.leanix.service

import com.leanix.model.Todo
import com.leanix.repository.TodoRepository
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.util.*


@Singleton
open class TodoService(private val todoRepository: TodoRepository) {

    open fun listTodos(): List<Todo> = todoRepository.findAll()

    @Transactional
    open fun createTodo(todo: Todo): Todo = todoRepository.save(todo)

    open fun findTodoById(id: UUID): Todo? = todoRepository.findById(id).orElse(null)

    @Transactional
    open fun updateTodo(id: UUID, updatedTodo: Todo): Todo? {
        val todo = findTodoById(id) ?: return null
        todo.name = updatedTodo.name
        todo.description = updatedTodo.description


        return todoRepository.update(todo)
    }

    @Transactional
    open fun deleteTodo(id: UUID) {
        todoRepository.deleteById(id)
    }
}