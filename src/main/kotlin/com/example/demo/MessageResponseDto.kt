package com.example.demo

import java.time.Instant
import java.util.UUID

data class MessageResponseDto(
    val id: UUID,
    val content: String,
    val createdAt: Instant,
)
