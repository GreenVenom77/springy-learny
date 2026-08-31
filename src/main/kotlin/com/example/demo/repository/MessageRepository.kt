package com.example.demo.repository

import com.example.demo.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface MessageRepository: JpaRepository<MessageEntity, UUID> {
    fun findByContentContainsIgnoreCase(query: String): List<MessageEntity>

    @Query("""
        SELECT m
        FROM MessageEntity m
        WHERE m.content LIKE '%' || :query || '%' 
    """)
    fun searchMessages(query: String): List<MessageEntity>
}