package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable
import java.util.UUID;

@Introspected
@Serdeable.Serializable
@Deserializable
data class Todo (
        val id: String? = UUID.randomUUID().toString(),
        var name: String,
        var description: String?,
        var tasks: MutableList<Task>
)