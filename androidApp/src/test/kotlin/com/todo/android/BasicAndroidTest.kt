package com.todo.android

import com.todo.shared.data.model.Priority
import org.junit.Test
import org.junit.Assert.*

class BasicAndroidTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun priority_enumeration_works() {
        assertEquals(3, Priority.values().size)
        assertTrue(Priority.values().contains(Priority.HIGH))
    }
}