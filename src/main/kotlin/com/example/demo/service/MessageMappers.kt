package com.example.demo.service

import com.example.demo.MessageResponseDto
import com.example.demo.MessageEntity
import com.example.demo.InsertMessageRequestDto
import com.example.demo.UpdateMessageRequestDto

fun MessageEntity.toDto(): MessageResponseDto {
    return MessageResponseDto(
        id = this.id,
        content = this.content,
        createdAt = this.createdAt,
    )
}

fun InsertMessageRequestDto.toEntity(): MessageEntity {
    return MessageEntity(
        content = this.content,
    )
}

fun UpdateMessageRequestDto.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        content = this.content,
    )
}