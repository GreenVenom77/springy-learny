package com.example.demo.service

import com.example.demo.InsertMessageRequestDto
import com.example.demo.MessageResponseDto
import com.example.demo.UpdateMessageRequestDto
import com.example.demo.config.MessagesConfig
import com.example.demo.exception.MessageNotFoundException
import com.example.demo.repository.MessageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MessageService(
    private val db: MessageRepository,
    private val messagesConfig: MessagesConfig
) {
    fun findMessages(content: String?): List<MessageResponseDto> {
        return if(content != null) {
            db
                .findByContentContainsIgnoreCase(content)
                .map { it.toDto() }
        } else {
            db
                .findAll()
                .map { it.toDto() }
        }
    }

    fun findMessageById(id: UUID): MessageResponseDto = db.findByIdOrNull(id)?.toDto() ?: throw MessageNotFoundException(id)

    fun insertMessage(message: InsertMessageRequestDto): MessageResponseDto = db.save(message.toEntity()).toDto()

    fun updateMessage(message: UpdateMessageRequestDto): MessageResponseDto = db.save(message.toEntity()).toDto()

    fun deleteById(id: UUID): Unit = db.deleteById(id)
}