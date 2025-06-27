package com.todo.shared.domain.usecase

import com.todo.shared.data.model.Task
import com.todo.shared.data.repository.TaskRepository

class UpdateTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.updateTask(task)
    }
}