package com.example.mvvmdemo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    private val scope = TestScope(UnconfinedTestDispatcher())

    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun beforeEach() {
        viewModel = MainViewModel(providedScope = scope)
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
}