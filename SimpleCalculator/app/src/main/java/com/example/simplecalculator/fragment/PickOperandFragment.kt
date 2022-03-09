package com.example.simplecalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.simplecalculator.R
import com.example.simplecalculator.viewmodel.CalculatorViewModel
import kotlin.math.sqrt

class PickOperandFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    private var selectedOperation = ""
    private var operandOne = ""
    private var operandTwo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pick_operand, container, false)

        val safeArgs: PickOperandFragmentArgs by navArgs()
        selectedOperation = safeArgs.selectedOperation.toString()

        val equationOperation: TextView = view.findViewById(R.id.equation_operation)
        equationOperation.text = notation(selectedOperation)
        val equationOperandLeft: TextView = view.findViewById(R.id.equation_operand_left)
        val equationOperandRight: TextView = view.findViewById(R.id.equation_operand_right)

        if (selectedOperation == "Square Root") {
            equationOperandLeft.visibility = View.INVISIBLE
        }

        // todo: limit input
        val addParamsEditText: EditText = view.findViewById(R.id.add_params_edittext)
        //onTextChanged

        val addOperandButton: Button = view.findViewById(R.id.add_operand_btn)
        addOperandButton.setOnClickListener {
            if (selectedOperation != "Square Root") {
                if (operandOne == "") {
                    operandOne = addParamsEditText.text.toString()
                    equationOperandLeft.text = operandOne
                    addParamsEditText.text.clear()
                } else if (operandTwo == "") {
                    operandTwo = addParamsEditText.text.toString()
                    equationOperandRight.text = operandTwo
                    addParamsEditText.text.clear()
                    addOperandButton.isEnabled = false
                    addOperandButton.isClickable = false
                }
            } else {
                if (operandOne == "") {
                    operandOne = addParamsEditText.text.toString()
                    equationOperandRight.text = operandOne
                    addParamsEditText.text.clear()
                    addOperandButton.isEnabled = false
                    addOperandButton.isClickable = false
                }
            }
        }

        view.findViewById<Button>(R.id.show_result_btn).setOnClickListener {
            model.performOperation(selectedOperation, operandOne, operandTwo)
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_show_result)
        }

        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.pick_operand_to_select_operation)
        }

        return view
    }

    private fun notation(op: String) : String {
        return when (op) {
            "Addition" -> "+"
            "Subtraction" -> "-"
            "Multiply" -> "*"
            "Divide" -> "/"
            "Square Root" -> "√"
            else -> "null"
        }

    }
}