package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable.Serializable
@Deserializable
data class TodoRequest (
        @field:NotBlank val name: String,
        var description: String,
        var tasks: MutableList<Task>) {

        fun newTodo(): Todo {
                return Todo(name, description)
        }
}