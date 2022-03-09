package com.example.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

//    var selectedOperation = MutableLiveData<String>()
    var result = MutableLiveData<String>()

    // todo: zero division
    // todo: negative sqrt
    // todo: invalid inputs, empty...
    // todo: crash = java.lang.NumberFormatException: empty String
    // todo: round the double val round(200.3456, 3);
    fun performOperation(operation: String,
                         operandOne: String,
                         operandTwo: String = "") {
//        selectedOperation.value = operation

        result.value = when (operation) {
            "Addition" ->
                (operandOne.toDouble() + operandTwo.toDouble()).toString()
            "Subtraction" ->
                (operandOne.toDouble() - operandTwo.toDouble()).toString()
            "Multiply" ->
                (operandOne.toDouble() * operandTwo.toDouble()).toString()
            "Divide" ->
                (operandOne.toDouble() / operandTwo.toDouble()).toString()
            "Square Root" ->
                (sqrt(operandOne.toDouble())).toString()
            else ->
                "Undefined Operation"
        }
    }
}