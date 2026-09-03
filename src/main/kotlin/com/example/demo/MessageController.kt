package com.example.demo

import com.example.demo.service.MessageService
import jakarta.validation.Valid
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/")
class MessageController(
    private val service: MessageService
) {
    @GetMapping
    fun listMessages(
        @RequestParam("content", required = false) content: String?
    ): List<MessageResponseDto> = service.findMessages(content)

    @GetMapping("paged")
    fun listMessagesPaged(
        @RequestParam("content", required = false) content: String?,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("pageSize", defaultValue = "10") pageSize: Int,
    ): PagedModel<MessageResponseDto> = service.findMessagesPaged(content, page, pageSize)

    @PostMapping
    fun postMessage(
        @Valid @RequestBody message: InsertMessageRequestDto
    ): ResponseEntity<MessageResponseDto> {
        val savedMessage = service.insertMessage(message)
        return ResponseEntity.created(URI("/${savedMessage.id}")).body(savedMessage)
    }

    @PutMapping
    fun putMessage(
        @Valid @RequestBody message: UpdateMessageRequestDto
    ): ResponseEntity<MessageResponseDto> {
        val updatedMessage = service.updateMessage(message)
        return ResponseEntity.ok(updatedMessage)
    }

    @GetMapping("/{id}")
    fun getMessage(@PathVariable id: UUID): ResponseEntity<MessageResponseDto> =
        service.findMessageById(id).toResponseEntity()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMessage(@PathVariable id: UUID) {
        service.findMessageById(id)
        service.deleteById(id)
    }

    private fun MessageResponseDto?.toResponseEntity(): ResponseEntity<MessageResponseDto> =
        // If the message is null (not found), set response code to 404
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}