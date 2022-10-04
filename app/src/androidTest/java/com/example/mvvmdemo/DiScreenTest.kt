package com.example.mvvmdemo

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.mvvmdemo.di.DiScreenPart1
import com.example.mvvmdemo.di.DiScreenPart2
import com.example.mvvmdemo.di.DiViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

internal class DiScreenTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel: DiViewModel = mockk()

    @Test
    fun part_1_should_show_text() {
        val testText = "Geoff is great!"
        every { mockViewModel.message } returns testText

        composeTestRule.setContent {
            DiScreenPart1(mockViewModel)
        }

        composeTestRule.onNodeWithTag("id").assertTextEquals(testText)
    }

    @Test
    fun part_2_should_show_text() {
        val testText = "Geoff is lovely!"
        every { mockViewModel.message } returns testText

        composeTestRule.setContent {
            DiScreenPart2(mockViewModel)
        }

        composeTestRule.onNodeWithTag("id").assertTextEquals(testText)
    }
}
