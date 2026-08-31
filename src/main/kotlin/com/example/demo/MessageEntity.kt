package com.example.demo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "messages")
class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID(0,0),
    @Column(nullable = false)
    var content: String = "",
    @CreationTimestamp
    var createdAt: Instant = Instant.now(),
)
