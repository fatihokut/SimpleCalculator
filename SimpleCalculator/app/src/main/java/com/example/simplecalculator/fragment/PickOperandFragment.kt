package com.example.simplecalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.simplecalculator.R
import com.example.simplecalculator.databinding.FragmentPickOperandBinding
import com.example.simplecalculator.viewmodel.CalculatorViewModel

class PickOperandFragment : Fragment() {

    private val model: CalculatorViewModel by activityViewModels()

    private var pickOperandBinding: FragmentPickOperandBinding? = null

    private var selectedOperation = ""
    private var operandOne = ""
    private var operandTwo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPickOperandBinding.inflate(inflater, container, false)
        pickOperandBinding = binding

        model.activeOperation.observe(viewLifecycleOwner) { newOperation ->
            selectedOperation = newOperation
            binding.equationOperation.text = toNotation(selectedOperation)
            if (selectedOperation == "Square Root") {
                binding.equationOperandLeft.visibility = View.INVISIBLE
            }
        }

        binding.addOperandBtn.setOnClickListener {
            // todo: move this block to updateEquation()
            if (binding.addParamsEdittext.text.toString().isEmpty()) {
                Toast.makeText(context, "Operands can not be empty!", Toast.LENGTH_SHORT).show()
            } else {
                if (selectedOperation != "Square Root") {
                    if (operandOne.isEmpty()) {
                        operandOne = binding.addParamsEdittext.text.toString()
                        binding.equationOperandLeft.text = operandOne
                        binding.addParamsEdittext.text.clear()
                    } else if (operandTwo.isEmpty()) {
                        operandTwo = binding.addParamsEdittext.text.toString()
                        binding.equationOperandRight.text = operandTwo
                        binding.addParamsEdittext.text.clear()
                        binding.addOperandBtn.isEnabled = false
                        binding.addOperandBtn.isClickable = false
                    }
                } else {
                    if (operandOne.isEmpty()) {
                        operandOne = binding.addParamsEdittext.text.toString()
                        binding.equationOperandRight.text = operandOne
                        binding.addParamsEdittext.text.clear()
                        binding.addOperandBtn.isEnabled = false
                        binding.addOperandBtn.isClickable = false
                    }
                }
            }
        }

        binding.showResultBtn.setOnClickListener {
            try {
                model.performOperation(operandOne, operandTwo)
                Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_show_result)
            } catch (e: IllegalArgumentException) {
                Toast.makeText(context, "IllegalArgumentException", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: ArithmeticException) {
                Toast.makeText(context, "ArithmeticException", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: IndexOutOfBoundsException) {
                Toast.makeText(context, "IndexOutOfBoundsException", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: UnsupportedOperationException) {
                Toast.makeText(context, "UnsupportedOperationException", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_pick_operand)
            }
        }

        binding.cancelBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.pick_operand_to_select_operation)
        }

        return binding.root
    }

    override fun onDestroyView() {
        pickOperandBinding = null
        super.onDestroyView()
    }

    private fun updateEquation() {
        // todo
    }

    private fun toNotation(op: String): String {
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