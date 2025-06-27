package com.todo.shared.data.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Task(
    val id: String,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val category: String = "",
    val dueDate: Instant? = null,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now()
)

enum class Priority {
    LOW, MEDIUM, HIGH
}