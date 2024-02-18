package com.todo.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Serdeable
@Serdeable.Serializable
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @field:NotBlank var name: String,
    var description: String? = null,
    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var tasks: List<Task> = mutableListOf()
)