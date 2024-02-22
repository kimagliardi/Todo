package com.leanix.controller

import com.leanix.model.Task
import com.leanix.model.Todo
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test

@MicronautTest(environments = ["test"])
class TodoRequestsTest {
    fun sampleTodo(): Todo{
        val task1 = Task(
            name = "test Task",
            description = "This is a test task"
        )
        val task2 = Task(
            name = "test Task",
            description = "This is a test task"
        )

         return Todo(
            name = "Test Todo",
            description = "This is a test todo",
            tasks = mutableListOf(task1, task2))
    }


    // POST tests
    @Test
    fun `successfullyAddsNewTodo`(spec: RequestSpecification) {
        val todo = sampleTodo()
        spec
            .`when`()
            .contentType(ContentType.JSON)
            .body(todo)
            .post("/todos")
            .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
    }


    @Test
    fun `successfullyAddsNewTodoWithoutTasks`(spec: RequestSpecification) {
        var request = sampleTodo()
        request.tasks = emptyList<Task>().toMutableList()
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
    fun `failsToAddTodoWithWithoutName`(spec: RequestSpecification) {
        var request = sampleTodo()
        request.name = ""

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
    fun `SucessfullyGetAllTodos`(spec: RequestSpecification) {
        spec
            .`when`()
            .get("/todos")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json")
    }


    @Test
    fun `FailsToGetNonExistingTodo` (spec: RequestSpecification){
        val todoId = "5645508f-fd6e-4fe2-a860-88970c807249"
        spec
            .`when`()
            .get("/todos/$todoId")
            .then()
            .statusCode(404)

    }

}