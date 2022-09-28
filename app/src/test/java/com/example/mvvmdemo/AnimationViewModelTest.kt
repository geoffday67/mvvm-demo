package com.example.mvvmdemo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AnimationViewModelTest {
    lateinit var viewModel: AnimationViewModel

    @BeforeEach
    fun beforeEach() {
        viewModel = AnimationViewModel()
    }

    @Test
    @DisplayName("Should toggle size")
    fun size() {
        viewModel.onSizeChange()
        assertTrue(viewModel.big)

        viewModel.onSizeChange()
        assertFalse(viewModel.big)
    }

    @Test
    @DisplayName("Should increment count")
    fun increment() {
        viewModel.onAnimationDone()
        assertEquals(1, viewModel.count)
    }
}