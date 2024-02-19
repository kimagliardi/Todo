package com.leanix
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@MicronautTest
@Testcontainers
class TodoRequestTest {

    companion object {
        @Container
        val postgresContainer = PostgreSQLContainer<Nothing>("postgres:13.3").apply {
            withDatabaseName("cdc")
            withUsername("postgres")
            withPassword("postgres")
        }

        @JvmStatic
        @BeforeAll
        fun setUp() {
            // Inicia o container
            postgresContainer.start()

            // Configura as propriedades do sistema com as informações do container
            System.setProperty("datasources.default.url", postgresContainer.jdbcUrl)
            System.setProperty("datasources.default.username", postgresContainer.username)
            System.setProperty("datasources.default.password", postgresContainer.password)
        }
    }

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }
}
