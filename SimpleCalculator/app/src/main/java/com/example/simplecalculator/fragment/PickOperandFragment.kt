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
            binding.equationOperation.text = getNotation(selectedOperation)
            if (selectedOperation == "Square Root")
                binding.equationOperandLeft.visibility = View.INVISIBLE
        }

        binding.addOperandBtn.setOnClickListener {
            if (binding.addParamsEdittext.text.toString().isEmpty()) Toast.makeText(
                context,
                "Operands can not be empty",
                Toast.LENGTH_SHORT
            ).show() else addOperand()
        }

        binding.showResultBtn.setOnClickListener {
            try {
                model.performOperation(operandOne, operandTwo)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.pick_operand_to_show_result)
            } catch (e: IllegalArgumentException) {
                Toast.makeText(context, "Operands can not be empty", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root)
                    .navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: ArithmeticException) {
                Toast.makeText(context, "Zero divisions are not allowed", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root)
                    .navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: IndexOutOfBoundsException) {
                Toast.makeText(
                    context,
                    "Can not calculate square root of a negative number",
                    Toast.LENGTH_LONG
                ).show()
                Navigation.findNavController(binding.root)
                    .navigate(R.id.pick_operand_to_pick_operand)
            } catch (e: UnsupportedOperationException) {
                Toast.makeText(context, "Operation is not supported", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root)
                    .navigate(R.id.pick_operand_to_pick_operand)
            }
        }

        binding.cancelBtn.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.pick_operand_to_select_operation)
        }

        return binding.root
    }

    override fun onDestroyView() {
        pickOperandBinding = null
        super.onDestroyView()
    }

    private fun addOperand() {
        when {
            (selectedOperation == "Square Root") && (operandOne.isEmpty()) -> {
                operandOne = pickOperandBinding?.addParamsEdittext?.text.toString()
                pickOperandBinding?.equationOperandRight?.text = operandOne
                pickOperandBinding?.addParamsEdittext?.text?.clear()
                pickOperandBinding?.addOperandBtn?.isEnabled = false
                pickOperandBinding?.addOperandBtn?.isClickable = false
            }
            (selectedOperation != "Square Root") && (operandOne.isEmpty()) -> {
                operandOne = pickOperandBinding?.addParamsEdittext?.text.toString()
                pickOperandBinding?.equationOperandLeft?.text = operandOne
                pickOperandBinding?.addParamsEdittext?.text?.clear()
            }
            (selectedOperation != "Square Root") && (operandOne.isNotEmpty()) -> {
                operandTwo = pickOperandBinding?.addParamsEdittext?.text.toString()
                pickOperandBinding?.equationOperandRight?.text = operandTwo
                pickOperandBinding?.addParamsEdittext?.text?.clear()
                pickOperandBinding?.addOperandBtn?.isEnabled = false
                pickOperandBinding?.addOperandBtn?.isClickable = false
            }
        }
    }

    private fun getNotation(op: String): String {
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