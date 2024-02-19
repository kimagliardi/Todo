package com.leanix
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container


@MicronautTest
class TodoRequestTest {
    companion object {
        @Container
        val postgresContainer = PostgreSQLContainer<Nothing>("postgres:latest").apply {
            withDatabaseName("cdc")
            withUsername("postgres")
            withPassword("postgres")
        }

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            // Certifique-se de que o container esteja iniciado
            postgresContainer.start()

            // Configura o sistema ou variáveis de ambiente para utilizar a URL, usuário e senha do container
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
