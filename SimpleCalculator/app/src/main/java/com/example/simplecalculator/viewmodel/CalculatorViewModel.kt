package com.example.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.UnsupportedOperationException
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

    var result = MutableLiveData<String>()
    var activeOperation = MutableLiveData<String>()

    fun performOperation(
        operandOne: String,
        operandTwo: String = ""
    ) {

        when (activeOperation.value) {
            "Addition" -> {
                result.value = "%.2f".format((operandOne.toDouble() + operandTwo.toDouble()))
            }
            "Subtraction" -> {
                result.value = "%.2f".format((operandOne.toDouble() - operandTwo.toDouble()))
            }
            "Multiply" -> {
                result.value = "%.2f".format((operandOne.toDouble() * operandTwo.toDouble()))
            }
            "Divide" -> {
                if (operandTwo.toDouble().equals(0.0)) throw ArithmeticException()
                result.value = "%.2f".format((operandOne.toDouble() / operandTwo.toDouble()))
            }
            "Square Root" -> {
                if (operandOne.toDouble() < 0) throw IndexOutOfBoundsException()
                result.value = "%.2f".format((sqrt(operandOne.toDouble())))
            }
            else ->
                throw UnsupportedOperationException()
        }
    }

    fun setActiveOperation(operation: String) {
        activeOperation.value = operation
    }
}