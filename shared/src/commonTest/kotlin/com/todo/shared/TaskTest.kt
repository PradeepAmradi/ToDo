package com.todo.shared

import com.todo.shared.data.model.Priority
import com.todo.shared.data.model.Task
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TaskTest {
    @Test
    fun testTaskCreation() {
        val task = Task(
            id = "test-id",
            title = "Test Task",
            description = "Test Description",
            priority = Priority.HIGH,
            category = "Work"
        )
        
        assertEquals("test-id", task.id)
        assertEquals("Test Task", task.title)
        assertEquals("Test Description", task.description)
        assertEquals(Priority.HIGH, task.priority)
        assertEquals("Work", task.category)
        assertFalse(task.isCompleted)
    }
    
    @Test
    fun testTaskCompletion() {
        val task = Task(
            id = "test-id",
            title = "Test Task"
        )
        
        assertFalse(task.isCompleted)
        
        val completedTask = task.copy(isCompleted = true)
        assertTrue(completedTask.isCompleted)
    }
    
    @Test
    fun testPriorityLevels() {
        val priorities = Priority.values()
        assertEquals(3, priorities.size)
        assertTrue(priorities.contains(Priority.LOW))
        assertTrue(priorities.contains(Priority.MEDIUM))
        assertTrue(priorities.contains(Priority.HIGH))
    }
}