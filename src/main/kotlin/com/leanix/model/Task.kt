package com.leanix.model

import com.fasterxml.jackson.annotation.JsonBackReference
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Serdeable
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @field:NotBlank var name: String,
    var description: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    var todo: Todo? = null
    )