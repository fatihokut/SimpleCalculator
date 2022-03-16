package com.example.simplecalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simplecalculator.viewmodel.CalculatorViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CalculatorViewModelTest {

    private lateinit var model: CalculatorViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        model = CalculatorViewModel()
    }

    @Test
    fun `add two numbers`() {
        model.setActiveOperation("Addition")
        model.performOperation("2", "2")
        assertEquals("4.00", model.result.value)
    }

    @Test
    fun `subtract from a number`() {
        model.setActiveOperation("Subtraction")
        model.performOperation("5", "1")
        assertEquals("4.00", model.result.value)
    }

    @Test
    fun `multiply two numbers`() {
        model.setActiveOperation("Multiply")
        model.performOperation("5", "4")
        assertEquals("20.00", model.result.value)
    }

    @Test
    fun `divide a number`() {
        model.setActiveOperation("Divide")
        model.performOperation("10", "2")
        assertEquals("5.00", model.result.value)
    }

    @Test
    fun `divide to zero returns error`() {
        assertThrows(ArithmeticException::class.java) {
            model.setActiveOperation("Divide")
            model.performOperation("10", "0")
        }
    }

    @Test
    fun `square root of a number`() {
        model.setActiveOperation("Square Root")
        model.performOperation("25")
        assertEquals("5.00", model.result.value)
    }

    @Test
    fun `square root of a negative number returns error`() {
        assertThrows(IndexOutOfBoundsException::class.java) {
            model.setActiveOperation("Square Root")
            model.performOperation("-25")
        }
    }

    @Test
    fun `unsupported operation input returns error`() {
        assertThrows(UnsupportedOperationException::class.java) {
            model.setActiveOperation("Random Operation")
            model.performOperation("3", "5")
        }
    }
}