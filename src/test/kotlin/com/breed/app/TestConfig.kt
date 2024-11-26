package com.breed.app

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value

@Configuration
class TestConfig {

    @Value("\${spring.datasource.url}")
    private lateinit var dataSourceUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var dataSourceUsername: String

    @Value("\${spring.datasource.password}")
    private lateinit var dataSourcePassword: String

    @Bean
    fun flyway(): Flyway {
        val flyway = Flyway.configure()
            .dataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword)
            .cleanDisabled(false)
            .load()
        println("Flyway clean disabled: ${flyway.configuration.isCleanDisabled}")
        return flyway
    }
}
