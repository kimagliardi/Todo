package com.leanix.model

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*


@Entity
@Serdeable
@Serdeable.Serializable
@Table(name = "todo")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @field:NotBlank var name: String,
    var description: String? = null,

    //@OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    var tasks: List<Task> = mutableListOf()
)