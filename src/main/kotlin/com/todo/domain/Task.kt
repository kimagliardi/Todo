package com.todo.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Entity
@Serdeable
@Serdeable.Serializable
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    var name: String,
    var description: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_id")
    var todo: Todo? = null
)