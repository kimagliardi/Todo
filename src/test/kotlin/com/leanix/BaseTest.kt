package com.leanix



import org.junit.jupiter.api.BeforeAll
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
open class BaseTest {

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
}
