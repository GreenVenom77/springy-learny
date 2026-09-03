package com.example.demo.repository

import com.example.demo.MessageEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface MessageRepository: JpaRepository<MessageEntity, UUID> {
    fun findAllByOrderByCreatedAtDesc(pageable: Pageable): Page<MessageEntity>

    fun findByContentContainsIgnoreCase(query: String): List<MessageEntity>

    fun findByContentContainsIgnoreCase(query: String, pageable: Pageable): Page<MessageEntity>

    @Query("""
        SELECT m
        FROM MessageEntity m
        WHERE m.content LIKE '%' || :query || '%' 
    """)
    fun searchMessages(query: String): List<MessageEntity>
}