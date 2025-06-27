package com.todo.shared.domain.usecase

import com.todo.shared.data.model.Task
import com.todo.shared.data.model.TaskFilter
import com.todo.shared.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(filter: TaskFilter = TaskFilter.ALL): Flow<List<Task>> {
        return repository.getTasks(filter)
    }
}