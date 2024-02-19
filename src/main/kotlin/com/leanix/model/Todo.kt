package com.leanix.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*


@Entity
@Serdeable
@Table(name = "todo")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @field:NotBlank var name: String,
    var description: String? = null,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    @JsonManagedReference
    var tasks: MutableList<Task> = mutableListOf()
)