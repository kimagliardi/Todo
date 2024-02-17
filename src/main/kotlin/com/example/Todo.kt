package com.example

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Todo (
    var name: String,
    var description: String
){
    @Id
    @GeneratedValue
    var id: Long? = null

    val createdAt: LocalDateTime = LocalDateTime.now()
}
