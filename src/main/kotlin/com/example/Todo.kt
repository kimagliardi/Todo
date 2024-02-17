package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable
import jakarta.validation.constraints.NotBlank
import java.util.*

@Introspected
@Serdeable.Serializable
@Deserializable
data class Todo (
        @field:NotBlank val id: String? = UUID.randomUUID().toString(),
        @field:NotBlank val name: String,
        var description: String,
        var tasks: MutableList<Task>

)