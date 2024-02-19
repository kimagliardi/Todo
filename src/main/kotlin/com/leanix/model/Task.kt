package com.leanix.model

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.util.*

@Entity
@Serdeable
@Serdeable.Serializable
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    var name: String,
    var description: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    var todo: Todo? = null
    )