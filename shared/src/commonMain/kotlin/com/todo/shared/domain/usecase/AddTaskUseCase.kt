package com.todo.shared.domain.usecase

import com.todo.shared.data.model.Priority
import com.todo.shared.data.model.Task
import com.todo.shared.data.repository.TaskRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.random.Random

class AddTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(
        title: String,
        description: String = "",
        category: String = "",
        priority: Priority = Priority.MEDIUM,
        dueDate: Instant? = null
    ) {
        val task = Task(
            id = generateId(),
            title = title,
            description = description,
            category = category,
            priority = priority,
            dueDate = dueDate
        )
        repository.insertTask(task)
    }
    
    private fun generateId(): String {
        return "task_${Random.nextInt(10000, 99999)}_${Clock.System.now().toEpochMilliseconds()}"
    }
}