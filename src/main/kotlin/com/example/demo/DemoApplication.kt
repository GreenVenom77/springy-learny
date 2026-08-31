package com.example.demo

import com.example.demo.config.MessagesConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MessagesConfig::class)
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
