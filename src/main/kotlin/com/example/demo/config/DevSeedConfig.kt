package com.example.demo.config

import com.example.demo.MessageEntity
import com.example.demo.repository.MessageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class DevSeedConfig {
    @Bean
    fun seedDatabase(messageRepository: MessageRepository) = CommandLineRunner {
        if (messageRepository.count() == 0L) {
            val seedMessages = (1..20).map { i ->
                MessageEntity(content = "Seed message #$i")
            }
            messageRepository.saveAll(seedMessages)
        }
    }
}