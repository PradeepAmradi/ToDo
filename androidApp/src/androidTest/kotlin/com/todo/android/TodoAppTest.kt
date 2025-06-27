package com.todo.android

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.todo.android.ui.TodoApp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun todoApp_displaysCorrectTitle() {
        composeTestRule.setContent {
            TodoApp()
        }

        composeTestRule
            .onNodeWithText("ToDo App")
            .assertExists()
    }
}