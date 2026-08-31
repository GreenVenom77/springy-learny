package com.example.demo

import org.hibernate.validator.constraints.Length
import java.util.UUID

data class UpdateMessageRequestDto(
    val id: UUID,
    @field:Length(
        min = 5,
        max = 500,
        message = "Message length must be between 5 and 500 characters"
    )
    val content: String,
)