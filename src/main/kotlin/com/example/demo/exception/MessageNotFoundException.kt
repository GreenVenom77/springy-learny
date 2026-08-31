package com.example.demo.exception

import java.util.UUID

class MessageNotFoundException(
    id: UUID
): RuntimeException(
    "Message not found: $id"
)