package com.leanix.controller

import com.leanix.model.Task
import com.leanix.model.Todo
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test

@MicronautTest(environments = ["test"])
class RequestsWithoutMocking {
fun createSampleTodo(spec: RequestSpecification){
    val task1 = Task(
        name = "test Task",
        description = "This is a test task"
    )
    val task2 = Task(
        name = "test Task",
        description = "This is a test task"
    )
    val request = Todo(
        name = "Test Todo",
        description = "This is a test todo",
        tasks = mutableListOf(task1, task2)

    )
    spec
        .`when`()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/todos")
        .then()
        .statusCode(201)
        .header("Content-Type", "application/json")


}

    // POST tests
    @Test
    fun `SUCCESS - should return 201 (Created) on POST Todo with subtasks`(spec: RequestSpecification) {

        val task1 = Task(
            name = "test Task",
            description = "This is a test task"
        )
        val task2 = Task(
            name = "test Task",
            description = "This is a test task"
        )
        val request = Todo(
            name = "Test Todo",
            description = "This is a test todo",
            tasks = mutableListOf(task1, task2)

        )
        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/todos")
            .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
    }


    @Test
    fun `SUCCESS - should return 201 (Created) on POST Todo without subtasks`(spec: RequestSpecification) {
        val request = Todo(
            name = "Test Todo",
            description = "This is a test todo"
        )

        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/todos")
            .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
    }

    @Test
    fun `FAIL - should return 400 (Bad Request) on POST Todo with a blank name`(spec: RequestSpecification) {
        val request = Todo(
            name = "",
            description = "This is a test todo"
        )

        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/todos")
            .then()
            .statusCode(400)
            .header("Content-Type", "application/json")
    }

    @Test
    fun `FAIL - should return 400 (Bad Request) on POST Todo with a task blank name`(spec: RequestSpecification) {
        val request = Todo(
            name = "",
            description = "This is a test todo"
        )

        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/todos")
            .then()
            .statusCode(400)
            .header("Content-Type", "application/json")
    }


    // GET tests
    @Test
    fun `SUCCESS - should return 200 on GET all todos`(spec: RequestSpecification) {
        spec
            .`when`()
            .get("/todos")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json")
    }


    @Test
    fun `FAIL - should return 404 on GET todo by ID with an that does not exists in DB` (spec: RequestSpecification){
        val todoId = "5645508f-fd6e-4fe2-a860-88970c807249"
        spec
            .`when`()
            .get("/todos/$todoId")
            .then()
            .statusCode(404)

    }

    @Test
    fun `SUCCESS - should return 200 on GET todo by ID` (spec: RequestSpecification){
        val todoId = "e54473f4-6075-402c-bda0-944a95747982"
        spec
            .`when`()
            .get("/todos/$todoId")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json")

    }

    // Reminder: Is there a need for an empty list test case?

    // PUT
}