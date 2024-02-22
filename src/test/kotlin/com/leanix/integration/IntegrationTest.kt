package com.leanix.integration
import com.leanix.model.Todo
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.mapper.ObjectMapperType
import jakarta.inject.Inject
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest(environments = ["test"])
class IntegrationTest {
    @Inject
    lateinit var server: EmbeddedServer

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:${server.port}"
    }
    @Test
    fun testCreateReadUpdateDeleteTodo() {
        // Create a new Todo
        val newTodo = Todo(name = "Test Todo", description = "This is a test todo")
        var createdTodo: Todo = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(newTodo, ObjectMapperType.JACKSON_2)
            .`when`()
            .post("/todos")
            .then()
            .statusCode(201)
            .extract()
            .`as`(Todo::class.java, ObjectMapperType.JACKSON_2)

        // Read the created Todo
        RestAssured.given()
            .`when`()
            .get("/todos/${createdTodo.id}")
            .then()
            .statusCode(200)
            .body("name", equalTo(createdTodo.name))
            .body("description", equalTo(createdTodo.description))

        // Update the Todo


        // Update the Todo
        val updatedTodo = Todo(name="updated todo", description = "updated description")
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(updatedTodo, ObjectMapperType.JACKSON_2)
            .`when`()
            .put("/todos/${createdTodo.id}")
            .then()
            .statusCode(200)
            .body("name", equalTo(updatedTodo.name))
            .body("description", equalTo(updatedTodo.description))

        // Delete the Todo
        RestAssured.given()
            .`when`()
            .delete("/todos/${createdTodo.id}")
            .then()
            .statusCode(204)

        // Verify the Todo is deleted
        RestAssured.given()
            .`when`()
            .get("/todos/${createdTodo.id}")
            .then()
            .statusCode(404)
    }
}
