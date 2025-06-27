package com.todo.shared.domain.usecase

import com.todo.shared.data.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String) {
        repository.deleteTaskById(taskId)
    }
}