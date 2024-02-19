package com.leanix.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    var tasks: MutableList<Task> = mutableListOf()
) {
    fun replaceTasks(newTasks: List<Task>) {
        // Clear the existing tasks
        tasks.clear()
        tasks.addAll(newTasks)

        // Set the parent todo for each task in the new list
        newTasks.forEach { it.todo = this }
    }
}