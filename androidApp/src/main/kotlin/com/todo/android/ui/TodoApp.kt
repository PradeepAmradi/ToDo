package com.todo.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.todo.android.ui.component.TaskItem
import com.todo.android.ui.component.AddTaskDialog
import com.todo.android.ui.viewmodel.TodoViewModel
import com.todo.shared.data.model.TaskFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    val viewModel = remember { TodoViewModel() }
    val tasks by viewModel.tasks.collectAsState()
    val categories by viewModel.categories.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf(TaskFilter.ALL) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("ToDo App") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Filter Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TaskFilter.values().forEach { filter ->
                    FilterChip(
                        onClick = { 
                            selectedFilter = filter
                            viewModel.filterTasks(filter)
                        },
                        label = { Text(filter.name) },
                        selected = selectedFilter == filter
                    )
                }
            }

            // Tasks List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onToggleComplete = { viewModel.toggleTaskCompletion(task) },
                        onDelete = { viewModel.deleteTask(task.id) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
            categories = categories,
            onDismiss = { showAddDialog = false },
            onAddTask = { title, description, category, priority, dueDate ->
                viewModel.addTask(title, description, category, priority, dueDate)
                showAddDialog = false
            }
        )
    }
}