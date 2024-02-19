package com.leanix.repository

import com.leanix.model.Todo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface TodoRepository: JpaRepository<Todo, UUID> {

}