package com.example.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "messages")
data class MessagesConfig(
    val search: SearchConfig,
    val validation: ValidationConfig
) {
    data class SearchConfig(
        val ignoreCase: Boolean = true,
        val minLength: Int = 0
    )

    data class ValidationConfig(
        val minContentLength: Int = 0,
        val maxContentLength: Int = 500,
        val requireAuthor: Boolean = true
    )
}
