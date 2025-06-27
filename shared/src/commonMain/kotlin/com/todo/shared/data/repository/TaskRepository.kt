package com.todo.shared.data.repository

import com.todo.shared.data.model.Task
import com.todo.shared.data.model.TaskFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    
    fun getTasks(filter: TaskFilter): Flow<List<Task>> {
        return _tasks.map { tasks ->
            when (filter) {
                TaskFilter.ALL -> tasks
                TaskFilter.PENDING -> tasks.filter { !it.isCompleted }
                TaskFilter.COMPLETED -> tasks.filter { it.isCompleted }
                TaskFilter.TODAY -> {
                    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                    tasks.filter { task ->
                        task.dueDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.date == today
                    }
                }
                TaskFilter.UPCOMING -> {
                    val now = Clock.System.now()
                    tasks.filter { task ->
                        task.dueDate != null && task.dueDate > now && !task.isCompleted
                    }
                }
            }
        }
    }

    fun getTasksByCategory(category: String): Flow<List<Task>> {
        return _tasks.map { tasks ->
            tasks.filter { it.category == category }
        }
    }

    suspend fun getTaskById(id: String): Task? {
        return _tasks.value.find { it.id == id }
    }

    suspend fun insertTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.add(task)
        _tasks.value = currentTasks
    }

    suspend fun updateTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        val index = currentTasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            currentTasks[index] = task.copy(updatedAt = Clock.System.now())
            _tasks.value = currentTasks
        }
    }

    suspend fun deleteTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.removeAll { it.id == task.id }
        _tasks.value = currentTasks
    }

    suspend fun deleteTaskById(id: String) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.removeAll { it.id == id }
        _tasks.value = currentTasks
    }

    fun getCategories(): Flow<List<String>> {
        return _tasks.map { tasks ->
            tasks.mapNotNull { if (it.category.isNotEmpty()) it.category else null }
                .distinct()
                .sorted()
        }
    }
}