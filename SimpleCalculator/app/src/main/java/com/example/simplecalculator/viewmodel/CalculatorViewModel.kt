package com.example.simplecalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

    private var _result = MutableLiveData<String>()
    var result: LiveData<String> = _result

    private var _activeOperation = MutableLiveData<String>()
    var activeOperation: LiveData<String> = _activeOperation

    fun performOperation(
        operandOne: String,
        operandTwo: String = ""
    ) {
        val inputType: InputType =
            if (operandOne.toIntOrNull() != null && operandTwo.toIntOrNull() != null)
                InputType.INTEGER
            else
                InputType.DOUBLE

        when (activeOperation.value) {
            "Addition" -> {
                _result.value = "%.2f".format((operandOne.toDouble() + operandTwo.toDouble()))
            }
            "Subtraction" -> {
                _result.value = "%.2f".format((operandOne.toDouble() - operandTwo.toDouble()))
            }
            "Multiply" -> {
                _result.value = "%.2f".format((operandOne.toDouble() * operandTwo.toDouble()))
            }
            "Divide" -> {
                if (operandTwo.toDouble().equals(0.0)) throw ArithmeticException()
                _result.value = "%.2f".format((operandOne.toDouble() / operandTwo.toDouble()))
            }
            "Square Root" -> {
                if (operandOne.toDouble() < 0) throw IndexOutOfBoundsException()
                _result.value = "%.2f".format((sqrt(operandOne.toDouble())))
            }
            else ->
                throw UnsupportedOperationException()
        }
    }

    fun setActiveOperation(operation: String) {
        _activeOperation.value = operation
    }

    enum class InputType {
        INTEGER,
        DOUBLE
    }

}