package com.todo.repository

import com.todo.domain.Todo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface TodoRepository: JpaRepository<Todo, Long> {

}