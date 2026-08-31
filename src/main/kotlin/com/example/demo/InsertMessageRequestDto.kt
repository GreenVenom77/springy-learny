package com.example.demo

import org.hibernate.validator.constraints.Length

data class InsertMessageRequestDto(
    @field:Length(
        min = 5,
        max = 500,
        message = "Message length must be between 5 and 500 characters"
    )
    val content: String,
)
