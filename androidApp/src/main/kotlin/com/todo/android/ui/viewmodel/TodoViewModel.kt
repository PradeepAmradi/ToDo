package com.todo.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.shared.data.model.Priority
import com.todo.shared.data.model.Task
import com.todo.shared.data.model.TaskFilter
import com.todo.shared.data.repository.TaskRepository
import com.todo.shared.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class TodoViewModel : ViewModel() {
    private val repository = TaskRepository()
    
    private val getTasksUseCase = GetTasksUseCase(repository)
    private val addTaskUseCase = AddTaskUseCase(repository)
    private val updateTaskUseCase = UpdateTaskUseCase(repository)
    private val deleteTaskUseCase = DeleteTaskUseCase(repository)
    
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()
    
    init {
        filterTasks(TaskFilter.ALL)
        loadCategories()
        // Add some sample data for demonstration
        addSampleTasks()
    }
    
    private fun addSampleTasks() {
        viewModelScope.launch {
            addTaskUseCase("Complete project setup", "Set up the basic structure for the ToDo app", "Work", Priority.HIGH)
            addTaskUseCase("Buy groceries", "Milk, Bread, Eggs", "Personal", Priority.MEDIUM)
            addTaskUseCase("Exercise", "30 minutes workout", "Health", Priority.LOW)
        }
    }
    
    fun filterTasks(filter: TaskFilter) {
        viewModelScope.launch {
            getTasksUseCase(filter).collect { taskList ->
                _tasks.value = taskList
            }
        }
    }
    
    private fun loadCategories() {
        viewModelScope.launch {
            repository.getCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }
    
    fun addTask(
        title: String,
        description: String,
        category: String,
        priority: Priority,
        dueDate: Instant?
    ) {
        viewModelScope.launch {
            addTaskUseCase(title, description, category, priority, dueDate)
        }
    }
    
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(
                isCompleted = !task.isCompleted,
                updatedAt = Clock.System.now()
            ))
        }
    }
    
    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }
}