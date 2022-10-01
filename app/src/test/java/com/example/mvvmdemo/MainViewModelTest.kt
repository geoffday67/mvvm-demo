package com.example.mvvmdemo

import androidx.navigation.NavController
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
class MainViewModelTest {
    private val scope = TestScope(UnconfinedTestDispatcher())

    @MockK
    lateinit var mockNavController: NavController

    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun beforeEach() {
        viewModel = MainViewModel(
            navController = mockNavController,
            providedScope = scope
        )
    }

    @Test
    @DisplayName("Should set the name twice")
    fun delay() = scope.runTest {
        viewModel.delay()
        assertEquals("... wait for it", viewModel.name)
        scope.advanceTimeBy(2000)
        scope.runCurrent()
        assertEquals("Fire!!", viewModel.name)
    }

    @Test
    @DisplayName("Should go to next screen")
    fun next() {
        viewModel.handleNext()

        verify {
            mockNavController.navigate("great")
        }
    }
}