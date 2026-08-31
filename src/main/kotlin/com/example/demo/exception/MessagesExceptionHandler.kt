package com.example.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MessagesExceptionHandler {

    @ExceptionHandler(MessageNotFoundException::class)
    fun onQuoteNotFound(e: MessageNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponseException(HttpStatus.NOT_FOUND, e)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidationFailed(e: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val map = mutableMapOf<String, Any>()
        e.bindingResult.fieldErrors.forEach { error ->
            map[error.field] = error.defaultMessage ?: "Validation failed"
        }

        return ResponseEntity
            .badRequest()
            .body(map)
    }
}