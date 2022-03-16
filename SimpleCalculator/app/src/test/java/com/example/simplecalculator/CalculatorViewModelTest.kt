package com.example.simplecalculator

import com.example.simplecalculator.viewmodel.CalculatorViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {

    private lateinit var model: CalculatorViewModel

    @Before
    override fun setUp() {
        model = CalculatorViewModel()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_twoNumbers() {
        model.performOperation("","")
        assertEquals(4, 2 + 2)
    }
}