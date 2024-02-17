package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable.Serializable
@Serdeable.Deserializable
data class Task (
        @field:NotBlank val name: String,
        var description: String?,
)